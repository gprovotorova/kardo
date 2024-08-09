package com.kardoaward.kardo.partners.service;

import com.kardoaward.kardo.partners.dto.PartnerDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartnerService {

    PartnerDto getPartnerById(Long userId, Long partnerId);

    List<PartnerDto> getAllPartnersWithFilters(Long userId, String type, Pageable page);
}
