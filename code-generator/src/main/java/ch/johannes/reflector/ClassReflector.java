package ch.johannes.reflector;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.ClassnameDescriptorBuilder;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.FieldDescriptorBuilder;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.PackageDescriptorBuilder;
import ch.johannes.descriptor.TypeDescriptor;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClassReflector {

    public static List<ClassDescriptor> reflectAllClasses(List<Class<?>> listOfClasses) {
        return listOfClasses.stream().map(ClassReflector::reflectClass).collect(Collectors.toList());
    }


    public static ClassDescriptor reflectClass(Class<?> clazz) {
        TypeDescriptor typeDescriptor = reflectClassAsTypeDescriptor(clazz);
        ClassDescriptorBuilder builder = ClassDescriptorBuilder
                .with(typeDescriptor.getClassName())
                .setClassPackage(typeDescriptor.getClassPackage());

        for (Field field : clazz.getDeclaredFields()) {
            if(!field.isSynthetic()) { //filter compile generated fields like this$0 for inner classes
                builder.addClassField(reflectField(field));
            }
        }
        return builder.build();
    }

    public static TypeDescriptor reflectClassAsTypeDescriptor(Class<?> clazz) {
        Class<?> typeClass = getComponentTypeIfArray(clazz);
        return TypeDescriptor.of(reflectPackage(typeClass), reflectClassname(typeClass), clazz.isArray(), typeClass.isPrimitive(), Collections.emptyList());
    }

    private static Class<?> getComponentTypeIfArray(Class<?> clazz) {
        return clazz.isArray() ? clazz.getComponentType() : clazz;
    }

    private static FieldDescriptor reflectField(Field field) {
        return FieldDescriptorBuilder
                .with(field.getName())
                .setFieldType(reflectFieldType(field))
                .build();
    }

    private static TypeDescriptor reflectFieldType(Field field) {
        final Type genericType = field.getGenericType();
        return reflectType(genericType);
    }

    private static TypeDescriptor reflectType(Type type) {
        if (type instanceof Class) {
            return reflectClassAsTypeDescriptor((Class)type);
        } else if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            final TypeDescriptor rawType = reflectType(genericArrayType.getGenericComponentType()); //recursive

            return TypeDescriptor.of(rawType.getClassPackage(), rawType.getClassName(), true, rawType.isPrimitive(), rawType.getGenericParameters());
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;


            List<TypeDescriptor> genericParameters = new ArrayList<>();
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            for (Type typeArgument : typeArguments) {
                final TypeDescriptor typeDescriptorForGenericParameter = reflectType(typeArgument); //recursive
                genericParameters.add(typeDescriptorForGenericParameter);
            }
            final TypeDescriptor rawType = reflectType(parameterizedType.getRawType()); //recursive

            return TypeDescriptor.of(rawType.getClassPackage(), rawType.getClassName(), rawType.isArray(), rawType.isPrimitive(), genericParameters);
        } else {
            throw new IllegalStateException("type conversion not supported yet" + type);
        }

    }

    private static ClassnameDescriptor reflectClassname(Class<?> clazz) {
        rejectArrayClass(clazz);
        return ClassnameDescriptorBuilder.with(clazz.getSimpleName()).build();
    }

    private static PackageDescriptor reflectPackage(Class<?> clazz) {
        rejectArrayClass(clazz);
        if(clazz.isPrimitive()) {
            return PackageDescriptorBuilder.with("").build();
        } else {
            return PackageDescriptorBuilder.with(clazz.getPackage().getName()).build();
        }
    }

    private static void rejectArrayClass(Class<?> clazz) {
        if(clazz.isArray()) {
            throw new IllegalStateException("Array classes are not permitted. Please pass the composed type of array the array. " + clazz);
        }

    }
}
