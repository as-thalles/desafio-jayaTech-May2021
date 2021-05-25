package com.phanthasm.currencyConverter.dto;

import com.phanthasm.currencyConverter.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public UserDTO() {}

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
