package com.srs.service.shift;

import com.srs.OperationStatus;
import com.srs.dto.ShiftDTO;
import com.srs.entity.Shift;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.ShiftFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftService {

    ShiftDTO getShiftById(Integer shiftId);

    List<ShiftDTO> getAllShifts();

    List<ShiftDTO> getShiftsByFilter(ShiftFilter shiftFilter);

    OperationStatus upsert(Shift shift);

    OperationStatus delete(Integer shiftId);
}
