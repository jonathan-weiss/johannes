package ch.johannes.examples;

import ch.johannes.cg.BeanSourceGenerator;
import ch.johannes.cg.JavaSourceWriter;
import ch.johannes.cg.dummy.MapperSourceGenerator;
import ch.johannes.cg.dummy.MapperTestSourceGenerator;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.examples.metadata.PersonMetadata;
import ch.johannes.examples.metadata.PersonTOMetadata;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExamplesCodeGenerationRunner {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Arguments must be two absolute paths (mainSourceFileBase, testSourceFileBase)");
        }
        String mainSourceFileBasePath = args[0];
        String testSourceFileBasePath = args[1];
        runCodeGeneration(Paths.get(mainSourceFileBasePath), Paths.get(testSourceFileBasePath));

    }

    public static void runCodeGeneration(Path mainSourceFilesBase, Path testSourceFilesBase) {
        try {
            JavaSourceWriter mainJavaSourceWriter = new JavaSourceWriter(mainSourceFilesBase);
            JavaSourceWriter testJavaSourceWriter = new JavaSourceWriter(testSourceFilesBase);
            BeanSourceGenerator cg = new BeanSourceGenerator();

            //generate person bean descriptor
            final ClassDescriptor personDescriptor = PersonMetadata.PERSON_DESCRIPTOR;
            final String sourceCodeForPerson = cg.generateCode(personDescriptor);
            mainJavaSourceWriter.writeJavaSourceFile(
                    personDescriptor.getTypeDescriptor().getClassPackage(),
                    personDescriptor.getTypeDescriptor().getClassName(),
                    sourceCodeForPerson);

            //generate personTO bean
            final ClassDescriptor personTODescriptor = ClassDescriptor.of(
                    PersonTOMetadata.PERSONT_O_DESCRIPTOR.getTypeDescriptor().getClassPackage(),
                    "MyPersonTO")
                    .addFields(PersonTOMetadata.PERSONT_O_DESCRIPTOR.getFields());
            final String sourceCodeForPersonTO = cg.generateCode(personTODescriptor);
            mainJavaSourceWriter.writeJavaSourceFile(
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    personTODescriptor.getTypeDescriptor().getClassName(),
                    sourceCodeForPersonTO);

            //generate mapper
            String mapperName = "MyGeneratedPersonMapper";
            MapperSourceGenerator mapperCg = new MapperSourceGenerator();
            final String sourceCodeForPersonMapper = mapperCg.generateCode(
                    personDescriptor,
                    personTODescriptor,
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    ClassnameDescriptor.of(mapperName));
            mainJavaSourceWriter.writeJavaSourceFile(
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    ClassnameDescriptor.of(mapperName),
                    sourceCodeForPersonMapper);

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
