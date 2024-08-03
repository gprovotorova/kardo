package com.kardoaward.kardo.partners.service;

import com.kardoaward.kardo.partners.dto.PartnerDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartnerService {

    PartnerDto getPartnerById(Long partnerId);

    List<PartnerDto> getAllPartnersWithFilters(String type, Pageable page);
}
