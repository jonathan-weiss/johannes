package ch.johannes.example.data;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public class ClassWithTypeArgumentsMetadata {
  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.example.data");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"ClassWithTypeArguments");

  public static final ClassDescriptor CLASS_WITH_TYPE_ARGUMENTS_DESCRIPTOR = CLASS_DESCRIPTOR;
}
