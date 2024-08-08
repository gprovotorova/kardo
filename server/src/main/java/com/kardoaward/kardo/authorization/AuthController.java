package com.kardoaward.kardo.authorization;

import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.dto.UserEntrance;
import com.kardoaward.kardo.user.service.UserService;
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
public class AuthController {

    private final UserService userService;
    private static final String USER = "Authorization";

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String registration(@RequestBody UserDto userDto) {
        return userService.registration(userDto);
    }

    @GetMapping("/logout")
    @ResponseStatus(code = HttpStatus.OK)
    public String logout(@RequestHeader(USER) long userId) {
        return "redirect:/feed";
    }

    @GetMapping("/login")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String login(@RequestBody UserEntrance userEntrance) {
        return userService.login(userEntrance);
    }
}
