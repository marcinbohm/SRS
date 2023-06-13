package com.srs.repository;

import com.srs.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Integer>, JpaSpecificationExecutor<Sentence> {
    List<Sentence> findByPrisonId(Integer prisonId);
}
