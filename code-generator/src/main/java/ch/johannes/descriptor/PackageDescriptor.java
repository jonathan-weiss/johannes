package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PackageDescriptor implements Descriptor {

    private final String packageName;

    /**
     * private constructor.
     * For construction, use factory method {@link #of(String)}
     */
    private PackageDescriptor(String packageName) {
        Preconditions.checkNotNull(packageName, "package must not be null. Define empty string for default package");
        this.packageName = packageName;
    }

    /**
     * Factory method
     */
    public static PackageDescriptor of(String packageName) {
        return new PackageDescriptor(packageName);
    }

    public String getPackageName() {
        return packageName;
    }

    public List<String> getPackages() {
        if(packageName == null || packageName.equals("")) {
            return Collections.emptyList();
        }
        return Arrays.asList(packageName.split("\\."));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PackageDescriptor that = (PackageDescriptor) o;

        return packageName.equals(that.packageName);

    }

    @Override
    public int hashCode() {
        return packageName.hashCode();
    }

    @Override
    public String toString() {
        return packageName;
    }
}
