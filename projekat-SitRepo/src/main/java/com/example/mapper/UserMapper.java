package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<UserDTO> convertListToDto(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        userDTOS = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOS;
    }

    public List<User> convertListToEntity(List<UserDTO> usersDto) throws ParseException{
        List<User> users = new ArrayList<User>();
        users = usersDto.stream().map(user -> modelMapper.map(user, User.class)).collect(Collectors.toList());
        return users;
    }

    public UserDTO convertToDto(User user) {
        UserDTO userDto = modelMapper.map(user, UserDTO.class);

        return userDto;
    }

    public User convertToEntity(UserDTO userDto) throws ParseException {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
