package ch.johannes.cg;

import ch.johannes.FileUtil;
import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.BeanDescriptorBuilder;
import ch.johannes.descriptor.FieldDescriptor;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BeanSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, BeanSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        BeanSourceGenerator cg = new BeanSourceGenerator();

        BeanDescriptor personTODescriptor = BeanDescriptorBuilder.with("PersonTO")
                .addTargetField(FieldDescriptor.of("firstname", String.class))
                .addTargetField(FieldDescriptor.of("lastname", String.class))
                .setTargetBeanPackage("ch.johannes.examples.mapper.oneone")
                .createBeanDescriptor();

        String generatedCode = cg.generateCode(personTODescriptor);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));
    }
}