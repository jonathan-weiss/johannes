package ch.johannes.cg;

import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.plan.BeanSourcePlan;
import ch.johannes.plan.ImmutableBeanSourcePlan;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ImmutableBeanSourceGenerator {

    public String generateCode(ImmutableBeanSourcePlan immutableBeanSourcePlan) {
        List<FieldSpec> fields = new ArrayList<>();
        List<MethodSpec> getterMethods = new ArrayList<>();

        final MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC);

        for (FieldDescriptor fieldDescriptor : immutableBeanSourcePlan.getFieldDescriptors()) {
            String nameOfField = fieldDescriptor.getFieldName();
            ClassName classOfField = ClassName.get(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), fieldDescriptor.getFieldType().getClassName().getClassName());
            FieldSpec fieldSpec = FieldSpec.builder(classOfField, nameOfField)
                    .addModifiers(Modifier.PRIVATE)
                    .addModifiers(Modifier.FINAL)
                    .build();
            fields.add(fieldSpec);

            MethodSpec getter = MethodSpec.methodBuilder(BeanUtil.createGetterName(nameOfField))
                    .addModifiers(Modifier.PUBLIC)
                    .returns(classOfField)
                    .addStatement("return this.$L", nameOfField)
                    .build();

            getterMethods.add(getter);

            constructorBuilder
                    .addParameter(classOfField, nameOfField)
                    .addStatement("this.$L = $L", nameOfField, nameOfField);

        }

        MethodSpec constructor = constructorBuilder.build();

        TypeSpec targetType = TypeSpec.classBuilder(immutableBeanSourcePlan.getClassnameDescriptor().getClassName())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(constructor)
                .addFields(fields)
                .addMethods(getterMethods)
                .build();

        JavaFile javaFile = JavaFile.builder(immutableBeanSourcePlan.getPackageDescriptor().getPackageName(), targetType)
                .build();

        return javaFile.toString();
    }

}
