package ch.johannes.descriptor;

public class TypeDescriptor {

    private final PackageDescriptor classPackage;

    private final ClassnameDescriptor className;

    TypeDescriptor(PackageDescriptor classPackage, ClassnameDescriptor className) {
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


