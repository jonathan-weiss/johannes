package ch.johannes.generated.metadata;

import ch.johannes.CollectionUtil;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class PersonMetadata {
  public static final FieldDescriptor FIRSTNAME = FieldDescriptor.of("firstname", TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String"), false, false, CollectionUtil.<TypeDescriptor>listOf()));

  public static final FieldDescriptor LASTNAME = FieldDescriptor.of("lastname", TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String"), false, false, CollectionUtil.<TypeDescriptor>listOf()));

  public static final FieldDescriptor NICKNAMES = FieldDescriptor.of("nicknames", TypeDescriptor.of(PackageDescriptor.of("java.util"), ClassnameDescriptor.of("List"), false, false, CollectionUtil.<TypeDescriptor>listOf(TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String"), false, false, CollectionUtil.<TypeDescriptor>listOf()))));

  public static final FieldDescriptor STUPID_MAP = FieldDescriptor.of("stupidMap", TypeDescriptor.of(PackageDescriptor.of("java.util"), ClassnameDescriptor.of("Map"), false, false, CollectionUtil.<TypeDescriptor>listOf(TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String"), false, false, CollectionUtil.<TypeDescriptor>listOf()),TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("Integer"), false, false, CollectionUtil.<TypeDescriptor>listOf()))));
}
