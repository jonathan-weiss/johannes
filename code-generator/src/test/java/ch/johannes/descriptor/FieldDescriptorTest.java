package ch.johannes.descriptor;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class FieldDescriptorTest {

    @Test
    public void of() throws Exception {
        assertThat(FieldDescriptor.of("myField", String.class).getFieldType(), equalTo(Descriptors.STRING_TYPE_DESCRIPTOR));
    }

}