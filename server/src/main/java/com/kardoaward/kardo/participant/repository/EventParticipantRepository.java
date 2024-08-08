package com.kardoaward.kardo.participant.repository;

import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.participant.dto.EventParticipantDto;
import com.kardoaward.kardo.participant.model.EventParticipant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {

    Page<EventDto> findAllEventsByUserId(Long userId, Pageable page);
}
