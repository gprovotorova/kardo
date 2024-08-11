package com.kardoaward.kardo.partners.repository;

import com.kardoaward.kardo.partners.model.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    boolean existsByName(String name);

    @Query("select p from Partner p " +
            "where (:type is null or (lower(p.type) like lower(concat('%', :type, '%'))))")
    Page<Partner> findAll(@Param("type") String type, Pageable page);

    Page<Partner> findAll(Pageable page);
}
