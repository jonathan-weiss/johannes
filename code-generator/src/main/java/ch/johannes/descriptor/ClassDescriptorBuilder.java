package ch.johannes.descriptor;

import java.util.ArrayList;
import java.util.List;

public class ClassDescriptorBuilder {

    private TypeDescriptorBuilder typeDescriptorBuilder = new TypeDescriptorBuilder();

    private List<FieldDescriptor> classFields = new ArrayList<>();

    public static ClassDescriptorBuilder with(ClassnameDescriptor beanName) {
        ClassDescriptorBuilder builder = new ClassDescriptorBuilder();
        return builder.setBeanName(beanName);
    }

    public ClassDescriptorBuilder setClassPackage(PackageDescriptor classPackage) {
        this.typeDescriptorBuilder.setClassPackage(classPackage);
        return this;
    }

    public ClassDescriptorBuilder setBeanName(ClassnameDescriptor className) {
        this.typeDescriptorBuilder.setClassName(className);
        return this;
    }

    public ClassDescriptorBuilder setClassFields(List<FieldDescriptor> classFields) {
        this.classFields = classFields;
        return this;
    }

    public ClassDescriptorBuilder addClassFields(List<FieldDescriptor> classFields) {
        this.classFields.addAll(classFields);
        return this;
    }

    public ClassDescriptorBuilder addClassField(FieldDescriptor classField) {
        this.classFields.add(classField);
        return this;
    }

    public ClassDescriptor build() {
        return new ClassDescriptor(typeDescriptorBuilder.build(), classFields);
    }
}