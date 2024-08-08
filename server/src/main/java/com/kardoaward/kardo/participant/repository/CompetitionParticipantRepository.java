package com.kardoaward.kardo.participant.repository;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.participant.dto.CompetitionParticipantDto;
import com.kardoaward.kardo.participant.model.CompetitionParticipant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionParticipantRepository extends JpaRepository<CompetitionParticipant, Long> {

    @Query("select c from CompetitionParticipant c " +
            "where c.user.id = :userId")
    Page<CompetitionDto> findAllCompetitionsByUserId(Long userId, Pageable page);

    @Query("select c from CompetitionParticipant c " +
            "where c.user.id = :userId " +
            "and c.competition.id = :compId")
    Page<CompetitionParticipantDto> findAllByUserIdAndCompetitionId(Long userId, Long compId, Pageable page);
}
