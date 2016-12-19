package ch.johannes.cg;

import ch.johannes.FileUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BeanSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, BeanSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        BeanSourceGenerator cg = new BeanSourceGenerator();

        TypeDescriptor stringFieldType = TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String"));
        ClassDescriptor personTODescriptor = ClassDescriptorBuilder.with(ClassnameDescriptor.of("PersonTO"))
                .addClassField(FieldDescriptor.of("firstname", stringFieldType))
                .addClassField(FieldDescriptor.of("lastname", stringFieldType))
                .setClassPackage(PackageDescriptor.of("ch.johannes.examples.mapper.oneone"))
                .build();

        String generatedCode = cg.generateCode(personTODescriptor);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));
    }
}