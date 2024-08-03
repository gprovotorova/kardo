package com.kardoaward.kardo.admin.dto;

import com.kardoaward.kardo.file.model.FileInfo;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Set<String> links = new HashSet<>();

    private String phone;

    private FileInfo fileInfo;
}
