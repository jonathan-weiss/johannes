package ch.johannes.example.generator;

import ch.johannes.cg.BeanSourceGenerator;
import ch.johannes.cg.dummy.MapperSourceGenerator;
import ch.johannes.cg.dummy.MapperTestSourceGenerator;
import ch.johannes.core.JavaSourceWriter;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import ch.johannes.example.data.dao.person.PersonMetadata;
import ch.johannes.plan.BeanSourcePlan;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CodeGeneratorRunnable {

    public static void main(String[] args) {
        String pathMain = args[0];
        String pathTest = args[1];
        runCodeGeneration(Paths.get(pathMain), Paths.get(pathTest));
    }

    public static void runCodeGeneration(Path mainSourceFilesBase, Path testSourceFilesBase) {
        try {
            PackageDescriptor personBeanPackage = PackageDescriptor.of("ch.johannes.examples.bean");
            PackageDescriptor personTransferPackage = PackageDescriptor.of("ch.johannes.examples.transfer");

            JavaSourceWriter mainJavaSourceWriter = new JavaSourceWriter(mainSourceFilesBase);
            JavaSourceWriter testJavaSourceWriter = new JavaSourceWriter(testSourceFilesBase);
            BeanSourceGenerator cg = new BeanSourceGenerator();

            //generate person bean descriptor
            final ClassDescriptor personDescriptor = PersonMetadata.PERSON_DESCRIPTOR
                    .with(TypeDescriptor.of(personBeanPackage.getPackageName(), "Person"));
            BeanSourcePlan plan = new BeanSourcePlan(
                    personDescriptor.getTypeDescriptor().getClassPackage(),
                    personDescriptor.getTypeDescriptor().getClassName(),
                    personDescriptor.getFields());
            final String sourceCodeForPerson = cg.generateCode(plan);
            mainJavaSourceWriter.writeJavaSourceFile(
                    personDescriptor.getTypeDescriptor().getClassPackage(),
                    personDescriptor.getTypeDescriptor().getClassName(),
                    sourceCodeForPerson);

            //generate personTO bean
            final ClassDescriptor personTODescriptor = ClassDescriptor.of(
                    personTransferPackage,
                    "MyPersonTO")
                    .addFields(personDescriptor.getFields());

            BeanSourcePlan planTO = new BeanSourcePlan(
                    personTODescriptor.getTypeDescriptor().getClassPackage(),
                    personTODescriptor.getTypeDescriptor().getClassName(),
                    personTODescriptor.getFields());

            final String sourceCodeForPersonTO = cg.generateCode(planTO);
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
