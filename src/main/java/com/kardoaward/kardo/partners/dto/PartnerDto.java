package com.kardoaward.kardo.partners.dto;

import com.kardoaward.kardo.photo.Photo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerDto {

    private Photo photo;

    private String name;

    private String type;

    private String description;

    private String link;
}

