package com.kardoaward.kardo.partners.controller;

import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.partners.service.PartnersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("admin/partners")
@Slf4j
@AllArgsConstructor
@Validated
public class AdminPartnerController {

    private final PartnersService partnerService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PartnerDto createPartner (@RequestBody @Valid PartnerDto partnerDto){
        log.info("Добавление нового gfhnythf {}", partnerDto.toString());
        return partnerService.createPartner(partnerDto);
    }

    @DeleteMapping("/{partnerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePartner(@PathVariable @PositiveOrZero Long partnerId) {
        log.info("Удаление партнера с id {}", partnerId);
        partnerService.deletePartner(partnerId);
    }

    @PatchMapping("/{partnerId}")
    @ResponseStatus(HttpStatus.OK)
    public PartnerDto updatePartner(@PathVariable @PositiveOrZero Long partnerId, @RequestBody @Valid PartnerDto partnerDto){
        log.info("Изменение партнера с id {}", partnerId);
        return partnerService.updatePartner(partnerId, partnerDto);
    }
}

