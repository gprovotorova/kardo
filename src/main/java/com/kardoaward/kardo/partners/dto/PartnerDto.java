package com.kardoaward.kardo.partners.dto;

import com.kardoaward.kardo.media.model.Media;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerDto {

    private Media media;

    private String name;

    private String type;

    private String description;

    private String link;
}

