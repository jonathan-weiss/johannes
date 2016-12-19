package ch.johannes.cg.dummy;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

public class MapperSourceGenerator {

    public String generateCode(ClassDescriptor sourceBean, ClassDescriptor targetBean, PackageDescriptor packageDescriptor, ClassnameDescriptor mapperClassName) {
        ClassName sourceBeanClass = ClassName.get(sourceBean.getTypeDescriptor().getClassPackage().getPackageName(), sourceBean.getTypeDescriptor().getClassName().getClassName());
        ClassName targetBeanClass = ClassName.get(targetBean.getTypeDescriptor().getClassPackage().getPackageName(), targetBean.getTypeDescriptor().getClassName().getClassName());

        MethodSpec dummyApply = MethodSpec.methodBuilder("apply")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(sourceBeanClass, "sourceBean")
                .returns(targetBeanClass)
                .addComment("TODO implement mapping here")
                .addStatement("return null")
                .build();

        TypeSpec targetType = TypeSpec.classBuilder(mapperClassName.getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(dummyApply)
                .build();

        JavaFile javaFile = JavaFile.builder(packageDescriptor.getPackageName(), targetType)
                .build();

        return javaFile.toString();
    }

}
