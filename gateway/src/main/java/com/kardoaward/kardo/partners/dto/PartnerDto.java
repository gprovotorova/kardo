package com.kardoaward.kardo.partners.dto;

import lombok.*;
import org.apache.tomcat.jni.FileInfo;

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

