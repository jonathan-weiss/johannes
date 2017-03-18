package ch.johannes.example.data.schema.tables;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class UserMappingMetadata {
  public static final TypeDescriptor _TYPE_FOR_SERIAL_VERSION_UID = TypeDescriptor.of("", "long").withPrimitive(true);

  public static final FieldDescriptor SERIAL_VERSION_UID = FieldDescriptor.of("serialVersionUID", _TYPE_FOR_SERIAL_VERSION_UID);

  public static final TypeDescriptor _TYPE_FOR_USER_MAPPING = TypeDescriptor.of("ch.johannes.example.data.schema.tables", "UserMapping");

  public static final FieldDescriptor USER_MAPPING = FieldDescriptor.of("USER_MAPPING", _TYPE_FOR_USER_MAPPING);

  public static final TypeDescriptor _TYPE_FOR_ACCESS_GUID = TypeDescriptor.of("org.jooq", "TableField");

  public static final FieldDescriptor ACCESS_GUID = FieldDescriptor.of("ACCESS_GUID", _TYPE_FOR_ACCESS_GUID);

  public static final TypeDescriptor _TYPE_FOR_TECHNICAL_USERNAME = TypeDescriptor.of("org.jooq", "TableField");

  public static final FieldDescriptor TECHNICAL_USERNAME = FieldDescriptor.of("TECHNICAL_USERNAME", _TYPE_FOR_TECHNICAL_USERNAME);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.example.data.schema.tables");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"UserMapping").addField(SERIAL_VERSION_UID).addField(USER_MAPPING).addField(ACCESS_GUID).addField(TECHNICAL_USERNAME);

  public static final ClassDescriptor USER_MAPPING_DESCRIPTOR = CLASS_DESCRIPTOR;
}
