package com.kardoaward.kardo.streams.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.common.Constants;
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

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime streamDateTime;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate publishedDate;
}
