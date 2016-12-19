package ch.johannes.descriptor;

public class PackageDescriptor {

    private final String packageName;

    private PackageDescriptor(String packageName) {
        this.packageName = packageName;
    }

    public static PackageDescriptor of(String packageName) {
        return new PackageDescriptor(packageName);
    }

    public String getPackageName() {
        return packageName;
    }
}
