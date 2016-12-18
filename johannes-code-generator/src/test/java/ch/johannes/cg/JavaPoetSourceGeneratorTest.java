package ch.johannes.cg;

import ch.johannes.FileUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class JavaPoetSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, JavaPoetSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        JavaPoetSourceGenerator cg = new JavaPoetSourceGenerator();
        assertThat(cg.generateCode(), equalTo(expectedJavaSourceText));
    }
}