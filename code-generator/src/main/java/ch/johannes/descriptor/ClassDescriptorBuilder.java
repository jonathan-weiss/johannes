package ch.johannes.descriptor;

import java.util.ArrayList;
import java.util.List;

public class ClassDescriptorBuilder implements DescriptorBuilder<ClassDescriptor> {

    private TypeDescriptorBuilder typeDescriptorBuilder = TypeDescriptorBuilder.with();

    private List<FieldDescriptor> classFields = new ArrayList<>();

    private ClassDescriptorBuilder() {
        //private constructor
    }
    public static ClassDescriptorBuilder with() {
        return new ClassDescriptorBuilder();
    }

    public static ClassDescriptorBuilder with(ClassDescriptor classDescriptor) {
        if(classDescriptor == null) {
            return with();
        }

        return with()
                .setTypeDescriptor(classDescriptor.getTypeDescriptor())
                .setClassFields(classDescriptor.getFields());
    }

    public static ClassDescriptorBuilder with(ClassnameDescriptor classnameDescriptor) {
        return with().setClassnameDescriptor(classnameDescriptor);
    }

    public static ClassDescriptorBuilder with(String classname) {
        final ClassnameDescriptor classnameDescriptor = ClassnameDescriptorBuilder.with(classname).build();
        return with().setClassnameDescriptor(classnameDescriptor);
    }



    public ClassDescriptorBuilder setTypeDescriptor(TypeDescriptor typeDescriptor) {
        this.typeDescriptorBuilder.setTypeDescriptor(typeDescriptor);
        return this;
    }

    public ClassDescriptorBuilder setClassPackage(PackageDescriptor classPackage) {
        this.typeDescriptorBuilder.setClassPackage(classPackage);
        return this;
    }

    public ClassDescriptorBuilder setClassPackage(String classPackage) {
        this.setClassPackage(PackageDescriptorBuilder.with(classPackage).build());
        return this;
    }


    public ClassDescriptorBuilder setClassnameDescriptor(ClassnameDescriptor className) {
        this.typeDescriptorBuilder.setClassName(className);
        return this;
    }

    public ClassDescriptorBuilder setClassname(String className) {
        this.setClassnameDescriptor(ClassnameDescriptorBuilder.with(className).build());
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
        return ClassDescriptor.of(typeDescriptorBuilder.build(), classFields);
    }
}