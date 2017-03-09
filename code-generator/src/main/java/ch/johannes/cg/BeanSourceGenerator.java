package ch.johannes.cg;

import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.plan.BeanSourcePlan;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

public class BeanSourceGenerator {

    public String generateCode(BeanSourcePlan beanSourcePlan) {
        List<FieldSpec> fields = new ArrayList<>();
        List<MethodSpec> setterAndGetterMethods = new ArrayList<>();
        for (FieldDescriptor fieldDescriptor : beanSourcePlan.getFieldDescriptors()) {
            String nameOfField = fieldDescriptor.getFieldName();
            ClassName classOfField = ClassName.get(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), fieldDescriptor.getFieldType().getClassName().getClassName());
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
        TypeSpec targetType = TypeSpec.classBuilder(beanSourcePlan.getClassnameDescriptor().getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addFields(fields)
                .addMethods(setterAndGetterMethods)
                .build();

        JavaFile javaFile = JavaFile.builder(beanSourcePlan.getPackageDescriptor().getPackageName(), targetType)
                .build();

        return javaFile.toString();
    }

}
