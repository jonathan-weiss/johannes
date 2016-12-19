package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

public class ClassnameDescriptor {

    private final String className;

    private ClassnameDescriptor(String className) {
        this.className = className;
    }

    public static ClassnameDescriptor of(String className) {
        Preconditions.checkNotNull(className, "class name must not be null.");
        Preconditions.checkState(!className.isEmpty(), "class name must not be empty.");
        return new ClassnameDescriptor(className);
    }

    public String getClassName() {
        return className;
    }
}
