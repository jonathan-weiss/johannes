package ch.johannes.examples;

import ch.johannes.cg.BeanSourceGenerator;
import ch.johannes.cg.JavaSourceWriter;
import ch.johannes.cg.dummy.MapperSourceGenerator;
import ch.johannes.cg.dummy.MapperTestSourceGenerator;
import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.examples.descriptor.PersonDescriptor;
import ch.johannes.examples.descriptor.PersonMappingDescription;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExamplesCodeGenerationRunner {

    public static void main(String [] args)
    {
        if(args.length != 2) {
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
            final BeanDescriptor personDescriptor = PersonDescriptor.create();

            //generate personTO bean
            final BeanDescriptor personTODescriptor = PersonMappingDescription.create();
            BeanSourceGenerator cg = new BeanSourceGenerator();
            final String sourceCodeForPersonTO = cg.generateCode(personTODescriptor);
            mainJavaSourceWriter.writeJavaSourceFile(personTODescriptor.getBeanPackage(), personTODescriptor.getBeanName(), sourceCodeForPersonTO);

            //generate mapper
            String mapperName = "MyGeneratedPersonMapper";
            MapperSourceGenerator mapperCg = new MapperSourceGenerator();
            final String sourceCodeForPersonMapper = mapperCg.generateCode(personDescriptor, personTODescriptor, personTODescriptor.getBeanPackage(), ClassnameDescriptor.of(mapperName));
            mainJavaSourceWriter.writeJavaSourceFile(personTODescriptor.getBeanPackage(), ClassnameDescriptor.of(mapperName), sourceCodeForPersonMapper);

            //generate test
            String mapperTestName = "MyGeneratedPersonMapperTest";
            MapperTestSourceGenerator mapperTestCg = new MapperTestSourceGenerator();
            final String sourceCodeForPersonMapperTest = mapperTestCg.generateCode(personDescriptor, personTODescriptor, personTODescriptor.getBeanPackage(), ClassnameDescriptor.of(mapperTestName));
            testJavaSourceWriter.writeJavaSourceFile(personTODescriptor.getBeanPackage(), ClassnameDescriptor.of(mapperTestName), sourceCodeForPersonMapperTest);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
