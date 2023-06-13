package com.srs.service.report;

import com.srs.OperationStatus;
import com.srs.dto.ReportDTO;
import com.srs.entity.Report;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.ReportFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {

    ReportDTO getReportById(Integer reportId);

    List<ReportDTO> getAllReports();

    List<ReportDTO> getReportsByFilter(ReportFilter reportFilter);

    OperationStatus upsert(Report report);

    OperationStatus delete(Integer reportId);
}
