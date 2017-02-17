package ch.johannes.example.data.dao.person;

public class Person {

    private final Gender gender;
    private final String firstname;
    private final String lastname;

    public Person(Gender gender, String firstname, String lastname) {
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
