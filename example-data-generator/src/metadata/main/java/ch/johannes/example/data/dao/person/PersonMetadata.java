package ch.johannes.example.data.dao.person;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class PersonMetadata {
  public static final TypeDescriptor _TYPE_FOR_GENDER = TypeDescriptor.of("ch.johannes.example.data.dao.person", "Gender");

  public static final FieldDescriptor GENDER = FieldDescriptor.of("gender", _TYPE_FOR_GENDER);

  public static final TypeDescriptor _TYPE_FOR_FIRSTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor FIRSTNAME = FieldDescriptor.of("firstname", _TYPE_FOR_FIRSTNAME);

  public static final TypeDescriptor _TYPE_FOR_LASTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor LASTNAME = FieldDescriptor.of("lastname", _TYPE_FOR_LASTNAME);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.example.data.dao.person");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"Person").addField(GENDER).addField(FIRSTNAME).addField(LASTNAME);

  public static final ClassDescriptor PERSON_DESCRIPTOR = CLASS_DESCRIPTOR;
}
