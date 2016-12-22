package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class PersonTOMetadata {
  public static final TypeDescriptor _TYPE_FOR_FIRSTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor FIRSTNAME = FieldDescriptor.of("firstname", _TYPE_FOR_FIRSTNAME);

  public static final TypeDescriptor _TYPE_FOR_LASTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor LASTNAME = FieldDescriptor.of("lastname", _TYPE_FOR_LASTNAME);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.oneone");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"PersonTO").addField(FIRSTNAME).addField(LASTNAME);

  public static final ClassDescriptor PERSONT_O_DESCRIPTOR = CLASS_DESCRIPTOR;
}
