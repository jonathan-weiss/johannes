package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PackageDescriptor {

    private final String packageName;

    private PackageDescriptor(String packageName) {
        Preconditions.checkNotNull(packageName, "package must not be null. Define empty string for default package");
        this.packageName = packageName;
    }

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
}
