package ch.johannes.descriptor;

public class TypeDescriptorBuilder implements DescriptorBuilder<TypeDescriptor>{

    private PackageDescriptor classPackage;

    private ClassnameDescriptor className;

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

    public TypeDescriptor build() {
        return new TypeDescriptor(classPackage, className);
    }
}