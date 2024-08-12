package com.kardoaward.kardo.streams.repository;

import com.kardoaward.kardo.streams.model.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {

}
