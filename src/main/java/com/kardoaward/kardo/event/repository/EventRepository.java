package com.kardoaward.kardo.event.repository;

import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByName(Long id);

    @Query("select e from Event e, Direction d " +
            "where (:type is null or e.type = :type) " +
            "and (:date is null or e.date = :date)" +
            "group by e.id ")
    Page<Event> findEvents(@Param("type") EventType eventType,
                           @Param("date") LocalDate date,
                           Pageable page);
}


