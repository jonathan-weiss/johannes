package ch.johannes.examples.mapper.person;

import ch.johannes.CollectionUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PersonMapperTest {

    @Test
    public void testMapper() throws Exception {
        //arrange
        Country switzerland = new Country();
        switzerland.setCountryIsoCode("CH");
        switzerland.setCountryName("Switzerland");


        Address addressTill2014 = new Address();
        addressTill2014.setCity("Zürich");
        addressTill2014.setZipCode("8002");
        addressTill2014.setStreet("Bederstrasse");
        addressTill2014.setStreetNo(1);
        addressTill2014.setCountry(switzerland);

        Address addressTill2016 = new Address();
        addressTill2016.setCity("Zürich");
        addressTill2016.setZipCode("8032");
        addressTill2016.setStreet("Merkurstrasse");
        addressTill2016.setStreetNo(43);
        addressTill2016.setCountry(switzerland);

        Map<LocalDate, Address> addressMapJohannes = new LinkedHashMap<>();
        addressMapJohannes.put(LocalDate.of(2014, 11, 1), addressTill2014);
        addressMapJohannes.put(LocalDate.of(2016, 12, 31), addressTill2016);

        Map<LocalDate, Address> addressMapLydia = new LinkedHashMap<>();
        addressMapLydia.put(LocalDate.of(2016, 12, 31), addressTill2016);


        Person personLydia = new Person();
        personLydia.setFirstname("Lydia");
        personLydia.setLastname("Fitzi");
        personLydia.setBirthday(LocalDate.of(1982, 8, 31));
        personLydia.setGender(Gender.FEMALE);
        personLydia.setNicknames(CollectionUtil.listOf("Lydi", "Li"));
        personLydia.setAddresses(addressMapLydia);
        personLydia.setCurrentAddress(addressTill2016);

        Person personJohannes = new Person();
        personJohannes.setFirstname("Johannes");
        personJohannes.setLastname("Fitzi");
        personJohannes.setBirthday(LocalDate.of(1981, 12, 31));
        personJohannes.setGender(Gender.MALE);
        personJohannes.setNicknames(CollectionUtil.listOf("Jonny", "John"));
        personJohannes.setAddresses(addressMapJohannes);
        personJohannes.setCurrentAddress(addressTill2016);
        personJohannes.setSpouse(personLydia);

        personLydia.setSpouse(personJohannes);

        PersonMapper mapper = new PersonMapper();

        //act
        PersonTO personTO = mapper.apply(personJohannes);

        //assert
        assertThat(personTO.getFirstname(), is("Johannes"));
        assertThat(personTO.getLastname(), is("Fitzi"));
        assertThat(personTO.getGender(), is(Gender.MALE));
        assertThat(personTO.getBirthday(), is(LocalDate.of(1981, 12, 31)));
        assertThat(personTO.getFirstNickname(), is("Jonny"));
        assertThat(personTO.getSpouseFirstname(), is("Lydia"));
        assertThat(personTO.getCurrentAddress().getStreet(), is("Merkurstrasse"));
        assertThat(personTO.getCurrentAddress().getStreetNo(), is(43));
        assertThat(personTO.getCurrentAddress().getZipCode(), is("8032"));
        assertThat(personTO.getCurrentAddress().getCity(), is("Zürich"));
        assertThat(personTO.getCurrentAddress().getCountryIsoCode(), is("CH"));
        assertThat(personTO.getCurrentAddress().getCountry(), is("Switzerland"));
    }



}
