package ch.johannes.cg;

import ch.johannes.FileUtil;
import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.BeanDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BeanSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, BeanSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        BeanSourceGenerator cg = new BeanSourceGenerator();

        BeanDescriptor personTODescriptor = BeanDescriptorBuilder.with(ClassnameDescriptor.of("PersonTO"))
                .addTargetField(FieldDescriptor.of("firstname", String.class))
                .addTargetField(FieldDescriptor.of("lastname", String.class))
                .setTargetBeanPackage(PackageDescriptor.of("ch.johannes.examples.mapper.oneone"))
                .createBeanDescriptor();

        String generatedCode = cg.generateCode(personTODescriptor);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));
    }
}