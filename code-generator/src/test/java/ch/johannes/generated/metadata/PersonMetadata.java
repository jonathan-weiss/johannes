package ch.johannes.generated.metadata;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class PersonMetadata {
  public static final TypeDescriptor _TYPE_FOR_FIRSTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor FIRSTNAME = FieldDescriptor.of("firstname", _TYPE_FOR_FIRSTNAME);

  public static final TypeDescriptor _TYPE_FOR_LASTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor LASTNAME = FieldDescriptor.of("lastname", _TYPE_FOR_LASTNAME);

  public static final TypeDescriptor _TYPE_FOR_NICKNAMES = TypeDescriptor.of("java.util", "List").addGenericParameter(Descriptors.STRING_TYPE_DESCRIPTOR);

  public static final FieldDescriptor NICKNAMES = FieldDescriptor.of("nicknames", _TYPE_FOR_NICKNAMES);

  public static final TypeDescriptor _TYPE_FOR_ADDRESS_MAP = TypeDescriptor.of("java.util", "Map").addGenericParameter(Descriptors.STRING_TYPE_DESCRIPTOR).addGenericParameter(TypeDescriptor.of("ch.johannes.virtualpackage", "Address"));

  public static final FieldDescriptor ADDRESS_MAP = FieldDescriptor.of("addressMap", _TYPE_FOR_ADDRESS_MAP);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.virtualpackage");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"Person").addField(FIRSTNAME).addField(LASTNAME).addField(NICKNAMES).addField(ADDRESS_MAP);

  public static final ClassDescriptor PERSON_DESCRIPTOR = CLASS_DESCRIPTOR;
}
