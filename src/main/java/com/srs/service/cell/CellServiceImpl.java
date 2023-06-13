package com.srs.service.cell;

import com.srs.OperationStatus;
import com.srs.VerificationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.CellDTO;
import com.srs.entity.Cell;
import com.srs.mapper.SmartMapper;
import com.srs.repository.CellRepository;
import com.srs.repository.filters.CellFilter;
import com.srs.repository.specs.CellSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class CellServiceImpl implements CellService {

    private final CellRepository cellRepository;
    private final CellVerificationService cellVerificationService;
    private final ModelMapper modelMapper;

    public CellServiceImpl(CellRepository cellRepository, CellVerificationService cellVerificationService, ModelMapper modelMapper) {
        this.cellRepository = cellRepository;
        this.cellVerificationService = cellVerificationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CellDTO getCellById(Integer cellId) {
        return cellRepository.findById(cellId)
                .map(cell -> this.modelMapper.map(cell, CellDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<CellDTO> getAllCells() {
        return cellRepository.findAll()
                .stream()
                .map(cell -> this.modelMapper.map(cell, CellDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CellDTO> getCellsByFilter(CellFilter cellFilter) {
        Specification<Cell> spec = Specification
                .where(CellSpecs.cellIdIn(cellFilter.cellIdList))
                .and(CellSpecs.cellIdIn(cellFilter.cellIdList))
                .and(CellSpecs.segmentEquals(cellFilter.segment))
                .and(CellSpecs.blokEquals(cellFilter.blok))
                .and(CellSpecs.assignDateGreaterThanOrEqualTo(cellFilter.assignDateFrom))
                .and(CellSpecs.assignDateLessThanOrEqualTo(cellFilter.assignDateTo))
                .and(CellSpecs.unassignDateGreaterThanOrEqualTo(cellFilter.unassignDateFrom))
                .and(CellSpecs.unassignDateLessThanOrEqualTo(cellFilter.unassignDateTo));

        return cellRepository.findAll(spec)
                .stream()
                .map(cell -> this.modelMapper.map(cell, CellDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OperationStatus upsert(Cell cell) {
        boolean adding = (cell.getCellId() == null);

        Cell cellExisting =
                (adding ? new Cell() : cellRepository.findById(cell.getCellId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + cell.getCellId())));

        OperationStatus opStatus =
                new OperationStatus(Cell.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(cell, cellExisting);

        VerificationStatus verificationStatus =
                cellVerificationService.verifyCell(cellExisting);

        if (!verificationStatus.isAccepted()) {
            return opStatus.setRecordId(cellExisting.getCellId()).setSuccess(false)
                    .addMessage(verificationStatus.getMessage());
        }

        Cell cellSaved =
                cellRepository.save(cell);
        Integer id = cellSaved.getCellId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer cellId) {

        Cell cell =
                cellRepository
                        .findById(cellId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Cell.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(cellId);
        cellRepository.delete(cell);

        return opStatus.setSuccess(!cellRepository.existsById(cellId));
    }
}
