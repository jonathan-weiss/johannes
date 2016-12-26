package ch.johannes.descriptor;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class ClassDescriptorTest {

    @Test
    public void of() throws Exception {
        assertThat(ClassDescriptor.of("java.lang", "String").getTypeDescriptor(), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR));
        assertThat(ClassDescriptor.of(PackageDescriptor.of("java.lang"), "String").getTypeDescriptor(), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR));
        assertThat(ClassDescriptor.of(String.class).getTypeDescriptor(), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR));
    }
}