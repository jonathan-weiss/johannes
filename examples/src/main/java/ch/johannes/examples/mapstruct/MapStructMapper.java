package ch.johannes.examples.mapstruct;

import ch.johannes.examples.mapper.person.Person;
import ch.johannes.examples.mapper.person.PersonTO;
import ch.johannes.examples.mapper.product.Product;
import ch.johannes.examples.mapper.product.ProductTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface MapStructMapper {

    @Mappings({
            @Mapping(source = "productIdentifier", target = "id"),
            @Mapping(source = "productName", target = "name")
    })
    ProductTO productToProductTO(Product product);

}
