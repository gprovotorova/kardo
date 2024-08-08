package com.kardoaward.kardo.participant.repository;

import com.kardoaward.kardo.participant.model.EvaluatingParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluatingParticipantRepository extends JpaRepository<EvaluatingParticipant, Long> {

}
