package com.kardoaward.kardo.partners.controller;

import com.kardoaward.kardo.partners.PartnerClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/partners")
@Slf4j
@AllArgsConstructor
@Validated
public class PartnerController {

    private final PartnerClient partnerClient;

    @GetMapping()
    public ResponseEntity getAllPartnersWithFilters(@RequestParam(required = true) String type,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение комментариев с возможностью фильтрации");
        return partnerClient.getAllPartnersWithFilters(type, from, size);
    }

    @GetMapping("/{partnerId}")
    public ResponseEntity getPartnerById(@PathVariable @PositiveOrZero Long partnerId) {
        log.info("Получение данных партнера по его id {}", partnerId);
        return partnerClient.getPartnerById(partnerId);
    }
}

