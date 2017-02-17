package ch.johannes.example.service.person;

import ch.johannes.example.data.dao.person.Person;
import ch.johannes.example.data.dao.person.PersonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PersonService {

    private final PersonDao personDao;

    public PersonService() {
        this(null);
    }

    @Inject
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllPerson() {
        return personDao.getAllPerson();
    }
}
