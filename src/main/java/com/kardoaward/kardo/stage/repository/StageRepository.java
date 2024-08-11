package com.kardoaward.kardo.stage.repository;

import com.kardoaward.kardo.stage.model.StageOfCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<StageOfCompetition, Long> {

}
