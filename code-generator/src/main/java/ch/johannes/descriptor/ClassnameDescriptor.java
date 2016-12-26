package ch.johannes.descriptor;

import ch.johannes.reflector.ClassReflector;
import com.google.common.base.Preconditions;

import java.util.List;

public class ClassnameDescriptor implements Descriptor{

    private final String className;

    /**
     * private constructor.
     * For construction, use factory method {@link #of(String)}
     */
    private ClassnameDescriptor(String className) {
        Preconditions.checkNotNull(className, "class name must not be null.");
        Preconditions.checkState(!className.isEmpty(), "class name must not be empty.");
        this.className = className;
    }

    /**
     * Factory method
     */
    public static ClassnameDescriptor of(String className) {
        return new ClassnameDescriptor(className);
    }

    /**
     * Factory method
     */
    public static ClassnameDescriptor of(Class<?> clazz) {
        return ClassReflector.reflectClass(clazz).getTypeDescriptor().getClassName();
    }


    public String getClassName() {
        return className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClassnameDescriptor that = (ClassnameDescriptor) o;

        return className.equals(that.className);

    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

    @Override
    public String toString() {
        return className;
    }
}
