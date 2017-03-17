package ch.johannes.example.data.schema.tables.records;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

public class PersonRecordMetadata {
  public static final TypeDescriptor _TYPE_FOR_SERIAL_VERSION_UID = TypeDescriptor.of("", "long").withPrimitive(true);

  public static final FieldDescriptor SERIAL_VERSION_UID = FieldDescriptor.of("serialVersionUID", _TYPE_FOR_SERIAL_VERSION_UID);

  public static final PackageDescriptor PACKAGE_DESCRIPTOR = PackageDescriptor.of("ch.johannes.example.data.schema.tables.records");

  public static final ClassDescriptor CLASS_DESCRIPTOR = ClassDescriptor.of(PACKAGE_DESCRIPTOR,"PersonRecord").addField(SERIAL_VERSION_UID);

  public static final ClassDescriptor PERSON_RECORD_DESCRIPTOR = CLASS_DESCRIPTOR;
}
