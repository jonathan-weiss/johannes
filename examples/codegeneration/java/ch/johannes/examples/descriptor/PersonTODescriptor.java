package ch.johannes.examples.descriptor;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public class PersonTODescriptor {

    public static ClassDescriptor create() {
        ClassDescriptor personTODescriptor = ClassDescriptorBuilder.with(ClassnameDescriptor.of("MyPersonTO"))
                .addClassField(FieldDescriptor.of("lastname", String.class))
                .addClassField(FieldDescriptor.of("firstname", String.class))
                .setClassPackage(PackageDescriptor.of("ch.johannes.examples.mapper.oneone"))
                .build();

        return personTODescriptor;
    }
}
