package ch.johannes.cg;

import ch.johannes.FieldNameUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import ch.johannes.descriptor.TypeDescriptorBuilder;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MetadataSourceGenerator {

    public static final String CLASS_SUFFIX = "Metadata";

    public String generateCode(ClassDescriptor sourceClassDescriptor, PackageDescriptor targetPackage) {

        List<FieldSpec> fields = new ArrayList<>();
        List<MethodSpec> methods = new ArrayList<>();

        List<FieldSpec> constantFields = new ArrayList<>();
        for (FieldDescriptor fieldDescriptor : sourceClassDescriptor.getFields()) {
            String nameOfField = fieldDescriptor.getFieldName();
            ClassName classOfField = ClassName.get(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), fieldDescriptor.getFieldType().getClassName().getClassName());

            //create constant for field type
            FieldSpec fieldTypeSpec = FieldSpec.builder(TypeDescriptor.class, "TYPE_FOR_" + FieldNameUtil.fieldNameToConstant(nameOfField))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer(createTypeDescriptorBuilderCode(fieldDescriptor.getFieldType()))
                    .build();
            constantFields.add(fieldTypeSpec);

            //create constant for field
            FieldSpec fieldSpec = FieldSpec.builder(FieldDescriptor.class, FieldNameUtil.fieldNameToConstant(nameOfField))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer(createFieldDescriptorBuilderCode(fieldDescriptor, fieldTypeSpec))
                    .build();
            constantFields.add(fieldSpec);

        }

        fields.addAll(constantFields);
        TypeSpec targetType = TypeSpec.classBuilder(sourceClassDescriptor.getTypeDescriptor().getClassName().getClassName() + CLASS_SUFFIX)
                .addModifiers(Modifier.PUBLIC)
                .addFields(fields)
                .addMethods(methods)
                .build();

        JavaFile javaFile = JavaFile.builder(targetPackage.getPackageName(), targetType)
                .build();

        return javaFile.toString();
    }

    /**
     * <code>
     * FieldDescriptorBuilder.with("firstname").setFieldType(..).build();
     * </code>
     */
    private CodeBlock createFieldDescriptorBuilderCode(FieldDescriptor fieldDescriptor, FieldSpec fieldTypeSpec) {

        String fieldName = fieldDescriptor.getFieldName();
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.add("$T.of($S, $N)", FieldDescriptor.class, fieldName, fieldTypeSpec);
        return codeBlockBuilder.build();
    }

    /**
     * <code>
     * TypeDescriptorBuilder
     * .with("packageName", "className")
     * .setIsArray(true)
     * .setIsPrimitive(true)
     * .addGenericParameter(STRING_TYPE_DESCRIPTOR)
     * .build();
     * </code>
     */
    private CodeBlock createTypeDescriptorBuilderCode(TypeDescriptor typeDescriptor) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        if (Descriptors.AccessorMap.MAP_OF_TYPE_DESCRIPTOR.containsKey(typeDescriptor)) {
            codeBlockBuilder.add("$T.$L", Descriptors.class, Descriptors.AccessorMap.MAP_OF_TYPE_DESCRIPTOR.get(typeDescriptor));
        } else {
            codeBlockBuilder.add("$T", TypeDescriptorBuilder.class);
            addBuilderLineBreak(codeBlockBuilder);
            codeBlockBuilder.add(".with($S, $S)",
                    typeDescriptor.getClassPackage().getPackageName(),
                    typeDescriptor.getClassName().getClassName());

            if (typeDescriptor.isArray()) { //only write isArray, if it is true (builder default is false)
                addBuilderLineBreak(codeBlockBuilder);
                codeBlockBuilder.add(".setIsArray($L)", typeDescriptor.isArray());
            }

            if (typeDescriptor.isPrimitive()) { //only write isPrimitive, if it is true (builder default is false)
                addBuilderLineBreak(codeBlockBuilder);
                codeBlockBuilder.add(".setIsPrimitive($L)", typeDescriptor.isPrimitive());
            }

            for (TypeDescriptor genericParameter : typeDescriptor.getGenericParameters()) {
                addBuilderLineBreak(codeBlockBuilder);
                codeBlockBuilder.add(".addGenericParameter($L)", createTypeDescriptorBuilderCode(genericParameter));
            }
            codeBlockBuilder.add(".build()");
        }

        return codeBlockBuilder.build();
    }

    private CodeBlock.Builder addBuilderLineBreak(CodeBlock.Builder codeBlockBuilder) {
        //codeBlockBuilder.add("\n");
        return codeBlockBuilder;
    }

}
