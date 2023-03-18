package com.app.hotels.web.mapper;

import com.app.hotels.domain.User;
import com.app.hotels.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
