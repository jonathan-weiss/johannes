package ch.johannes.examples.mapper.person;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Has the following cases and conrnercases:
 * - Simple field (firstname, lastname)
 * - Primitive field (age)
 * - Enumeration (gender)
 * - List of String (nicknames)
 * - Map of Entities (addresses)
 * - Self-Reference (spouse)
 * - nested Model (Address)
 */
public class Person {

    private Gender gender;

    private String firstname;

    private String lastname;

    private List<String> nicknames;

    private LocalDate birthday;

    private Map<LocalDate, Address> addresses;

    private Person spouse;

    private Address currentAddress;

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

    public List<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(List<String> nicknames) {
        this.nicknames = nicknames;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Map<LocalDate, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<LocalDate, Address> addresses) {
        this.addresses = addresses;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }
}
