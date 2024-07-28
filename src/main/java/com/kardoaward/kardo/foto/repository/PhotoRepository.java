package com.kardoaward.kardo.foto.repository;

import com.kardoaward.kardo.partners.model.Partner;
import com.kardoaward.kardo.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
