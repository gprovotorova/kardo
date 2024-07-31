package com.kardoaward.kardo.event.repository;

import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByName(Long id);
}
