package ch.johannes.cg;

import ch.johannes.FileUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.plan.BeanSourcePlan;
import org.junit.Test;

import static ch.johannes.descriptor.Descriptors.STRING_TYPE_DESCRIPTOR;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BeanSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, BeanSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        BeanSourceGenerator cg = new BeanSourceGenerator();

        ClassDescriptor personTODescriptor = ClassDescriptor.of("ch.johannes.examples.mapper.oneone", "PersonTO")
                .addField(FieldDescriptor.of("firstname", STRING_TYPE_DESCRIPTOR))
                .addField(FieldDescriptor.of("lastname", STRING_TYPE_DESCRIPTOR));

        BeanSourcePlan plan = new BeanSourcePlan(personTODescriptor);

        String generatedCode = cg.generateCode(plan);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));
    }
}