package ch.johannes.examples.mapper.oneone;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;

public class PersonMapperTest {

    @Test
    public void testMapper() throws Exception {
        //perpare
        Person person = new Person("Johannes", "Täufer");
        PersonMapper mapper = new PersonMapper();

        //act
        PersonTO personTO = mapper.apply(person);

        assertThat(personTO.getFirstname(), is("Johannes"));
        assertThat(personTO.getLastname(), is("Täufer"));
    }



}
