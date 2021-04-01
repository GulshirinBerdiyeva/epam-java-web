package com.epam.task.web.project.entity;

public class Admin extends User{

    public Admin(Long id, String name, String login, String password, Role role) {
        super(id, name, login, password, role);
    }
}
