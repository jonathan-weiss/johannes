package ch.johannes.examples.descriptor;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptorBuilder;

public class PersonTODescriptor {

    public static ClassDescriptor create() {
        ClassDescriptor personTODescriptor = ClassDescriptorBuilder.with(PersonDescriptor.create())
                .setClassnameDescriptor(ClassnameDescriptorBuilder.with("MyPersonTO").build())
                .build();

        return personTODescriptor;
    }
}
