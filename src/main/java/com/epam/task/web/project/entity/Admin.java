package com.epam.task.web.project.entity;

public class Admin extends User{

    public Admin(Long id, String name, String login, String password) {
        super(id, name, login, password);
        setRole(Role.ADMIN);
    }

}
