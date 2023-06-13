package com.srs.service.report;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.ReportDTO;
import com.srs.dto.ShiftDTO;
import com.srs.entity.Application;
import com.srs.entity.Report;
import com.srs.entity.Report;
import com.srs.entity.Shift;
import com.srs.mapper.SmartMapper;
import com.srs.repository.ReportRepository;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.ReportFilter;
import com.srs.repository.specs.ReportSpecs;
import com.srs.repository.specs.ShiftSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReportDTO getReportById(Integer reportId) {
        return reportRepository.findById(reportId)
                .map(report -> this.modelMapper.map(report, ReportDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(report -> this.modelMapper.map(report, ReportDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportDTO> getReportsByFilter(ReportFilter reportFilter) {
        Specification<Report> spec = Specification
                .where(ReportSpecs.userTypeEquals(reportFilter.userType))
                .and(ReportSpecs.userIdIn(reportFilter.userIdList));

        return reportRepository.findAll(spec)
                .stream()
                .map(cell -> this.modelMapper.map(cell, ReportDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OperationStatus upsert(Report report) {
        boolean adding = (report.getReportId() == null);

        Report reportExisting =
                (adding ? new Report() : reportRepository.findById(report.getReportId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + report.getReportId())));

        OperationStatus opStatus =
                new OperationStatus(Report.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(report, reportExisting);

        Report reportSaved =
                reportRepository.save(report);
        Integer id = reportSaved.getReportId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer reportId) {

        Report report =
                reportRepository
                        .findById(reportId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Report.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(reportId);
        reportRepository.delete(report);

        return opStatus.setSuccess(!reportRepository.existsById(reportId));
    }
}
