package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

public class TypeDescriptor {

    private final PackageDescriptor classPackage;

    private final ClassnameDescriptor className;

    TypeDescriptor(PackageDescriptor classPackage, ClassnameDescriptor className) {
        Preconditions.checkNotNull(classPackage, "package must not be null.");
        Preconditions.checkNotNull(className, "classname must not be null.");
        this.classPackage = classPackage;
        this.className = className;
    }

    public static TypeDescriptor of(PackageDescriptor packageDescriptor, ClassnameDescriptor classnameDescriptor) {
        return new TypeDescriptor(packageDescriptor, classnameDescriptor);
    }

    public PackageDescriptor getClassPackage() {
        return classPackage;
    }

    public ClassnameDescriptor getClassName() {
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

        TypeDescriptor that = (TypeDescriptor) o;

        if (!classPackage.equals(that.classPackage)) {
            return false;
        }
        return className.equals(that.className);

    }

    @Override
    public int hashCode() {
        int result = classPackage.hashCode();
        result = 31 * result + className.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return classPackage + "|" + className;
    }
}


