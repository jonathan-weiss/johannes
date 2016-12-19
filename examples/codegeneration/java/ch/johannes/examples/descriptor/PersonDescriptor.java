package ch.johannes.examples.descriptor;

import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.BeanDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public class PersonDescriptor {

    public static BeanDescriptor create() {
        BeanDescriptor personDescriptor = BeanDescriptorBuilder.with(ClassnameDescriptor.of("Person"))
                .addTargetField(FieldDescriptor.of("lastname", String.class))
                .addTargetField(FieldDescriptor.of("firstname", String.class))
                .setTargetBeanPackage(PackageDescriptor.of("ch.johannes.examples.mapper.oneone"))
                .createBeanDescriptor();

        return personDescriptor;
    }
}
