package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class CountryMetadata {
  public static final TypeDescriptor _TYPE_FOR_COUNTRY_ISO_CODE = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor COUNTRY_ISO_CODE = FieldDescriptor.of("countryIsoCode", _TYPE_FOR_COUNTRY_ISO_CODE);

  public static final TypeDescriptor _TYPE_FOR_COUNTRY_NAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor COUNTRY_NAME = FieldDescriptor.of("countryName", _TYPE_FOR_COUNTRY_NAME);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.person");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"Country").addField(COUNTRY_ISO_CODE).addField(COUNTRY_NAME);

  public static final ClassDescriptor COUNTRY_DESCRIPTOR = CLASS_DESCRIPTOR;
}
