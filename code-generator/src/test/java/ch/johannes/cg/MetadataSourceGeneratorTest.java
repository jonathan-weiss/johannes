package ch.johannes.cg;

import ch.johannes.FileUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import ch.johannes.descriptor.TypeDescriptorBuilder;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static ch.johannes.descriptor.Descriptors.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MetadataSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, MetadataSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        MetadataSourceGenerator metadataSourceGenerator = new MetadataSourceGenerator();

        TypeDescriptor listOfStringFieldType = TypeDescriptorBuilder.with(LIST_TYPE_DESCRIPTOR).addGenericParameter(STRING_TYPE_DESCRIPTOR).build();
        TypeDescriptor mapOfStringAndIntegerFieldType = TypeDescriptorBuilder.with(MAP_TYPE_DESCRIPTOR).addGenericParameter(STRING_TYPE_DESCRIPTOR).addGenericParameter(INTEGER_TYPE_DESCRIPTOR).build();
        ClassDescriptor personDescriptor = ClassDescriptorBuilder.with("Person")
                .addClassField(FieldDescriptor.of("firstname", STRING_TYPE_DESCRIPTOR))
                .addClassField(FieldDescriptor.of("lastname", STRING_TYPE_DESCRIPTOR))
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