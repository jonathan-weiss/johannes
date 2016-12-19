package ch.johannes.reflector;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

import java.util.List;
import java.util.stream.Collectors;

public class ClassReflector {

    public static ClassDescriptor reflect(Class<?> clazz) {
        return ClassDescriptorBuilder
                .with(ClassnameDescriptor.of(clazz.getSimpleName()))
                .setClassPackage(PackageDescriptor.of(clazz.getPackage().getName()))
                .build();
    }

    public static List<ClassDescriptor> reflectAll(List<Class<?>> listOfClasses) {
        return listOfClasses.stream().map(clazz -> reflect(clazz)).collect(Collectors.toList());
    }
}
