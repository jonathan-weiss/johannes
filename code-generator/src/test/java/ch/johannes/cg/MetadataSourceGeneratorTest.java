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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MetadataSourceGeneratorTest {

    private static final String SOURCE_PACKAGE = "ch.johannes.virtualpackage";

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, MetadataSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        MetadataSourceGenerator metadataSourceGenerator = new MetadataSourceGenerator();

        ClassDescriptor addressDescriptor = ClassDescriptorBuilder.with("Address")
                .addClassField(FieldDescriptor.of("street", STRING_TYPE_DESCRIPTOR))
                .addClassField(FieldDescriptor.of("zipCode", STRING_TYPE_DESCRIPTOR))
                .addClassField(FieldDescriptor.of("city", STRING_TYPE_DESCRIPTOR))
                .setClassPackage(SOURCE_PACKAGE)
                .build();


        TypeDescriptor listOfStringFieldType = TypeDescriptorBuilder
                .with(LIST_TYPE_DESCRIPTOR)
                .addGenericParameter(STRING_TYPE_DESCRIPTOR)
                .build();

        TypeDescriptor mapOfStringAndAddressFieldType = TypeDescriptorBuilder
                .with(MAP_TYPE_DESCRIPTOR)
                .addGenericParameter(STRING_TYPE_DESCRIPTOR)
                .addGenericParameter(addressDescriptor.getTypeDescriptor())
                .build();
        ClassDescriptor personDescriptor = ClassDescriptorBuilder
                .with("Person")
                .addClassField(FieldDescriptor.of("firstname", STRING_TYPE_DESCRIPTOR))
                .addClassField(FieldDescriptor.of("lastname", STRING_TYPE_DESCRIPTOR))
                .addClassField(FieldDescriptor.of("nicknames", listOfStringFieldType))
                .addClassField(FieldDescriptor.of("addressMap", mapOfStringAndAddressFieldType))
                .setClassPackage(SOURCE_PACKAGE)
                .build();

        PackageDescriptor targetPackage = PackageDescriptor.of("ch.johannes.generated.metadata");
        String generatedCode = metadataSourceGenerator.generateCode(personDescriptor, targetPackage);

        assertThat(generatedCode, is(expectedJavaSourceText));

        //convinience method to check for compiler errors
        //writeToFile(targetPackage, ClassnameDescriptor.of("Person" + MetadataSourceGenerator.METADATA_CLASS_SUFFIX), generatedCode);
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