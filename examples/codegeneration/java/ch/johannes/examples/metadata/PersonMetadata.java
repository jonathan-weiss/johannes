package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class PersonMetadata {
  public static final TypeDescriptor _TYPE_FOR_FIRSTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor FIRSTNAME = FieldDescriptor.of("firstname", _TYPE_FOR_FIRSTNAME);

  public static final TypeDescriptor _TYPE_FOR_LASTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor LASTNAME = FieldDescriptor.of("lastname", _TYPE_FOR_LASTNAME);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.oneone");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptorBuilder.with("Person").setClassPackage(PACKAGE_DESCRIPTOR).addClassField(FIRSTNAME).addClassField(LASTNAME).build();

  public static final ClassDescriptor PERSON_DESCRIPTOR = CLASS_DESCRIPTOR;
}
