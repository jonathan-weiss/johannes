package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class ProductMetadata {
  public static final TypeDescriptor _TYPE_FOR_PRODUCT_IDENTIFIER = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor PRODUCT_IDENTIFIER = FieldDescriptor.of("productIdentifier", _TYPE_FOR_PRODUCT_IDENTIFIER);

  public static final TypeDescriptor _TYPE_FOR_PRODUCT_NAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor PRODUCT_NAME = FieldDescriptor.of("productName", _TYPE_FOR_PRODUCT_NAME);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.product");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"Product").addField(PRODUCT_IDENTIFIER).addField(PRODUCT_NAME);

  public static final ClassDescriptor PRODUCT_DESCRIPTOR = CLASS_DESCRIPTOR;
}
