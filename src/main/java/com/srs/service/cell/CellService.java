package com.srs.service.cell;

import com.srs.OperationStatus;
import com.srs.dto.CellDTO;
import com.srs.entity.Cell;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.CellFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface CellService {

    CellDTO getCellById(Integer cellId);

    List<CellDTO> getAllCells();

    List<CellDTO> getCellsByFilter(CellFilter cellFilter);

    OperationStatus upsert(Cell cell);

    OperationStatus delete(Integer cellId);
}
