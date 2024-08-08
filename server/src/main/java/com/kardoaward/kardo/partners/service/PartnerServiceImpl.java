package com.kardoaward.kardo.partners.service;

import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.exception.ConflictDataException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.exception.StorageFileNotFoundException;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.partners.model.Partner;
import com.kardoaward.kardo.partners.repository.PartnerRepository;
import com.kardoaward.kardo.user.model.User;
import com.kardoaward.kardo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final UserRepository userRepository;
    private ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PartnerDto getPartnerById(Long userId, Long partnerId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (!user.getType().equals(UserType.ADMIN)){
            throw new ConflictDataException("Нет доступа");
        }
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new ObjectNotFoundException("Партнер с id = " + partnerId + " не найден."));
        return mapper.map(partner, PartnerDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartnerDto> getAllPartnersWithFilters(Long userId, String type, Pageable page) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (!user.getType().equals(UserType.ADMIN)){
            throw new ConflictDataException("Нет доступа");
        }
        return partnerRepository.findAll(page)
                .getContent()
                .stream()
                .map(partner -> mapper.map(partner, PartnerDto.class))
                .collect(Collectors.toList());
    }
}
