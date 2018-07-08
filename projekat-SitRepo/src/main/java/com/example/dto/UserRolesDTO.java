package com.example.dto;

import java.io.Serializable;

public class UserRolesDTO implements Serializable {
    private Long id;

    private UserDTO userDTO;

    private RoleDTO roleDTO;

    public UserRolesDTO(){};

    public UserRolesDTO(UserDTO userDTO, RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
        this.userDTO = userDTO;
    }


    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
