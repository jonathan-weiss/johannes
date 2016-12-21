package ch.johannes.descriptor;

public class PackageDescriptorBuilder implements DescriptorBuilder<PackageDescriptor>{

    private String packageName;

    private PackageDescriptorBuilder() {
        //private constructor
    }

    public static PackageDescriptorBuilder with() {
        return new PackageDescriptorBuilder();
    }

    public static PackageDescriptorBuilder with(PackageDescriptor packageDescriptor) {
        if(packageDescriptor == null) {
            return with();
        }

        return PackageDescriptorBuilder
                .with(packageDescriptor.getPackageName());
    }

    public static PackageDescriptorBuilder with(String packageName) {
        return PackageDescriptorBuilder
                .with()
                .setPackageName(packageName);
    }

    public PackageDescriptorBuilder setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public PackageDescriptor build() {
        return PackageDescriptor.of(packageName);
    }
}