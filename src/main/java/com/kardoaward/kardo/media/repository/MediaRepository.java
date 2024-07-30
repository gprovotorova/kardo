package com.kardoaward.kardo.media.repository;

import com.kardoaward.kardo.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Boolean existsByName(String name);
}
