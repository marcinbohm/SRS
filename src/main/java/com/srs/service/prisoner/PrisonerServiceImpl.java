package com.srs.service.prisoner;

import com.srs.OperationStatus;
import com.srs.VerificationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.PrisonerDTO;
import com.srs.entity.Prisoner;
import com.srs.mapper.SmartMapper;
import com.srs.repository.PrisonerRepository;
import com.srs.repository.filters.PrisonerFilter;
import com.srs.repository.specs.PrisonerSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class PrisonerServiceImpl implements PrisonerService {

    private final PrisonerRepository prisonerRepository;
    private final PrisonerVerificationService prisonerVerificationService;
    private final ModelMapper modelMapper;

    public PrisonerServiceImpl(PrisonerRepository prisonerRepository, PrisonerVerificationService prisonerVerificationService, ModelMapper modelMapper) {
        this.prisonerRepository = prisonerRepository;
        this.prisonerVerificationService = prisonerVerificationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public PrisonerDTO getPrisonerById(Integer prisonerId) {
        return prisonerRepository.findById(prisonerId)
                .map(prisoner -> this.modelMapper.map(prisoner, PrisonerDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PrisonerDTO> getAllPrisoners() {
        return prisonerRepository.findAll()
                .stream()
                .map(prisoner -> this.modelMapper.map(prisoner, PrisonerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PrisonerDTO> getPrisonersByFilter(PrisonerFilter prisonerFilter) {

        Specification<Prisoner> spec = Specification
                .where(PrisonerSpecs.prisonerIdIn(prisonerFilter.prisonerIdList))
                .and(PrisonerSpecs.nameEquals(prisonerFilter.name))
                .and(PrisonerSpecs.surnameEquals(prisonerFilter.surname))
                .and(PrisonerSpecs.peselEquals(prisonerFilter.pesel))
                .and(PrisonerSpecs.kartaPobytuEquals(prisonerFilter.kartaPobytuId))
                .and(PrisonerSpecs.passportEquals(prisonerFilter.passportId));

        return prisonerRepository.findAll(spec)
                .stream()
                .map(application -> this.modelMapper.map(application, PrisonerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OperationStatus upsert(Prisoner prisoner) {
        boolean adding = (prisoner.getPrisonerId() == null);

        Prisoner prisonerExisting =
                (adding ? new Prisoner() : prisonerRepository.findById(prisoner.getPrisonerId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + prisoner.getPrisonerId())));

        OperationStatus opStatus =
                new OperationStatus(Prisoner.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());


        SmartMapper.transferData(prisoner, prisonerExisting);

        VerificationStatus verificationStatus =
                prisonerVerificationService.verifyPrisoner(prisonerExisting);

        if (!verificationStatus.isAccepted()) {
            return opStatus.setRecordId(prisonerExisting.getPrisonerId()).setSuccess(false)
                    .addMessage(verificationStatus.getMessage());
        }

        Prisoner prisonerSaved =
                prisonerRepository.save(prisoner);
        Integer id = prisonerSaved.getPrisonerId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer prisonerId) {

        Prisoner prisoner =
                prisonerRepository
                        .findById(prisonerId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Prisoner.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(prisonerId);
        prisonerRepository.delete(prisoner);

        return opStatus.setSuccess(!prisonerRepository.existsById(prisonerId));
    }
}
