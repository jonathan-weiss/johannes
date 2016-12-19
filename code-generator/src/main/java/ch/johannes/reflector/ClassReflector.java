package ch.johannes.reflector;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class ClassReflector {

    public static ClassDescriptor reflectClass(Class<?> clazz) {
        TypeDescriptor typeDescriptor = reflectType(clazz);
        ClassDescriptorBuilder builder = ClassDescriptorBuilder
                .with(typeDescriptor.getClassName())
                .setClassPackage(typeDescriptor.getClassPackage());

        for (Field field : clazz.getDeclaredFields()) {
            builder.addClassField(reflectField(field));
        }
        return builder.build();
    }

    public static TypeDescriptor reflectType(Class<?> clazz) {
        return TypeDescriptor.of(reflectPackage(clazz), reflectClassname(clazz));
    }

    public static ClassnameDescriptor reflectClassname(Class<?> clazz) {
        return ClassnameDescriptor.of(clazz.getSimpleName());
    }

    public static PackageDescriptor reflectPackage(Class<?> clazz) {
        if(clazz.isPrimitive()) {
            return PackageDescriptor.of("");
        } else {
            return PackageDescriptor.of(clazz.getPackage().getName());
        }
    }



    public static FieldDescriptor reflectField(Field field) {
        TypeDescriptor fieldTypeDescriptor = reflectType(field.getType());
        return FieldDescriptor.of(field.getName(), fieldTypeDescriptor);
    }


    public static List<ClassDescriptor> reflectAllClasses(List<Class<?>> listOfClasses) {
        return listOfClasses.stream().map(clazz -> reflectClass(clazz)).collect(Collectors.toList());
    }
}
