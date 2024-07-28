package com.kardoaward.kardo.photo;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDto {

    private Long id;
    private String link;
}
