package com.example.insurance;

public class Client {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String login;

    public Client(Integer id, String firstName, String lastName, String password, String login) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
    }

    public Integer getId() {return this.id;}
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
}
