package  ch.johannes.descriptor;


public class ClassnameDescriptorBuilder implements DescriptorBuilder<ClassnameDescriptor> {

    private String className;

    private ClassnameDescriptorBuilder() {
        //private constructor
    }

    public static ClassnameDescriptorBuilder with() {
        return new ClassnameDescriptorBuilder();
    }

    public static ClassnameDescriptorBuilder with(ClassnameDescriptor classnameDescriptor) {
        if(classnameDescriptor == null) {
            return with();
        }

        return ClassnameDescriptorBuilder
                .with(classnameDescriptor.getClassName());
    }

    public static ClassnameDescriptorBuilder with(String className) {
        return ClassnameDescriptorBuilder
                .with()
                .setClassName(className);
    }

    public ClassnameDescriptorBuilder setClassName(String className) {
        this.className = className;
        return this;
    }

    public ClassnameDescriptor build() {
        return ClassnameDescriptor.of(className);
    }
}