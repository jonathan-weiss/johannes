package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class ProductTOMetadata {
  public static final TypeDescriptor _TYPE_FOR_ID = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor ID = FieldDescriptor.of("id", _TYPE_FOR_ID);

  public static final TypeDescriptor _TYPE_FOR_NAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor NAME = FieldDescriptor.of("name", _TYPE_FOR_NAME);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.product");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"ProductTO").addField(ID).addField(NAME);

  public static final ClassDescriptor PRODUCTT_O_DESCRIPTOR = CLASS_DESCRIPTOR;
}
