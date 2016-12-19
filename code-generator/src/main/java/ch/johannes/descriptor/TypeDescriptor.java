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
}


