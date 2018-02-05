package ch.johannes.example.data;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class ClassWithTypeParametersMetadata {
  public static final TypeDescriptor _TYPE_FOR_MY_FLOATING_SUPER_FIELD = Descriptors.LIST_TYPE_DESCRIPTOR;

  public static final FieldDescriptor MY_FLOATING_SUPER_FIELD = FieldDescriptor.of("myFloatingSuperField", _TYPE_FOR_MY_FLOATING_SUPER_FIELD);

  public static final TypeDescriptor _TYPE_FOR_MY_SPECIAL_FIELD = TypeDescriptor.of("ch.johannes.example.data", "Y");

  public static final FieldDescriptor MY_SPECIAL_FIELD = FieldDescriptor.of("mySpecialField", _TYPE_FOR_MY_SPECIAL_FIELD);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.example.data");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"ClassWithTypeParameters").addField(MY_FLOATING_SUPER_FIELD).addField(MY_SPECIAL_FIELD);

  public static final ClassDescriptor CLASS_WITH_TYPE_PARAMETERS_DESCRIPTOR = CLASS_DESCRIPTOR;
}
