package ch.johannes.examples.mapper.oneone.descriptor;

import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.BeanDescriptorBuilder;
import ch.johannes.descriptor.FieldDescriptor;

public class PersonMappingDescription {

    public static BeanDescriptor create() {
        BeanDescriptor personTODescriptor = BeanDescriptorBuilder.with("PersonTO")
                .addTargetField(FieldDescriptor.of("lastname", String.class))
                .addTargetField(FieldDescriptor.of("firstname", String.class))
                .setTargetBeanPackage("ch.johannes.examples.mapper.oneone")
                .createBeanDescriptor();

        return personTODescriptor;
    }
}
