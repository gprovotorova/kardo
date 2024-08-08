package com.kardoaward.kardo.streams.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StreamDto {

    private Long id;

    private String name;

    private URL link;

    private LocalDateTime streamDateTime;

    private LocalDate publishedDate;
}
