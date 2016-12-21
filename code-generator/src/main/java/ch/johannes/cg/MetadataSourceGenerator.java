package ch.johannes.cg;

import ch.johannes.CollectionUtil;
import ch.johannes.FieldNameUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MetadataSourceGenerator {

    public static final String CLASS_SUFFIX = "Metadata";

    public String generateCode(ClassDescriptor sourceClassDescriptor, PackageDescriptor targetPackage) {

        List<FieldSpec> fields = new ArrayList<>();
        List<MethodSpec> methods = new ArrayList<>();

        List<FieldSpec> constantFields = new ArrayList<>();
        for (FieldDescriptor fieldDescriptor : sourceClassDescriptor.getFields()) {
            String nameOfField = fieldDescriptor.getFieldName();
            ClassName classOfField = ClassName.get(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), fieldDescriptor.getFieldType().getClassName().getClassName());

            //create constant for field
            FieldSpec fieldSpec = FieldSpec.builder(FieldDescriptor.class, FieldNameUtil.fieldNameToConstant(nameOfField))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer(createFieldDescriptorCode(fieldDescriptor))
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
     * FieldDescriptor.of("name", TypeDescriptor.of(PackageDescriptor.of("packageName"), ClassnameDescriptor.of("className"), isArray, isPrimitive, List<TypeDescriptor> genericParameters))
     * </code>
     */
    private CodeBlock createFieldDescriptorCode(FieldDescriptor fieldDescriptor) {
        String fieldName = fieldDescriptor.getFieldName();
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.add("$T.of($S, $L)", FieldDescriptor.class, fieldName, createTypeDescriptorCode(fieldDescriptor.getFieldType()));
        return codeBlockBuilder.build();
    }

    /**
     * <code>
     * TypeDescriptor.of(PackageDescriptor.of("packageName"), ClassnameDescriptor.of("className"), isArray, isPrimitive, List<TypeDescriptor> genericParameters);
     * </code>
     */
    private CodeBlock createTypeDescriptorCode(TypeDescriptor typeDescriptor) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        final List<CodeBlock> codeBlocksForEachGenericParameter = typeDescriptor.getGenericParameters().stream().map(this::createTypeDescriptorCode).collect(Collectors.toList());

        codeBlockBuilder.add("$T.of($L, $L, $L, $L, $L)",
                TypeDescriptor.class,
                createPackageDescriptorCode(typeDescriptor.getClassPackage()),
                createClassnameDescriptorCode(typeDescriptor.getClassName()),
                typeDescriptor.isArray(), //TODO work with constants
                typeDescriptor.isPrimitive(), //TODO work with constants
                createListOfTypeCode(TypeDescriptor.class, codeBlocksForEachGenericParameter));
        return codeBlockBuilder.build();
    }

    /**
     * <code>
     * PackageDescriptor.of("packageName");
     * </code>
     */
    private CodeBlock createPackageDescriptorCode(PackageDescriptor packageDescriptor) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.add("$T.of($S)", PackageDescriptor.class, packageDescriptor.getPackageName());
        return codeBlockBuilder.build();
    }

    /**
     * <code>
     * ClassnameDescriptor.of("className")
     * </code>
     */
    private CodeBlock createClassnameDescriptorCode(ClassnameDescriptor classnameDescriptor) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.add("$T.of($S)", ClassnameDescriptor.class, classnameDescriptor.getClassName());
        return codeBlockBuilder.build();
    }

    /**
     * <code>
     * CollectionUtil<String></>.arrayListOf("1", "2", "3");
     * </code>
     */
    private CodeBlock createListOfTypeCode(Class<?> genericClassOfList, List<CodeBlock> elementsInList) {
        CodeBlock.Builder enumerationOfElements = CodeBlock.builder();
        boolean isFirstElement = true;
        for(CodeBlock element: elementsInList) {
            if(isFirstElement) {
                isFirstElement = false;
            } else {
                enumerationOfElements.add(",");
            }
            enumerationOfElements.add(element);
        }

        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();

        codeBlockBuilder.add("$T.<$T>listOf($L)", CollectionUtil.class, genericClassOfList, enumerationOfElements.build());
        return codeBlockBuilder.build();
    }



}
