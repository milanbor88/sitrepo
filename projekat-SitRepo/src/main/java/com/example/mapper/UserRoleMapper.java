package com.example.mapper;

import com.example.dto.UserRolesDTO;
import com.example.model.UserRoles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRoleMapper {

    @Autowired
    ModelMapper modelMapper;

    public List<UserRolesDTO> convertListToDto(List<UserRoles> users) {
        List<UserRolesDTO> userDTOS = new ArrayList<UserRolesDTO>();
        userDTOS = users.stream().map(user -> modelMapper.map(user, UserRolesDTO.class)).collect(Collectors.toList());
        return userDTOS;
    }

    public List<UserRoles> convertListToEntity(List<UserRolesDTO> usersDto) throws ParseException {
        List<UserRoles> users = new ArrayList<UserRoles>();
        users = usersDto.stream().map(user -> modelMapper.map(user, UserRoles.class)).collect(Collectors.toList());
        return users;
    }

    public UserRolesDTO convertToDto(UserRoles user) {
        UserRolesDTO userDto = modelMapper.map(user, UserRolesDTO.class);

        return userDto;
    }

    public UserRoles convertToEntity(UserRolesDTO userDto) throws ParseException {
        UserRoles user = modelMapper.map(userDto, UserRoles.class);
        return user;
    }
}
