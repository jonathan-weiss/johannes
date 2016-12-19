package ch.johannes.examples.descriptor;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public class PersonDescriptor {

    public static ClassDescriptor create() {
        ClassDescriptor personDescriptor = ClassDescriptorBuilder.with(ClassnameDescriptor.of("Person"))
                .addClassField(FieldDescriptor.of("lastname", String.class))
                .addClassField(FieldDescriptor.of("firstname", String.class))
                .setClassPackage(PackageDescriptor.of("ch.johannes.examples.mapper.oneone"))
                .build();

        return personDescriptor;
    }
}
