package ch.johannes.descriptor;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PackageDescriptorTest {

    @Test
    public void of() throws Exception {
        assertThat(PackageDescriptor.of("java.lang").getPackageName(), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR.getClassPackage().getPackageName()));
        assertThat(PackageDescriptor.of(String.class).getPackageName(), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR.getClassPackage().getPackageName()));
    }


    @Test
    public void getPackages() throws Exception {
        //Arrange
        PackageDescriptor packageName = PackageDescriptor.of("com.foo.bar");
        //Act
        List<String> packages = packageName.getPackages();
        //Assert
        assertThat(packages, contains("com", "foo", "bar"));
    }

    @Test
    public void getPackagesWithDefaultPackage() throws Exception {
        //Arrange
        PackageDescriptor packageName = PackageDescriptor.of("");
        //Act
        List<String> packages = packageName.getPackages();
        //Assert
        assertThat(packages.size(), is(0));
    }

}