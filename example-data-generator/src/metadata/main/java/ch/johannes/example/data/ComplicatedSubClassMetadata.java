package ch.johannes.example.data;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class ComplicatedSubClassMetadata {
  public static final TypeDescriptor _TYPE_FOR_MY_SPECIAL_FIELD = TypeDescriptor.of("ch.johannes.example.data", "A");

  public static final FieldDescriptor MY_SPECIAL_FIELD = FieldDescriptor.of("mySpecialField", _TYPE_FOR_MY_SPECIAL_FIELD);

  public static final TypeDescriptor _TYPE_FOR_MY_NORMAL_FIELD = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor MY_NORMAL_FIELD = FieldDescriptor.of("myNormalField", _TYPE_FOR_MY_NORMAL_FIELD);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.example.data");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"ComplicatedSubClass").addField(MY_SPECIAL_FIELD).addField(MY_NORMAL_FIELD);

  public static final ClassDescriptor COMPLICATED_SUB_CLASS_DESCRIPTOR = CLASS_DESCRIPTOR;
}
