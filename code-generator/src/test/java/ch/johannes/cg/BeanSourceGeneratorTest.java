package ch.johannes.cg;

import ch.johannes.FileUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import org.junit.Test;

import static ch.johannes.descriptor.Descriptors.STRING_TYPE_DESCRIPTOR;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BeanSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, BeanSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        BeanSourceGenerator cg = new BeanSourceGenerator();

        ClassDescriptor personTODescriptor = ClassDescriptorBuilder.with("PersonTO")
                .addClassField(FieldDescriptor.of("firstname", STRING_TYPE_DESCRIPTOR))
                .addClassField(FieldDescriptor.of("lastname", STRING_TYPE_DESCRIPTOR))
                .setClassPackage("ch.johannes.examples.mapper.oneone")
                .build();

        String generatedCode = cg.generateCode(personTODescriptor);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));
    }
}