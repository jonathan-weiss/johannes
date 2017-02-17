package ch.johannes.rest.endpoints.person;

import ch.johannes.example.data.dao.person.Person;

import java.util.function.Function;

public class PersonMapper implements Function<Person, PersonTO> {

    @Override
    public PersonTO apply(Person person) {
        PersonTO personTO = new PersonTO();
        personTO.setFirstname(person.getFirstname());
        personTO.setLastname(person.getLastname());
        personTO.setGender(person.getGender().name());
        return personTO;
    }
}
