package com.lfumard.medilabo_ui.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PatientBean {
    private long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String phoneNumber;
    private String sex;
    private String address;

    public PatientBean(long id, String firstName, String lastName, LocalDate birthdate, String phoneNumber, String sex, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.address = address;
    }

    public PatientBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PatientBean{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
