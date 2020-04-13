package com.restproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String surname;
    private String name;
    private String patronymic;
    private String dateofbirth;
    private String passportseries;
    private String passportnumber;
    private String passportfrom;
    private String passportdate;
    private String placeofbirth;
    private String city;
    private String addressreal;
    private String mobilephone;
    private String email;
    private String workplace;
    private String position;
    private String addressofficial;
    private String maritalstatus;
    private String citizenship;
    private String disability;
    private boolean pensioner;
    private String income;
    private boolean dutybound;

    @Override
    public String toString() {
        return "User{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateofbirth='" + dateofbirth + '\'' +
                ", passportseries='" + passportseries + '\'' +
                ", passportnumber='" + passportnumber + '\'' +
                ", passportfrom='" + passportfrom + '\'' +
                ", passportdate='" + passportdate + '\'' +
                ", placeofbirth='" + placeofbirth + '\'' +
                ", city='" + city + '\'' +
                ", addressreal='" + addressreal + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", email='" + email + '\'' +
                ", workplace='" + workplace + '\'' +
                ", position='" + position + '\'' +
                ", addressofficial='" + addressofficial + '\'' +
                ", maritalstatus='" + maritalstatus + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", disability='" + disability + '\'' +
                ", pensioner='" + pensioner + '\'' +
                ", income='" + income + '\'' +
                ", dutybound='" + dutybound + '\'' +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPassportseries() {
        return passportseries;
    }

    public void setPassportseries(String passportseries) {
        this.passportseries = passportseries;
    }

    public String getPassportnumber() {
        return passportnumber;
    }

    public void setPassportnumber(String passportnumber) {
        this.passportnumber = passportnumber;
    }

    public String getPassportfrom() {
        return passportfrom;
    }

    public void setPassportfrom(String passportfrom) {
        this.passportfrom = passportfrom;
    }

    public String getPassportdate() {
        return passportdate;
    }

    public void setPassportdate(String passportdate) {
        this.passportdate = passportdate;
    }

    public String getPlaceofbirth() {
        return placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        this.placeofbirth = placeofbirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressreal() {
        return addressreal;
    }

    public void setAddressreal(String addressreal) {
        this.addressreal = addressreal;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddressofficial() {
        return addressofficial;
    }

    public void setAddressofficial(String addressofficial) {
        this.addressofficial = addressofficial;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public boolean getPensioner() {
        return pensioner;
    }

    public void setPensioner(boolean pensioner) {
        this.pensioner = pensioner;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public boolean getDutybound() {
        return dutybound;
    }

    public void setDutybound(boolean dutybound) {
        this.dutybound = dutybound;
    }
}
