package com.kardoaward.kardo.user.controller;

import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.repository.UserRepository;
import com.kardoaward.kardo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@Slf4j
@AllArgsConstructor
@Validated
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/admin/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(@RequestParam(required = false) List<Long> ids,
                                     @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        if (ids == null) {
            ids = new ArrayList<>();
        }
        log.info("Получение всех пользователей: {}", ids.size());
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getAllUsers(ids, page);
    }

    @PostMapping("/admin/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        return userService.createUser(userDto);
    }

    @DeleteMapping("/admin/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @PositiveOrZero Long userId) {
        log.info("Удаление пользователя с id {}", userId);
        userService.deleteUser(userId);
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration/watcher")
    public String addParticipant(@RequestBody @Valid UserDto userDto, Map<String, Object> model) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        userDto.setType(UserType.WATCHER);
        userService.createUser(userDto);

        return "redirect:/login";
    }

    @PostMapping("/registration/admin")
    public String addAdmin(@RequestBody @Valid UserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        userDto.setType(UserType.ADMIN);
        userService.createUser(userDto);

        return "redirect:/login";
    }

    @PostMapping("/registration/expert")
    public String addExpert(@RequestBody @Valid UserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        userDto.setType(UserType.EXPERT);
        userService.createUser(userDto);

        return "redirect:/login";
    }
}
