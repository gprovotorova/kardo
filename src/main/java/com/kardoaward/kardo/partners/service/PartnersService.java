package com.kardoaward.kardo.partners.service;

import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.partners.dto.PartnerDto;

public interface PartnersService {
    PartnerDto createPartner(PartnerDto partnerDto);

    void deletePartner(Long partnerId);

    PartnerDto updatePartner(Long partnerId, PartnerDto partnerDto);
}
