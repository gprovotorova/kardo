package com.kardoaward.kardo.authorization;

import com.kardoaward.kardo.user.dto.NewUserDto;
import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.dto.UserEntrance;
import com.kardoaward.kardo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping()
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name="Авторизация")
public class AuthController {

    private final UserService userService;
    private static final String USER = "Authorization";

    @Operation(description = "Регистрация")
    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto registration(@RequestBody NewUserDto userDto) {
        return userService.registration(userDto);
    }

    @Operation(description = "Выход")
    @GetMapping("/logout")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto logout(@RequestHeader(USER) long userId) {
        return userService.getUserById(userId);
    }

    @Operation(description = "Вход")
    @GetMapping("/login")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public UserDto login(@RequestBody UserEntrance userEntrance) {
        return userService.login(userEntrance);
    }
}
