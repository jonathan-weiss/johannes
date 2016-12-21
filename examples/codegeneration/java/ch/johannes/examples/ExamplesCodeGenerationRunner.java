package ch.johannes.examples;

import ch.johannes.cg.BeanSourceGenerator;
import ch.johannes.cg.JavaSourceWriter;
import ch.johannes.cg.dummy.MapperSourceGenerator;
import ch.johannes.cg.dummy.MapperTestSourceGenerator;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassDescriptorBuilder;
import ch.johannes.descriptor.ClassnameDescriptorBuilder;
import ch.johannes.examples.metadata.PersonMetadata;
import ch.johannes.examples.metadata.PersonTOMetadata;

import java.io.IOException;
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

            //generate person bean descriptor
            final ClassDescriptor personDescriptor = PersonMetadata.PERSON_DESCRIPTOR;

            //generate personTO bean

            final ClassDescriptor personTODescriptor = ClassDescriptorBuilder.with(PersonTOMetadata.PERSONT_O_DESCRIPTOR).setClassname("MyPersonTO").build();
            BeanSourceGenerator cg = new BeanSourceGenerator();
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
                    ClassnameDescriptorBuilder.with(mapperName).build());
            mainJavaSourceWriter.writeJavaSourceFile(
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    ClassnameDescriptorBuilder.with(mapperName).build(),
                    sourceCodeForPersonMapper);

            //generate test
            String mapperTestName = "MyGeneratedPersonMapperTest";
            MapperTestSourceGenerator mapperTestCg = new MapperTestSourceGenerator();
            final String sourceCodeForPersonMapperTest = mapperTestCg.generateCode(
                    personDescriptor,
                    personTODescriptor,
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    ClassnameDescriptorBuilder.with(mapperTestName).build());
            testJavaSourceWriter.writeJavaSourceFile(
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    ClassnameDescriptorBuilder.with(mapperTestName).build(),
                    sourceCodeForPersonMapperTest);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
