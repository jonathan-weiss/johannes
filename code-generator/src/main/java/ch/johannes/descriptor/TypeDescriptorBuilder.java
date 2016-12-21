package ch.johannes.descriptor;

import ch.johannes.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

public class TypeDescriptorBuilder implements DescriptorBuilder<TypeDescriptor>{

    private PackageDescriptor classPackage;

    private ClassnameDescriptor className;

    private boolean isArray = TypeDescriptor.IS_NOT_ARRAY;

    private boolean isPrimitive = TypeDescriptor.IS_NOT_PRIMITIVE;

    private List<TypeDescriptor> genericParameters = new ArrayList<>();

    private TypeDescriptorBuilder() {
        //private constructor
    }

    public static TypeDescriptorBuilder with() {
        return new TypeDescriptorBuilder();
    }

    public static TypeDescriptorBuilder with(TypeDescriptor typeDescriptor) {
        if(typeDescriptor == null) {
            return with();
        }
        return with().setTypeDescriptor(typeDescriptor);
    }

    public static TypeDescriptorBuilder with(String packageName, String className) {
        ClassnameDescriptor classnameDescriptor = ClassnameDescriptorBuilder.with(className).build();
        PackageDescriptor packageDescriptor = PackageDescriptorBuilder.with(packageName).build();
        return with()
                .setClassName(classnameDescriptor)
                .setClassPackage(packageDescriptor);
    }


    public static TypeDescriptorBuilder with(ClassnameDescriptor className) {
        return with().setClassName(className);
    }

    public TypeDescriptorBuilder setTypeDescriptor(TypeDescriptor typeDescriptor) {
        setClassName(typeDescriptor.getClassName());
        setClassPackage(typeDescriptor.getClassPackage());
        setIsArray(typeDescriptor.isArray());
        setIsPrimitive(typeDescriptor.isPrimitive());
        setGenericParameters(new ArrayList<>(typeDescriptor.getGenericParameters()));

        return this;
    }


    public TypeDescriptorBuilder setClassPackage(PackageDescriptor classPackage) {
        this.classPackage = classPackage;
        return this;
    }

    public TypeDescriptorBuilder setClassName(ClassnameDescriptor className) {
        this.className = className;
        return this;
    }

    public TypeDescriptorBuilder setIsPrimitive(boolean isPrimitive) {
        this.isPrimitive = isPrimitive;
        return this;
    }

    public TypeDescriptorBuilder setIsArray(boolean isArray) {
        this.isArray = isArray;
        return this;
    }

    public TypeDescriptorBuilder setGenericParameters(List<TypeDescriptor> genericParameters) {
        this.genericParameters = genericParameters;
        return this;
    }

    public TypeDescriptorBuilder addGenericParameters(List<TypeDescriptor> genericParameters) {
        this.genericParameters.addAll(genericParameters);
        return this;
    }

    public TypeDescriptorBuilder addGenericParameter(TypeDescriptor genericParameter) {
        this.genericParameters.add(genericParameter);
        return this;
    }


    public TypeDescriptor build() {
        return TypeDescriptor.of(classPackage, className, isArray, isPrimitive, genericParameters);
    }

}