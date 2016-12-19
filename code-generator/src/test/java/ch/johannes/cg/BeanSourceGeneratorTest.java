package ch.johannes.cg;

import ch.johannes.FileUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
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

        ClassDescriptor personTODescriptor = ClassDescriptorBuilder.with(ClassnameDescriptor.of("PersonTO"))
                .addClassField(FieldDescriptor.of("firstname", String.class))
                .addClassField(FieldDescriptor.of("lastname", String.class))
                .setClassPackage(PackageDescriptor.of("ch.johannes.examples.mapper.oneone"))
                .build();

        String generatedCode = cg.generateCode(personTODescriptor);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));
    }
}