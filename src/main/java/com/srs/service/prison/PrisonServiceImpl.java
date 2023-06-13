package com.srs.service.prison;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.PrisonDTO;
import com.srs.dto.SentencePrisonerDTO;
import com.srs.entity.Prison;
import com.srs.entity.Sentence;
import com.srs.mapper.SmartMapper;
import com.srs.repository.PrisonRepository;
import com.srs.repository.SentenceRepository;
import com.srs.repository.filters.PrisonFilter;
import com.srs.repository.specs.PrisonSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class PrisonServiceImpl implements PrisonService {

    private final PrisonRepository prisonRepository;
    private final SentenceRepository sentenceRepository;
    private final ModelMapper modelMapper;

    public PrisonServiceImpl(PrisonRepository prisonRepository, SentenceRepository sentenceRepository, ModelMapper modelMapper) {
        this.prisonRepository = prisonRepository;
        this.sentenceRepository = sentenceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PrisonDTO getPrisonById(Integer prisonId) {
        return prisonRepository.findById(prisonId)
                .map(prison -> this.modelMapper.map(prison, PrisonDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PrisonDTO> getAllPrisons() {
        return prisonRepository.findAll()
                .stream()
                .map(prison -> this.modelMapper.map(prison, PrisonDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PrisonDTO> getPrisonsByFilter(PrisonFilter prisonFilter) {

        Specification<Prison> spec = Specification
                .where(PrisonSpecs.prisonIdIn(prisonFilter.prisonIdList))
                .and(PrisonSpecs.voivodeshipEquals(prisonFilter.voivodeship))
                .and(PrisonSpecs.cityEquals(prisonFilter.city))
                .and(PrisonSpecs.postalCodeEquals(prisonFilter.postalCode))
                .and(PrisonSpecs.streetEquals(prisonFilter.street));

        return prisonRepository.findAll(spec)
                .stream()
                .map(application -> this.modelMapper.map(application, PrisonDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OperationStatus upsert(Prison prison) {
        boolean adding = (prison.getPrisonId() == null);

        Prison prisonExisting =
                (adding ? new Prison() : prisonRepository.findById(prison.getPrisonId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + prison.getPrisonId())));

        OperationStatus opStatus =
                new OperationStatus(Prison.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(prison, prisonExisting);

        Prison prisonSaved =
                prisonRepository.save(prisonExisting);
        Integer id = prisonSaved.getPrisonId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer prisonId) {

        Prison prison =
                prisonRepository
                        .findById(prisonId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Prison.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(prisonId);
        prisonRepository.delete(prison);

        return opStatus.setSuccess(!prisonRepository.existsById(prisonId));
    }

    @Override
    public List<SentencePrisonerDTO> getPrisonSentencesWithPrisonerData(Integer prisonId) {
        List<Sentence> sentenceList = sentenceRepository.findByPrisonId(prisonId);
        return sentenceList
                .stream()
                .map(this::sentenceToSentencePrisonerDTOMapper)
                .collect(Collectors.toList());
    }

    private SentencePrisonerDTO sentenceToSentencePrisonerDTOMapper(Sentence sentence) {
        SentencePrisonerDTO sentencePrisonerDTO = new SentencePrisonerDTO();

        sentencePrisonerDTO.setSentenceId(sentence.getSentenceId());
        sentencePrisonerDTO.setPrisonerId(sentence.getPrisonerId());
        sentencePrisonerDTO.setPrisonId(sentence.getPrisonId());
        sentencePrisonerDTO.setStartDate(sentence.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        sentencePrisonerDTO.setEndDate(sentence.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        sentencePrisonerDTO.setAssignDate(sentence.getAssignDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        sentencePrisonerDTO.setUnassignDate(sentence.getUnassignDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        sentencePrisonerDTO.setName(sentence.getPrisoner().getName());
        sentencePrisonerDTO.setSurname(sentence.getPrisoner().getSurname());
        sentencePrisonerDTO.setPesel(sentence.getPrisoner().getPesel());
        sentencePrisonerDTO.setKartaPobytuId(sentence.getPrisoner().getKartaPobytuId());
        sentencePrisonerDTO.setPassportId(sentence.getPrisoner().getPassportId());

        return sentencePrisonerDTO;
    }
}
