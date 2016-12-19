package ch.johannes.examples.mapper.oneone.descriptor;

import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.BeanDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public class PersonMappingDescription {

    public static BeanDescriptor create() {
        BeanDescriptor personTODescriptor = BeanDescriptorBuilder.with(ClassnameDescriptor.of("PersonTO"))
                .addTargetField(FieldDescriptor.of("lastname", String.class))
                .addTargetField(FieldDescriptor.of("firstname", String.class))
                .setTargetBeanPackage(PackageDescriptor.of("ch.johannes.examples.mapper.oneone"))
                .createBeanDescriptor();

        return personTODescriptor;
    }
}
