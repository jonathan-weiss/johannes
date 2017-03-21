package ch.johannes.example.generator;

import ch.johannes.cg.BeanSourceGenerator;
import ch.johannes.cg.dummy.MapperTestSourceGenerator;
import ch.johannes.core.JavaSourceWriter;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.example.data.dao.person.PersonMetadata;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestCodeGeneratorRunnable {

    public static void main(String[] args) {
        System.out.println(" -!-!-!-!-! Write my test source files " + args[0]);

        String path = args[0];
        runCodeGeneration(Paths.get(path));
    }

    public static void runCodeGeneration(Path testSourceFilesBase) {
        try {
            PackageDescriptor personToPackage = PackageDescriptor.of("ch.johannes.examples.transfer");

            JavaSourceWriter testJavaSourceWriter = new JavaSourceWriter(testSourceFilesBase);
            BeanSourceGenerator cg = new BeanSourceGenerator();

            final ClassDescriptor personDescriptor = PersonMetadata.PERSON_DESCRIPTOR;

            //generate personTO bean
            final ClassDescriptor personTODescriptor = ClassDescriptor.of(
                    personToPackage,
                    "MyPersonTO")
                    .addFields(personDescriptor.getFields());

            //generate test
            String mapperTestName = "MyGeneratedPersonMapperTest";
            MapperTestSourceGenerator mapperTestCg = new MapperTestSourceGenerator();
            final String sourceCodeForPersonMapperTest = mapperTestCg.generateCode(
                    personDescriptor,
                    personTODescriptor,
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    ClassnameDescriptor.of(mapperTestName));
            testJavaSourceWriter.writeJavaSourceFile(
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    ClassnameDescriptor.of(mapperTestName),
                    sourceCodeForPersonMapperTest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
