package ch.johannes.descriptor;

import java.util.ArrayList;
import java.util.List;

public class TypeDescriptorBuilder implements DescriptorBuilder<TypeDescriptor>{

    private PackageDescriptor classPackage;

    private ClassnameDescriptor className;

    private boolean isArray = false;

    private boolean isPrimitive = false;

    private List<TypeDescriptor> genericParameters = new ArrayList<>();

    public static TypeDescriptorBuilder with(ClassnameDescriptor className) {
        TypeDescriptorBuilder builder = new TypeDescriptorBuilder();
        return builder.setClassName(className);
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
        return new TypeDescriptor(classPackage, className, isArray, isPrimitive, genericParameters);
    }
}