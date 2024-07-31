package com.kardoaward.kardo.photo.repository;

import com.kardoaward.kardo.partners.model.Partner;
import com.kardoaward.kardo.photo.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
