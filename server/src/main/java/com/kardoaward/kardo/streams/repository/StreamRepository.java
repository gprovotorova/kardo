package com.kardoaward.kardo.streams.repository;

import com.kardoaward.kardo.streams.model.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {

}
