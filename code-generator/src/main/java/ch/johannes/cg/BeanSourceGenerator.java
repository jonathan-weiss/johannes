package ch.johannes.cg;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BeanSourceGenerator {

    public String generateCode(String targetPackageName, String targetClassName, LinkedHashMap<String, Class<?>> targetFieldNames) {
        List<FieldSpec> fields = new ArrayList<>();
        List<MethodSpec> setterAndGetterMethods = new ArrayList<>();
        for(String fieldName : targetFieldNames.keySet()) {
            Class<?> classOfField = targetFieldNames.get(fieldName);
            FieldSpec fieldSpec = FieldSpec.builder(classOfField, fieldName)
                    .addModifiers(Modifier.PRIVATE)
                    .build();
            fields.add(fieldSpec);


            MethodSpec getter = MethodSpec.methodBuilder(BeanUtil.createGetterName(fieldName))
                    .addModifiers(Modifier.PUBLIC)
                    .returns(classOfField)
                    .addStatement("return this.$L", fieldName)
                    .build();

            setterAndGetterMethods.add(getter);

            MethodSpec setter = MethodSpec.methodBuilder(BeanUtil.createSetterName(fieldName))
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(classOfField, fieldName)
                    .returns(void.class)
                    .addStatement("this.$L = $L", fieldName, fieldName)
                    .build();

            setterAndGetterMethods.add(setter);


        }
        TypeSpec targetType = TypeSpec.classBuilder(targetClassName)
                .addModifiers(Modifier.PUBLIC)
                .addFields(fields)
                .addMethods(setterAndGetterMethods)
                .build();

        JavaFile javaFile = JavaFile.builder(targetPackageName, targetType)
                .build();

        return javaFile.toString();
    }

}
