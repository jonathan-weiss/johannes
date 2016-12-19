package ch.johannes.descriptor;

public class ClassnameDescriptor {

    private final String className;

    private ClassnameDescriptor(String className) {
        this.className = className;
    }

    public static ClassnameDescriptor of(String className) {
        return new ClassnameDescriptor(className);
    }

    public String getClassName() {
        return className;
    }
}
