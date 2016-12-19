package ch.johannes.examples.mapper.oneone.descriptor;

import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.BeanDescriptorBuilder;
import ch.johannes.descriptor.FieldDescription;

public class PersonMappingDescription {

    public static BeanDescriptor create() {
        BeanDescriptor personTODescriptor = BeanDescriptorBuilder.with("PersonTO")
                .addTargetField(FieldDescription.of("lastname", String.class))
                .addTargetField(FieldDescription.of("firstname", String.class))
                .setTargetBeanPackage("ch.johannes.examples.mapper.oneone")
                .createBeanDescriptor();

        return personTODescriptor;
    }
}
