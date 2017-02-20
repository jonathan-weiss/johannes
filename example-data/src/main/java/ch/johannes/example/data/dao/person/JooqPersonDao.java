package ch.johannes.example.data.dao.person;

import ch.johannes.example.data.jooq.JooqExecutor;
import ch.johannes.example.data.schema.enums.GenderTypeEnum;
import ch.johannes.example.data.schema.tables.records.PersonRecord;
import org.jooq.Result;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static ch.johannes.example.data.schema.tables.Person.PERSON;

@ApplicationScoped
public class JooqPersonDao implements PersonDao {

    private final JooqExecutor jooqExecutor;

    @Inject
    public JooqPersonDao(JooqExecutor jooqExecutor) {
        this.jooqExecutor = jooqExecutor;
    }

    @Override
    public List<Person> getAllPerson() {
        final Result<PersonRecord> personRecords = jooqExecutor.execute(dslContext -> dslContext.selectFrom(PERSON).fetch());
        return personRecords.stream().map(this::convertPerson).collect(Collectors.toList());
    }

    private Person convertPerson(PersonRecord personRecord) {
        Person person = new Person(convertGender(personRecord.getGender()), personRecord.getFirstname(), personRecord.getLastname());
        return person;
    }

    private Gender convertGender(GenderTypeEnum genderTypeEnum) {
        return Gender.valueOf(genderTypeEnum.getLiteral());
    }
}
