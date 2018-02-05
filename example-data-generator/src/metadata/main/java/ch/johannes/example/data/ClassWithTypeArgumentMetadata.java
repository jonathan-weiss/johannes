package ch.johannes.example.data;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public class ClassWithTypeArgumentMetadata {
  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.example.data");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"ClassWithTypeArgument");

  public static final ClassDescriptor CLASS_WITH_TYPE_ARGUMENT_DESCRIPTOR = CLASS_DESCRIPTOR;
}
