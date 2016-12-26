package ch.johannes.examples.mapper.person;

import java.util.function.Function;

public class PersonMapper implements Function<Person, PersonTO> {

    public PersonTO apply(Person person) {
        //TODO add several null-pointer checks and fallback strategies
        PersonTO personTO = new PersonTO();
        personTO.setFirstname(person.getFirstname());
        personTO.setLastname(person.getLastname());
        personTO.setGender(person.getGender());
        personTO.setBirthday(person.getBirthday());

        Address address = person.getCurrentAddress();
        final Country country = address.getCountry();
        AddressTO addressTO = new AddressTO();
        addressTO.setStreet(address.getStreet());
        addressTO.setStreetNo(address.getStreetNo());
        addressTO.setZipCode(address.getZipCode());
        addressTO.setCity(address.getCity());
        addressTO.setCountryIsoCode(country.getCountryIsoCode());
        addressTO.setCountry(country.getCountryName());
        personTO.setCurrentAddress(addressTO);

        personTO.setFirstNickname(person.getNicknames().get(0));
        personTO.setSpouseFirstname(person.getSpouse().getFirstname());
        return personTO;
    }
}
