package ch.johannes.examples.mapper.product;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductMapperTest {

    @Test
    public void testMapper() throws Exception {
        //arrange
        Product product = new Product("1234", "Baseball");
        ProductMapper mapper = new ProductMapper();

        //act
        ProductTO productTO = mapper.apply(product);

        //assert
        assertThat(productTO.getId(), is("1234"));
        assertThat(productTO.getName(), is("Baseball"));
    }



}
