package com.kardoaward.kardo.partners.service;

import com.kardoaward.kardo.enums.PartnerType;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartnerService {

    PartnerDto getPartnerById(long userId, Long partnerId);

    List<PartnerDto> getAllPartnersWithFilters(long userId, PartnerType type, Pageable page);
}
