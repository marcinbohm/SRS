package com.srs.service.sentence;

import com.srs.OperationStatus;
import com.srs.VerificationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.SentenceDTO;
import com.srs.entity.Cell;
import com.srs.entity.Sentence;
import com.srs.mapper.SmartMapper;
import com.srs.repository.CellRepository;
import com.srs.repository.SentenceRepository;
import com.srs.repository.filters.SentenceFilter;
import com.srs.repository.specs.SentenceSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class SentenceServiceImpl implements SentenceService {

    private final SentenceRepository sentenceRepository;
    private final CellRepository cellRepository;
    private final SentenceVerificationService sentenceVerificationService;
    private final ModelMapper modelMapper;

    public SentenceServiceImpl(SentenceRepository sentenceRepository, CellRepository cellRepository, SentenceVerificationService sentenceVerificationService, ModelMapper modelMapper) {
        this.sentenceRepository = sentenceRepository;
        this.cellRepository = cellRepository;
        this.sentenceVerificationService = sentenceVerificationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public SentenceDTO getSentenceById(Integer sentenceId) {
        return sentenceRepository.findById(sentenceId)
                .map(sentence -> this.modelMapper.map(sentence, SentenceDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<SentenceDTO> getAllSentences() {
        return sentenceRepository.findAll()
                .stream()
                .map(sentence -> this.modelMapper.map(sentence, SentenceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SentenceDTO> getSentencesByFilter(SentenceFilter sentenceFilter) {

        Specification<Sentence> spec = Specification
                .where(SentenceSpecs.sentenceIdIn(sentenceFilter.sentenceIdList))
                .and(SentenceSpecs.prisonerIdIn(sentenceFilter.sentenceIdList))
                .and(SentenceSpecs.sentenceIdIn(sentenceFilter.sentenceIdList))
                .and(SentenceSpecs.startDateLessThanOrEqualTo(sentenceFilter.startDateTo))
                .and(SentenceSpecs.startDateGreaterThanOrEqualTo(sentenceFilter.startDateFrom));

        return sentenceRepository.findAll(spec)
                .stream()
                .map(sentence -> this.modelMapper.map(sentence, SentenceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OperationStatus upsert(Sentence sentence) {
        boolean adding = (sentence.getSentenceId() == null);

        Sentence sentenceExisting =
                (adding ? new Sentence() : sentenceRepository.findById(sentence.getSentenceId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + sentence.getSentenceId())));

        OperationStatus opStatus =
                new OperationStatus(Sentence.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(sentence, sentenceExisting);

        VerificationStatus verificationStatus =
                sentenceVerificationService.verifySentence(sentenceExisting);

        if (!verificationStatus.isAccepted()) {
            return opStatus.setRecordId(sentenceExisting.getSentenceId()).setSuccess(false)
                    .addMessage(verificationStatus.getMessage());
        }

        Sentence sentenceSaved =
                sentenceRepository.save(sentence);
        Integer id = sentenceSaved.getSentenceId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer sentenceId) {

        Sentence sentence =
                sentenceRepository
                        .findById(sentenceId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Sentence.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(sentenceId);
        sentenceRepository.delete(sentence);

        return opStatus.setSuccess(!sentenceRepository.existsById(sentenceId));
    }

    @Override
    public OperationStatus setSentenceCell(Integer sentenceId, Integer cellId) {

        Sentence sentence = sentenceRepository.findById(sentenceId)
                .orElseThrow(EntityNotFoundException::new);

        Cell cell = cellRepository.findById(cellId)
                .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Sentence.class.getSimpleName(),
                        DictOperationName.UPDATE.getCode()
                );

        sentence.getSentenceCellSet().add(cell);
        opStatus.setRecordId(sentenceId);
        sentenceRepository.save(sentence);

        return opStatus.setSuccess(true);
    }
}
