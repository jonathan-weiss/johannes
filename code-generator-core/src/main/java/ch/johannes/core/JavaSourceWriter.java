package ch.johannes.core;

import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaSourceWriter {
    private final Path basePath;

    public JavaSourceWriter(Path basePath) {
        this.basePath = basePath;
    }

    public Path getJavaSourceFilePath(PackageDescriptor packageName, ClassnameDescriptor filename) {
        Path path = basePath;


        for(String packagePart : packageName.getPackages()) {
            path = path.resolve(packagePart);
        }
        path = path.resolve(filename.getClassName().concat(".java"));
        return path;
    };

    public void writeJavaSourceFile(PackageDescriptor packageName, ClassnameDescriptor filename, String content) throws IOException {
        final Path filePath = getJavaSourceFilePath(packageName, filename);
        writeJavaSourceFile(filePath, content);
    }

    public static void writeJavaSourceFile(Path path, String content) throws IOException {
        if(!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        Files.write(path, content.getBytes());
    };


}
