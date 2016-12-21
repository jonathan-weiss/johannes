package ch.johannes.cg;

import ch.johannes.CollectionUtil;
import ch.johannes.FileUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MetadataSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, MetadataSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        MetadataSourceGenerator metadataSourceGenerator = new MetadataSourceGenerator();

        TypeDescriptor stringFieldType = TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String"));
        TypeDescriptor integerFieldType = TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("Integer"));
        TypeDescriptor listOfStringFieldType = TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("List"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, CollectionUtil.listOf(stringFieldType));
        TypeDescriptor mapOfStringAndIntegerFieldType = TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("Map"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, CollectionUtil.listOf(stringFieldType, integerFieldType));
        ClassDescriptor personDescriptor = ClassDescriptorBuilder.with(ClassnameDescriptor.of("Person"))
                .addClassField(FieldDescriptor.of("firstname", stringFieldType))
                .addClassField(FieldDescriptor.of("lastname", stringFieldType))
                .addClassField(FieldDescriptor.of("nicknames", listOfStringFieldType))
                .addClassField(FieldDescriptor.of("stupidMap", mapOfStringAndIntegerFieldType))
                .setClassPackage(PackageDescriptor.of("ch.johannes.virtualpackage"))
                .build();

        PackageDescriptor targetPackage = PackageDescriptor.of("ch.johannes.generated.metadata");
        String generatedCode = metadataSourceGenerator.generateCode(personDescriptor, targetPackage);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));

        //convinience to check for compiler errors
        writeToFile(targetPackage, ClassnameDescriptor.of("Person" + MetadataSourceGenerator.CLASS_SUFFIX), generatedCode);
    }

    /**
     * Convinience method to write down the java source code to a file
     */
    private void writeToFile(PackageDescriptor packageName, ClassnameDescriptor className, String generatedCode) throws Exception {
        final Path pathToJavaTests = Paths.get("./src/test/java");
        JavaSourceWriter writer = new JavaSourceWriter(pathToJavaTests);
        System.out.println(String.format("Write content to %s.", writer.getJavaSourceFilePath(packageName, className).toAbsolutePath()));
        writer.writeJavaSourceFile(packageName, className, generatedCode);
    }
}