package com.srs.service.shift;

import com.srs.OperationStatus;
import com.srs.VerificationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.ShiftDTO;
import com.srs.entity.Shift;
import com.srs.mapper.SmartMapper;
import com.srs.repository.ShiftRepository;
import com.srs.repository.filters.ShiftFilter;
import com.srs.repository.specs.ShiftSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftVerificationService shiftVerificationService;
    private final ModelMapper modelMapper;

    public ShiftServiceImpl(ShiftRepository shiftRepository, ShiftVerificationService shiftVerificationService, ModelMapper modelMapper) {
        this.shiftRepository = shiftRepository;
        this.shiftVerificationService = shiftVerificationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ShiftDTO getShiftById(Integer shiftId) {
        return shiftRepository.findById(shiftId)
                .map(shift -> this.modelMapper.map(shift, ShiftDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ShiftDTO> getAllShifts() {
        return shiftRepository.findAll()
                .stream()
                .map(shift -> this.modelMapper.map(shift, ShiftDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShiftDTO> getShiftsByFilter(ShiftFilter shiftFilter) {
        Specification<Shift> spec = Specification
                .where(ShiftSpecs.userTypeEquals(shiftFilter.userType))
                .and(ShiftSpecs.userIdIn(shiftFilter.userIdList));

        return shiftRepository.findAll(spec)
                .stream()
                .map(cell -> this.modelMapper.map(cell, ShiftDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OperationStatus upsert(Shift shift) {
        boolean adding = (shift.getShiftId() == null);

        Shift shiftExisting =
                (adding ? new Shift() : shiftRepository.findById(shift.getShiftId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + shift.getShiftId())));

        OperationStatus opStatus =
                new OperationStatus(Shift.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(shift, shiftExisting);

        VerificationStatus verificationStatus =
                shiftVerificationService.verifyShift(shiftExisting);

        if (!verificationStatus.isAccepted()) {
            return opStatus.setRecordId(shiftExisting.getShiftId()).setSuccess(false)
                    .addMessage(verificationStatus.getMessage());
        }

        Shift shiftSaved =
                shiftRepository.save(shift);
        Integer id = shiftSaved.getShiftId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer shiftId) {

        Shift shift =
                shiftRepository
                        .findById(shiftId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Shift.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(shiftId);
        shiftRepository.delete(shift);

        return opStatus.setSuccess(!shiftRepository.existsById(shiftId));
    }
}
