package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public class PersonMapperTestMetadata {
  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.oneone");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"PersonMapperTest");

  public static final ClassDescriptor PERSON_MAPPER_TEST_DESCRIPTOR = CLASS_DESCRIPTOR;
}
