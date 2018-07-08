package com.example.mapper;

import com.example.dto.RoleDTO;
import com.example.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<RoleDTO> convertListToDto(List<Role> roles) {
        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        roleDTOS = roles.stream().map(r -> modelMapper.map(r, RoleDTO.class)).collect(Collectors.toList());
        return roleDTOS;
    }

    public List<Role> convertListToEntity(List<RoleDTO> roleDTOS) throws ParseException {
        List<Role> roles = new ArrayList<Role>();
        roles = roleDTOS.stream().map(r -> modelMapper.map(r, Role.class)).collect(Collectors.toList());
        return roles;
    }

    public RoleDTO convertToDto(Role role) {
        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);

        return roleDTO;
    }

    public Role convertToEntity(RoleDTO roleDTO) throws ParseException {
        Role role = modelMapper.map(roleDTO, Role.class);
        return role;
    }

}
