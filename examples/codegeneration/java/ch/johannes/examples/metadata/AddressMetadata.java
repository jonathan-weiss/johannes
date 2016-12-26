package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class AddressMetadata {
  public static final TypeDescriptor _TYPE_FOR_STREET = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor STREET = FieldDescriptor.of("street", _TYPE_FOR_STREET);

  public static final TypeDescriptor _TYPE_FOR_STREET_NO = TypeDescriptor.of("", "int").withPrimitive(true);

  public static final FieldDescriptor STREET_NO = FieldDescriptor.of("streetNo", _TYPE_FOR_STREET_NO);

  public static final TypeDescriptor _TYPE_FOR_CITY = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor CITY = FieldDescriptor.of("city", _TYPE_FOR_CITY);

  public static final TypeDescriptor _TYPE_FOR_ZIP_CODE = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor ZIP_CODE = FieldDescriptor.of("zipCode", _TYPE_FOR_ZIP_CODE);

  public static final TypeDescriptor _TYPE_FOR_COUNTRY = TypeDescriptor.of("ch.johannes.examples.mapper.person", "Country");

  public static final FieldDescriptor COUNTRY = FieldDescriptor.of("country", _TYPE_FOR_COUNTRY);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.person");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"Address").addField(STREET).addField(STREET_NO).addField(CITY).addField(ZIP_CODE).addField(COUNTRY);

  public static final ClassDescriptor ADDRESS_DESCRIPTOR = CLASS_DESCRIPTOR;
}
