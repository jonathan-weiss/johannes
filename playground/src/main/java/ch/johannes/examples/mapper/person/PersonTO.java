package ch.johannes.examples.mapper.person;

import java.time.LocalDate;

public class PersonTO {

    private Gender gender;

    private String firstname;

    private String lastname;

    private String firstNickname;

    private LocalDate birthday;

    private AddressTO currentAddress;

    private String spouseFirstname;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstNickname() {
        return firstNickname;
    }

    public void setFirstNickname(String firstNickname) {
        this.firstNickname = firstNickname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public AddressTO getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(AddressTO currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getSpouseFirstname() {
        return spouseFirstname;
    }

    public void setSpouseFirstname(String spouseFirstname) {
        this.spouseFirstname = spouseFirstname;
    }
}
