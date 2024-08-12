package com.kardoaward.kardo.partners.dto;

import com.kardoaward.kardo.enums.PartnerType;
import com.kardoaward.kardo.file.model.FileInfo;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerDto {

    private FileInfo file;

    private String name;

    @Enumerated(EnumType.STRING)
    private PartnerType type;

    private String description;

    private String link;
}

