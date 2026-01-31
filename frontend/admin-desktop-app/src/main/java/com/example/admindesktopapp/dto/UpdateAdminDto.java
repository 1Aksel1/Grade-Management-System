package com.example.admindesktopapp.dto;

public class UpdateAdminDto {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String telephoneNumber;
    private String dateOfBirth;

    public UpdateAdminDto() {
    }

    public UpdateAdminDto(String name, String surname, String username, String email, String telephoneNumber, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
