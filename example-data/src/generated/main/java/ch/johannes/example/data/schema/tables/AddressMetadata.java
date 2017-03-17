package ch.johannes.example.data.schema.tables;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class AddressMetadata {
  public static final TypeDescriptor _TYPE_FOR_SERIAL_VERSION_UID = TypeDescriptor.of("", "long").withPrimitive(true);

  public static final FieldDescriptor SERIAL_VERSION_UID = FieldDescriptor.of("serialVersionUID", _TYPE_FOR_SERIAL_VERSION_UID);

  public static final TypeDescriptor _TYPE_FOR_ADDRESS = TypeDescriptor.of("ch.johannes.example.data.schema.tables", "Address");

  public static final FieldDescriptor ADDRESS = FieldDescriptor.of("ADDRESS", _TYPE_FOR_ADDRESS);

  public static final TypeDescriptor _TYPE_FOR_ID = TypeDescriptor.of("org.jooq", "TableField");

  public static final FieldDescriptor ID = FieldDescriptor.of("ID", _TYPE_FOR_ID);

  public static final TypeDescriptor _TYPE_FOR_PERSON_ID = TypeDescriptor.of("org.jooq", "TableField");

  public static final FieldDescriptor PERSON_ID = FieldDescriptor.of("PERSON_ID", _TYPE_FOR_PERSON_ID);

  public static final TypeDescriptor _TYPE_FOR_STREET = TypeDescriptor.of("org.jooq", "TableField");

  public static final FieldDescriptor STREET = FieldDescriptor.of("STREET", _TYPE_FOR_STREET);

  public static final TypeDescriptor _TYPE_FOR_ZIP_CODE = TypeDescriptor.of("org.jooq", "TableField");

  public static final FieldDescriptor ZIP_CODE = FieldDescriptor.of("ZIP_CODE", _TYPE_FOR_ZIP_CODE);

  public static final TypeDescriptor _TYPE_FOR_CITY = TypeDescriptor.of("org.jooq", "TableField");

  public static final FieldDescriptor CITY = FieldDescriptor.of("CITY", _TYPE_FOR_CITY);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.example.data.schema.tables");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"Address").addField(SERIAL_VERSION_UID).addField(ADDRESS).addField(ID).addField(PERSON_ID).addField(STREET).addField(ZIP_CODE).addField(CITY);

  public static final ClassDescriptor ADDRESS_DESCRIPTOR = CLASS_DESCRIPTOR;
}
