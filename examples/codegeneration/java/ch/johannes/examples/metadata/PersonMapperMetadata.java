package ch.johannes.examples.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.PackageDescriptor;

public class PersonMapperMetadata {
  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.examples.mapper.oneone");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptorBuilder.with("PersonMapper").setClassPackage(PACKAGE_DESCRIPTOR).build();

  public static final ClassDescriptor PERSON_MAPPER_DESCRIPTOR = CLASS_DESCRIPTOR;
}
