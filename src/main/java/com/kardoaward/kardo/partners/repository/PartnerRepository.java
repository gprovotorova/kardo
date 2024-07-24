package com.kardoaward.kardo.partners.repository;

import com.kardoaward.kardo.partners.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

}
