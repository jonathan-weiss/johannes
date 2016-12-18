package ch.johannes.examples.mapper.oneone;

import java.util.function.Function;

public class PersonMapper implements Function<Person, PersonTO> {

    public PersonTO apply(Person person) {
        PersonTO personTO = new PersonTO();
        personTO.setFirstname(person.getFirstname());
        personTO.setLastname(person.getLastname());
        return personTO;
    }
}
