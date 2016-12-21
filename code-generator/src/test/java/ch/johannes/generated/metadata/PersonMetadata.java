package ch.johannes.generated.metadata;

import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import ch.johannes.descriptor.TypeDescriptorBuilder;

public class PersonMetadata {
  public static final TypeDescriptor TYPE_FOR_FIRSTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor FIRSTNAME = FieldDescriptor.of("firstname", TYPE_FOR_FIRSTNAME);

  public static final TypeDescriptor TYPE_FOR_LASTNAME = Descriptors.STRING_TYPE_DESCRIPTOR;

  public static final FieldDescriptor LASTNAME = FieldDescriptor.of("lastname", TYPE_FOR_LASTNAME);

  public static final TypeDescriptor TYPE_FOR_NICKNAMES = TypeDescriptorBuilder.with("java.util", "List").addGenericParameter(Descriptors.STRING_TYPE_DESCRIPTOR).build();

  public static final FieldDescriptor NICKNAMES = FieldDescriptor.of("nicknames", TYPE_FOR_NICKNAMES);

  public static final TypeDescriptor TYPE_FOR_ADDRESS_MAP = TypeDescriptorBuilder.with("java.util", "Map").addGenericParameter(Descriptors.STRING_TYPE_DESCRIPTOR).addGenericParameter(TypeDescriptorBuilder.with("ch.johannes.virtualpackage", "Address").build()).build();

  public static final FieldDescriptor ADDRESS_MAP = FieldDescriptor.of("addressMap", TYPE_FOR_ADDRESS_MAP);
}
