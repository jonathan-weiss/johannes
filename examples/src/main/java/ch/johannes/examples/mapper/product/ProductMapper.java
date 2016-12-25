package ch.johannes.examples.mapper.product;

import java.util.function.Function;

public class ProductMapper implements Function<Product, ProductTO> {

    public ProductTO apply(Product product) {
        ProductTO productTO = new ProductTO();
        productTO.setId(product.getProductIdentifier());
        productTO.setName(product.getProductName());
        return productTO;
    }
}
