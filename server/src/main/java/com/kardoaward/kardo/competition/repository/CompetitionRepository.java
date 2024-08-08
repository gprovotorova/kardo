package com.kardoaward.kardo.competition.repository;

import com.kardoaward.kardo.competition.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    boolean existsByName(String name);
}
