package com.kardoaward.kardo.partners.mapper;

import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.partners.model.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {
    public static PartnerDto toPartnerDto(Partner partner) {
        return new PartnerDto(
                partner.getId(),
                partner.getPhoto(),
                partner.getName(),
                partner.getType(),
                partner.getDescription(),
                partner.getLink()
        );
    }

    public static Partner toPartner(PartnerDto partnerDto) {
        return new Partner(
                partnerDto.getId(),
                partnerDto.getPhoto(),
                partnerDto.getName(),
                partnerDto.getType(),
                partnerDto.getDescription(),
                partnerDto.getLink()
        );
    }
}
