package ch.johannes.examples.metadatageneration;

import ch.johannes.cg.JavaSourceWriter;
import ch.johannes.cg.MetadataSourceGenerator;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.examples.mapper.oneone.Person;
import ch.johannes.examples.mapper.person.Address;
import ch.johannes.examples.mapper.person.Country;
import ch.johannes.examples.mapper.person.Gender;
import ch.johannes.examples.mapper.product.Product;
import ch.johannes.examples.mapper.product.ProductMapper;
import ch.johannes.examples.mapper.product.ProductMapperTest;
import ch.johannes.examples.mapper.product.ProductTO;
import ch.johannes.reflector.ClassReflector;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExamplesMetadataCodeGenerationRunner {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Arguments must be an absolute path (codegenerationSourceFilesBase)");
        }
        String codegenerationSourceFilesBasePath = args[0];
        runCodeGenerationForMetadata(Paths.get(codegenerationSourceFilesBasePath));
    }

    public static void runCodeGenerationForMetadata(Path codegenerationSourceFilesBase) {
        PackageDescriptor targetPackageDescriptor = PackageDescriptor.of("ch.johannes.examples.metadata");
        runCodeGenerationForMetadata(codegenerationSourceFilesBase, targetPackageDescriptor, Product.class);
        runCodeGenerationForMetadata(codegenerationSourceFilesBase, targetPackageDescriptor, Person.class);
        runCodeGenerationForMetadata(codegenerationSourceFilesBase, targetPackageDescriptor, Address.class);
        runCodeGenerationForMetadata(codegenerationSourceFilesBase, targetPackageDescriptor, Gender.class);
        runCodeGenerationForMetadata(codegenerationSourceFilesBase, targetPackageDescriptor, Country.class);
    }

    public static void runCodeGenerationForMetadata(Path codegenerationSourceFilesBase, PackageDescriptor targetPackageDescriptor, Class<?> clazzToCreateMetadata) {
        try {
            JavaSourceWriter mainJavaSourceWriter = new JavaSourceWriter(codegenerationSourceFilesBase);
            MetadataSourceGenerator metadataSourceGenerator = new MetadataSourceGenerator();
            ClassDescriptor personClassDescriptor = ClassReflector.reflectClass(clazzToCreateMetadata);
            String personMetadataSourceCode = metadataSourceGenerator.generateCode(personClassDescriptor, targetPackageDescriptor);
            mainJavaSourceWriter.writeJavaSourceFile(targetPackageDescriptor, metadataSourceGenerator.getTargetClassname(personClassDescriptor, targetPackageDescriptor), personMetadataSourceCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
