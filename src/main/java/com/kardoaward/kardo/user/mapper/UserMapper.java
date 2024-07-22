package com.kardoaward.kardo.user.mapper;


import com.kardoaward.kardo.user.dto.UserShortDto;
import com.kardoaward.kardo.user.model.User;

public class UserMapper {
    public static UserShortDto toUserDto(User user) {
        return new UserShortDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getType(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthday(),
                user.getCountry(),
                user.getRegion(),
                user.getCity());
    }

    public static User toUser(UserShortDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getPatronymic(),
                userDto.getType(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getBirthday(),
                userDto.getCountry(),
                userDto.getRegion(),
                userDto.getCity()
        );
    }
}
