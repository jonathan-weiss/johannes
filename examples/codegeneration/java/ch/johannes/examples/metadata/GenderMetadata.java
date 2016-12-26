package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class GenderMetadata {
  public static final TypeDescriptor _TYPE_FOR_MALE = TypeDescriptor.of("ch.johannes.examples.mapper.person", "Gender");

  public static final FieldDescriptor MALE = FieldDescriptor.of("MALE", _TYPE_FOR_MALE);

  public static final TypeDescriptor _TYPE_FOR_FEMALE = TypeDescriptor.of("ch.johannes.examples.mapper.person", "Gender");

  public static final FieldDescriptor FEMALE = FieldDescriptor.of("FEMALE", _TYPE_FOR_FEMALE);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.person");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"Gender").addField(MALE).addField(FEMALE);

  public static final ClassDescriptor GENDER_DESCRIPTOR = CLASS_DESCRIPTOR;
}
