package ch.johannes.cg;

import ch.johannes.FileUtil;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BeanSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, BeanSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        BeanSourceGenerator cg = new BeanSourceGenerator();

        String targetPackageName = "ch.johannes.examples.mapper.oneone";
        String targetClassName = "PersonTO";
        LinkedHashMap<String, Class<?>> targetFieldNames = new LinkedHashMap<>();
        targetFieldNames.put("firstname", String.class);
        targetFieldNames.put("lastname", String.class);

        String generatedCode = cg.generateCode(targetPackageName, targetClassName, targetFieldNames);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));
    }
}