package com.kardoaward.kardo.partners.service;

import com.kardoaward.kardo.enums.PartnerType;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.partners.model.Partner;
import com.kardoaward.kardo.partners.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public PartnerDto getPartnerById(Long partnerId) {
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new ObjectNotFoundException("Партнер с id = " + partnerId + " не найден."));
        return mapper.map(partner, PartnerDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartnerDto> getAllPartnersWithFilters(PartnerType partnerType, Pageable page) {
        List<PartnerDto> savedPartners = new ArrayList<>();
        if(partnerType == null){
            savedPartners = partnerRepository.findAll(page)
                    .getContent()
                    .stream()
                    .map(partner -> mapper.map(partner, PartnerDto.class))
                    .collect(Collectors.toList());
        } else {
            String type = partnerType.name();
            savedPartners = partnerRepository.findAll(type, page)
                    .getContent()
                    .stream()
                    .map(partner -> mapper.map(partner, PartnerDto.class))
                    .collect(Collectors.toList());
        }
        return savedPartners;
    }
}
