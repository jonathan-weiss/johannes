package ch.johannes.descriptor;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class ClassnameDescriptorTest {

    @Test
    public void of() throws Exception {
        assertThat(ClassnameDescriptor.of("String").getClassName(), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR.getClassName().getClassName()));
        assertThat(ClassnameDescriptor.of(String.class).getClassName(), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR.getClassName().getClassName()));
    }

}