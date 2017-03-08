package ch.johannes.descriptor;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class TypeDescriptorTest {

    @Test
    public void of() throws Exception {
        assertThat(TypeDescriptor.of("java.lang", "String"), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR));
        assertThat(TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String")), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR));
        assertThat(TypeDescriptor.of(String.class), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR));
    }

}