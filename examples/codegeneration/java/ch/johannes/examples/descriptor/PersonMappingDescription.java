package ch.johannes.examples.descriptor;

import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.BeanDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public class PersonMappingDescription {

    public static BeanDescriptor create() {
        BeanDescriptor personTODescriptor = BeanDescriptorBuilder.with(ClassnameDescriptor.of("MyPersonTO"))
                .addBeanField(FieldDescriptor.of("lastname", String.class))
                .addBeanField(FieldDescriptor.of("firstname", String.class))
                .setBeanPackage(PackageDescriptor.of("ch.johannes.examples.mapper.oneone"))
                .createBeanDescriptor();

        return personTODescriptor;
    }
}
