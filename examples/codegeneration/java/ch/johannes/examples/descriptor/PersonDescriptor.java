package ch.johannes.examples.descriptor;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.FieldDescriptorBuilder;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.PackageDescriptorBuilder;
import ch.johannes.descriptor.TypeDescriptor;
import ch.johannes.descriptor.TypeDescriptorBuilder;

public class PersonDescriptor {

    public static ClassDescriptor create() {
        TypeDescriptor stringFieldType = TypeDescriptorBuilder.with("java.lang", "String").build();
        ClassDescriptor personDescriptor = ClassDescriptorBuilder.with("Person")
                .addClassField(FieldDescriptorBuilder.with("lastname", stringFieldType).build())
                .addClassField(FieldDescriptorBuilder.with("firstname", stringFieldType).build())
                .setClassPackage(PackageDescriptorBuilder.with("ch.johannes.examples.mapper.oneone").build())
                .build();

        return personDescriptor;
    }
}
