package ch.johannes.cg;

import ch.johannes.descriptor.BeanDescriptor;
import ch.johannes.descriptor.FieldDescription;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BeanSourceGenerator {

    public String generateCode(BeanDescriptor beanDescriptor) {
        List<FieldSpec> fields = new ArrayList<>();
        List<MethodSpec> setterAndGetterMethods = new ArrayList<>();
        for (FieldDescription fieldDescription : beanDescriptor.getTargetFields()) {
            String nameOfField = fieldDescription.getFieldName();
            Class<?> classOfField = fieldDescription.getFieldType();
            FieldSpec fieldSpec = FieldSpec.builder(classOfField, nameOfField)
                    .addModifiers(Modifier.PRIVATE)
                    .build();
            fields.add(fieldSpec);

            MethodSpec getter = MethodSpec.methodBuilder(BeanUtil.createGetterName(nameOfField))
                    .addModifiers(Modifier.PUBLIC)
                    .returns(classOfField)
                    .addStatement("return this.$L", nameOfField)
                    .build();

            setterAndGetterMethods.add(getter);

            MethodSpec setter = MethodSpec.methodBuilder(BeanUtil.createSetterName(nameOfField))
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(classOfField, nameOfField)
                    .returns(void.class)
                    .addStatement("this.$L = $L", nameOfField, nameOfField)
                    .build();

            setterAndGetterMethods.add(setter);

        }
        TypeSpec targetType = TypeSpec.classBuilder(beanDescriptor.getTargetBeanName())
                .addModifiers(Modifier.PUBLIC)
                .addFields(fields)
                .addMethods(setterAndGetterMethods)
                .build();

        JavaFile javaFile = JavaFile.builder(beanDescriptor.getTargetBeanPackage(), targetType)
                .build();

        return javaFile.toString();
    }

}
