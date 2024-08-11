package com.kardoaward.kardo.file.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.common.Constants;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "files_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_size")
    private Long size;

    @Column(name = "file_key")
    private String key;

    @Column(name = "file_upload_date")
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate uploadDate = LocalDate.now();
}
