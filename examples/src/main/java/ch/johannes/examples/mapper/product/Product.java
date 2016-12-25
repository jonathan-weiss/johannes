package ch.johannes.examples.mapper.product;

public class Product {

    private final String productIdentifier;

    private final String productName;

    public Product(String productIdentifier, String productName) {
        this.productIdentifier = productIdentifier;
        this.productName = productName;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public String getProductName() {
        return productName;
    }
}
