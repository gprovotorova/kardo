package com.kardoaward.kardo.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntrance {

    @Email(message = "Неверный формат e-mail.")
    @NotEmpty(message = "Адрес электронной почты не может быть пустым.")
    @Size(min = 6, max = 254)
    private String email;

    @NotBlank(message = "Поле \"пароль\" не может быть пустым.")
    private String password;
}
