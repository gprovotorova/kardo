package com.kardoaward.kardo.partners.service;

import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.exception.ObjectExistException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.partners.model.Partner;
import com.kardoaward.kardo.partners.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnersServiceImp implements PartnersService{

    private PartnerRepository partnerRepository;
    private ModelMapper mapper;

    @Override
    public PartnerDto createPartner(PartnerDto partnerDto) {
        Partner partner = mapper.map(partnerDto, Partner.class);
        if(partnerRepository.existsByName(partner.getName())){
            throw new ObjectExistException("Партнер с таким названием уже существует");
        }
        return mapper.map(partnerRepository.save(partner), PartnerDto.class);
    }

    @Override
    public void deletePartner(Long partnerId) {
        if(partnerRepository.existsById(partnerId)){
            partnerRepository.deleteById(partnerId);
        } else {
            throw new ObjectNotFoundException("Партнер с таким id не существует");
        }
    }

    @Override
    public PartnerDto updatePartner(Long partnerId, PartnerDto partnerDto) {
        Partner partner = mapper.map(partnerDto, Partner.class);
        if(!partnerRepository.existsById(partnerId)){
            throw new ObjectNotFoundException("Партнер с таким id не существует");
        }
        return mapper.map(partnerRepository.save(partner), PartnerDto.class);
    }
}

