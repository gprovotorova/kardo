package com.kardoaward.kardo.partners.controller;

import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.partners.service.PartnerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/partners")
@Slf4j
@RequiredArgsConstructor
@Validated
public class PartnerController {

    private final PartnerService partnerService;
    private static final String USER = "Authorization";


    @GetMapping()
    public List<PartnerDto> getAllPartnersWithFilters(@RequestHeader(USER) long userId,
                                                      @RequestParam String type,
                                                      @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                      @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение комментариев с возможностью фильтрации");
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return partnerService.getAllPartnersWithFilters(userId, type, page);
    }

    @GetMapping("/{partnerId}")
    public PartnerDto getPartnerById(@RequestHeader(USER) long userId,
                                     @PathVariable @PositiveOrZero Long partnerId) {
        log.info("Получение данных партнера по его id {}", partnerId);
        return partnerService.getPartnerById(userId, partnerId);
    }
}

