package com.epam.task.web.project.entity;

public abstract class User implements Entity{

    private final Long id;
    private String name;
    private String login;
    private String password;
    private Role role;

    public User(Long id, String name, String login, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Long getId() {
        return id;
    }
}
