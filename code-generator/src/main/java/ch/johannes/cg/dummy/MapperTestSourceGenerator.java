package ch.johannes.cg.dummy;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.junit.Test;

import javax.lang.model.element.Modifier;

public class MapperTestSourceGenerator {

    public String generateCode(ClassDescriptor sourceBean, ClassDescriptor targetBean, PackageDescriptor packageDescriptor, ClassnameDescriptor testClassName) {
        MethodSpec dummyTestMethod = MethodSpec.methodBuilder("simpleTest")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Test.class)
                .returns(void.class)
                .addStatement("//test asserts")
                .build();

        TypeSpec targetType = TypeSpec.classBuilder(testClassName.getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(dummyTestMethod)
                .build();

        JavaFile javaFile = JavaFile.builder(packageDescriptor.getPackageName(), targetType)
                .build();

        return javaFile.toString();
    }

}
