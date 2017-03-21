package ch.johannes.metadata;

import ch.johannes.core.FileUtil;
import ch.johannes.core.JavaSourceWriter;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
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

        ClassDescriptor addressDescriptor = ClassDescriptor.of(SOURCE_PACKAGE, "Address")
                .addField(FieldDescriptor.of("street", STRING_TYPE_DESCRIPTOR))
                .addField(FieldDescriptor.of("zipCode", STRING_TYPE_DESCRIPTOR))
                .addField(FieldDescriptor.of("city", STRING_TYPE_DESCRIPTOR));

        TypeDescriptor listOfStringFieldType = LIST_TYPE_DESCRIPTOR.addGenericParameter(STRING_TYPE_DESCRIPTOR);

        TypeDescriptor mapOfStringAndAddressFieldType = MAP_TYPE_DESCRIPTOR.addGenericParameter(STRING_TYPE_DESCRIPTOR).addGenericParameter(addressDescriptor.getTypeDescriptor());
        ClassDescriptor personDescriptor = ClassDescriptor.of(SOURCE_PACKAGE, "Person")
                .addField(FieldDescriptor.of("firstname", STRING_TYPE_DESCRIPTOR))
                .addField(FieldDescriptor.of("lastname", STRING_TYPE_DESCRIPTOR))
                .addField(FieldDescriptor.of("nicknames", listOfStringFieldType))
                .addField(FieldDescriptor.of("addressMap", mapOfStringAndAddressFieldType));

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