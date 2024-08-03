package com.kardoaward.kardo.partners.dto;

import com.kardoaward.kardo.file.model.FileInfo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerDto {

    private FileInfo file;

    private String name;

    private String type;

    private String description;

    private String link;
}

