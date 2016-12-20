package ch.johannes.reflector;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClassReflector {

    public static ClassDescriptor reflectClass(Class<?> clazz) {
        TypeDescriptor typeDescriptor = reflectType(clazz);
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

    public static TypeDescriptor reflectFieldType(Field field) {
        final Type genericType = field.getGenericType();
        return createTypeDescriptorForType(genericType);
    }

    private static TypeDescriptor createTypeDescriptorForType(Type type) {
        if (type instanceof Class) {
            return reflectType((Class)type);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;


            List<TypeDescriptor> genericParameters = new ArrayList<>();
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            for (Type typeArgument : typeArguments) {
                final TypeDescriptor typeDescriptorForGenericParameter = createTypeDescriptorForType(typeArgument);
                genericParameters.add(typeDescriptorForGenericParameter);
            }
            final TypeDescriptor rawType = createTypeDescriptorForType(parameterizedType.getRawType());

            return TypeDescriptor.of(rawType.getClassPackage(), rawType.getClassName(), rawType.isArray(), rawType.isPrimitive(), genericParameters);
        } else {
            throw new IllegalStateException("type conversion not supported yet" + type);
        }

    }

    public static TypeDescriptor reflectType(Class<?> clazz) {
        return TypeDescriptor.of(reflectPackage(clazz), reflectClassname(clazz), clazz.isArray(), clazz.isPrimitive(), Collections.emptyList());
    }

    public static ClassnameDescriptor reflectClassname(Class<?> clazz) {
        return ClassnameDescriptor.of(clazz.getSimpleName());
    }

    public static PackageDescriptor reflectPackage(Class<?> clazz) {
        if(clazz.isPrimitive() || clazz.isArray()) {
            return PackageDescriptor.of("");
        } else {
            return PackageDescriptor.of(clazz.getPackage().getName());
        }
    }



    public static FieldDescriptor reflectField(Field field) {
        TypeDescriptor fieldTypeDescriptor = reflectFieldType(field);
        return FieldDescriptor.of(field.getName(), fieldTypeDescriptor);
    }


    public static List<ClassDescriptor> reflectAllClasses(List<Class<?>> listOfClasses) {
        return listOfClasses.stream().map(clazz -> reflectClass(clazz)).collect(Collectors.toList());
    }
}
