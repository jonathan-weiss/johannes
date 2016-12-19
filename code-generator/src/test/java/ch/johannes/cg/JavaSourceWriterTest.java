package ch.johannes.cg;

import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class JavaSourceWriterTest {

    @Test
    public void getJavaSourceFilePath() throws Exception {
        //Arrange
        Path basePath = Paths.get("base");
        PackageDescriptor packageName = PackageDescriptor.of("com.foo.bar");
        ClassnameDescriptor className = ClassnameDescriptor.of("MyClass");
        JavaSourceWriter javaSourceWriter = new JavaSourceWriter(basePath);

        //Act
        Path javaSourceFilePath = javaSourceWriter.getJavaSourceFilePath(packageName, className);

        //Assert
        assertThat(javaSourceFilePath.toString(), is("base/com/foo/bar/MyClass.java"));
    }

    @Test
    public void getJavaSourceFilePathInDefaultPackage() throws Exception {
        //Arrange
        Path basePath = Paths.get("base");
        PackageDescriptor packageName = PackageDescriptor.of("");
        ClassnameDescriptor className = ClassnameDescriptor.of("MyClass");
        JavaSourceWriter javaSourceWriter = new JavaSourceWriter(basePath);

        //Act
        Path javaSourceFilePath = javaSourceWriter.getJavaSourceFilePath(packageName, className);

        //Assert
        assertThat(javaSourceFilePath.toString(), is("base/MyClass.java"));
    }

    @Test
    public void writeJavaSourceFile() throws Exception {
        //Arrange
        Path basePath = Files.createTempDirectory("JavaSourceWriterTest");
        JavaSourceWriter javaSourceWriter = new JavaSourceWriter(basePath);
        PackageDescriptor packageName = PackageDescriptor.of("com.foo.bar");
        ClassnameDescriptor className = ClassnameDescriptor.of("MyClass");
        String content = "public class MyClass {}";
        Path pathToWriteTo = javaSourceWriter.getJavaSourceFilePath(packageName, className);

        //Act
        JavaSourceWriter.writeJavaSourceFile(pathToWriteTo, content); //test write
        JavaSourceWriter.writeJavaSourceFile(pathToWriteTo, content); //test overwrite

        //Assert
        assertThat(Files.exists(pathToWriteTo), is(Boolean.TRUE));
        assertThat(new String(Files.readAllBytes(pathToWriteTo)), equalTo(content));
    }


}