package com.epam.task.web.project.entity;

public abstract class User implements Entity{

    public enum Role{
        ADMIN, CLIENT
    }

    private final Long id;
    private String name;
    private String login;
    private String password;
    private Role role;

    public User(Long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
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

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Long getId() {
        return id;
    }
}
