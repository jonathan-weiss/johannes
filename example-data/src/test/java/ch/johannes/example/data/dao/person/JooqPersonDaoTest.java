package ch.johannes.example.data.dao.person;

import ch.johannes.example.data.jooq.JOOQCallback;
import ch.johannes.example.data.jooq.JOOQVoidCallback;
import ch.johannes.example.data.jooq.JooqExecutor;
import ch.johannes.example.data.schema.enums.GenderTypeEnum;
import ch.johannes.example.data.schema.tables.records.PersonRecord;
import org.hamcrest.CoreMatchers;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SelectWhereStep;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static ch.johannes.example.data.schema.tables.Person.PERSON;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JooqPersonDaoTest {

    @Test
    public void getAllPerson() throws Exception {
        //arrange
        DSLContext dslContext = mock(DSLContext.class);
        SelectWhereStep<PersonRecord> selectWhereStep = mock(SelectWhereStep.class);
        Result<PersonRecord> result = mock(Result.class);
        PersonRecord personRecord = new PersonRecord(1, UUID.randomUUID(), "Vera", "Hinterseen", GenderTypeEnum.FEMALE);
        Stream<PersonRecord> streamResult = Collections.singleton(personRecord).stream();

        when(dslContext.selectFrom(PERSON)).thenReturn(selectWhereStep);
        when(selectWhereStep.fetch()).thenReturn(result);
        when(result.stream()).thenReturn(streamResult);


        JooqExecutor jooqExecutor = new MockJooqExecutor(dslContext);
        JooqPersonDao jooqPersonDao = new JooqPersonDao(jooqExecutor);


        //act
        final List<Person> allPerson = jooqPersonDao.getAllPerson();

        //assert
        verify(result, times(1)).stream();
        verify(dslContext, times(1)).selectFrom(PERSON);
        verify(selectWhereStep, times(1)).fetch();
        verify(result, times(1)).stream();

        assertThat(allPerson.size(), CoreMatchers.is(1));
        assertThat(allPerson.get(0).getGender(), CoreMatchers.is(Gender.FEMALE));
        assertThat(allPerson.get(0).getFirstname(), CoreMatchers.is("Vera"));
        assertThat(allPerson.get(0).getLastname(), CoreMatchers.is("Hinterseen"));

    }

    private class MockJooqExecutor implements JooqExecutor {

        private DSLContext dslContext;

        public MockJooqExecutor(DSLContext dslContext) {
            this.dslContext = dslContext;
        }

        @Override
        public <T> T execute(JOOQCallback<T> callback) {
            return callback.execute(dslContext);
        }

        @Override
        public <T> T execute(JOOQCallback<T> callback, Connection connection) throws SQLException {
            return null;
        }

        @Override
        public void executeWithoutResult(JOOQVoidCallback callback) {

        }

        @Override
        public void executeWithoutResult(JOOQVoidCallback callback, Connection connection) throws SQLException {

        }
    }

}