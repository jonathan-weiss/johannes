package ch.johannes.examples.mapstruct;

import ch.johannes.examples.mapper.person.Address;
import ch.johannes.examples.mapper.person.AddressTO;
import ch.johannes.examples.mapper.person.Person;
import ch.johannes.examples.mapper.person.PersonTO;
import ch.johannes.examples.mapper.pet.Pet;
import ch.johannes.examples.mapper.pet.PetTO;
import ch.johannes.examples.mapper.product.Product;
import ch.johannes.examples.mapper.product.ProductTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper
public interface MapStructMapper {

    @Mappings({
            @Mapping(source = "productIdentifier", target = "id"),
            @Mapping(source = "productName", target = "name")
    })
    ProductTO productToProductTO(Product product);

    PetTO petToPetTO(Pet pet);

    @Mappings({
    })
    PersonTO personToPersonTO(Person person);

    default void personToPersonTOAdditions(Person person, @MappingTarget PersonTO personTO) {
        personTO.setFirstNickname(person.getNicknames().get(0));
    };


    @Mappings({
            @Mapping(source = "country.countryName", target = "country"),
            @Mapping(source = "country.countryIsoCode", target = "countryIsoCode")
    })
    AddressTO addressToAddressTO(Address address);



}
