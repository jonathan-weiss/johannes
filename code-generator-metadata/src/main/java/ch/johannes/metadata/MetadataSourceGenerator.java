package ch.johannes.metadata;

import ch.johannes.core.JavaNameUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.Descriptors;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetadataSourceGenerator {

    public static final String METADATA_CLASS_PREFIX = "";

    public static final String METADATA_CLASS_SUFFIX = "Metadata";

    public static final String FIELD_PREFIX = "";

    public static final String FIELD_TYPE_PREFIX = "_TYPE_FOR_";

    public static final String PACKAGE_DESCRIPTOR_FIELD_NAME = "PACKAGE_DESCRIPTOR";

    public static final String CLASS_DESCRIPTOR_FIELD_NAME = "CLASS_DESCRIPTOR";

    public static final String NAMED_CLASS_DESCRIPTOR_FIELD_NAME_PREFIX = "";

    public static final String NAMED_CLASS_DESCRIPTOR_FIELD_NAME_SUFFIX = "_DESCRIPTOR";

    public ClassnameDescriptor getTargetClassname(ClassDescriptor sourceClassDescriptor, PackageDescriptor targetPackage) {
        return ClassnameDescriptor.of(METADATA_CLASS_PREFIX + sourceClassDescriptor.getTypeDescriptor().getClassName().getClassName() + METADATA_CLASS_SUFFIX);
    }

    public String generateCode(ClassDescriptor sourceClassDescriptor, PackageDescriptor targetPackage) {

        List<FieldSpec> fields = new ArrayList<>();
        List<MethodSpec> methods = new ArrayList<>();

        List<FieldSpec> constantFields = new ArrayList<>();

        Map<FieldDescriptor, FieldSpec> fieldSpecs = new HashMap<>();
        for (FieldDescriptor fieldDescriptor : sourceClassDescriptor.getFields()) {
            String nameOfField = fieldDescriptor.getFieldName();

            //create constant field for field type
            FieldSpec fieldTypeSpec = FieldSpec.builder(TypeDescriptor.class, FIELD_TYPE_PREFIX + JavaNameUtil.fieldNameToConstant(nameOfField))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer(createTypeDescriptorBuilderCode(fieldDescriptor.getFieldType()))
                    .build();
            constantFields.add(fieldTypeSpec);

            //create constant field for field
            FieldSpec fieldSpec = FieldSpec.builder(FieldDescriptor.class, FIELD_PREFIX + JavaNameUtil.fieldNameToConstant(nameOfField))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer(createFieldDescriptorBuilderCode(fieldDescriptor, fieldTypeSpec))
                    .build();
            constantFields.add(fieldSpec);
            fieldSpecs.put(fieldDescriptor, fieldSpec);

        }

        //create constant field for class package descriptor
        FieldSpec packageFieldSpec = FieldSpec.builder(PackageDescriptor.class, PACKAGE_DESCRIPTOR_FIELD_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer(createPackageDescriptorBuilderCode(sourceClassDescriptor.getTypeDescriptor().getClassPackage()))
                .build();
        constantFields.add(packageFieldSpec);

        //create constant field for class descriptor
        FieldSpec classFieldSpec = FieldSpec.builder(ClassDescriptor.class, CLASS_DESCRIPTOR_FIELD_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer(createClassDescriptorBuilderCode(sourceClassDescriptor, packageFieldSpec, fieldSpecs))
                .build();
        constantFields.add(classFieldSpec);

        FieldSpec namedClassFieldSpec = FieldSpec.builder(ClassDescriptor.class, NAMED_CLASS_DESCRIPTOR_FIELD_NAME_PREFIX + JavaNameUtil.classNameToConstant(sourceClassDescriptor.getTypeDescriptor().getClassName() + NAMED_CLASS_DESCRIPTOR_FIELD_NAME_SUFFIX))
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$N", classFieldSpec)
                .build();
        constantFields.add(namedClassFieldSpec);

        fields.addAll(constantFields);
        TypeSpec targetType = TypeSpec.classBuilder(getTargetClassname(sourceClassDescriptor, targetPackage).getClassName())
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
     * ClassDescriptor
     * .of("packageName", "className")
     * .addField(FieldDescriptor.of("firstname", STRING_TYPE_DESCRIPTOR))
     * .addField(FieldDescriptor.of("lastname", STRING_TYPE_DESCRIPTOR))
     * .addField(FieldDescriptor.of("nicknames", listOfStringFieldType))
     * .addField(FieldDescriptor.of("addressMap", mapOfStringAndAddressFieldType))
     * </code>
     */
    private CodeBlock createClassDescriptorBuilderCode(ClassDescriptor sourceClassDescriptor, FieldSpec packageFieldSpec, Map<FieldDescriptor, FieldSpec> fieldSpecs) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.add("$T.of($N,$S)", ClassDescriptor.class, packageFieldSpec, sourceClassDescriptor.getTypeDescriptor().getClassName().getClassName());
        for (FieldDescriptor fieldDescriptor : sourceClassDescriptor.getFields()) {
            codeBlockBuilder.add(".addField($N)", fieldSpecs.get(fieldDescriptor));
        }
        return codeBlockBuilder.build();
    }

    /**
     * <code>
     * PackageDescriptor.of("package");
     * </code>
     */
    private CodeBlock createPackageDescriptorBuilderCode(PackageDescriptor packageDescriptor) {

        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.add("$T.of($S)", PackageDescriptor.class, packageDescriptor.getPackageName());
        return codeBlockBuilder.build();
    }

    /**
     * <code>
     * FieldDescriptor.of("firstname", typeDescriptor);
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
     * TypeDescriptor
     * .of("packageName", "className")
     * .withArray(true)
     * .withPrimitive(true)
     * .addGenericParameter(STRING_TYPE_DESCRIPTOR)
     * </code>
     */
    private CodeBlock createTypeDescriptorBuilderCode(TypeDescriptor typeDescriptor) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        if (Descriptors.AccessorMap.MAP_OF_TYPE_DESCRIPTOR.containsKey(typeDescriptor)) {
            codeBlockBuilder.add("$T.$L", Descriptors.class, Descriptors.AccessorMap.MAP_OF_TYPE_DESCRIPTOR.get(typeDescriptor));
        } else {
            codeBlockBuilder.add("$T", TypeDescriptor.class);
            codeBlockBuilder.add(".of($S, $S)",
                    typeDescriptor.getClassPackage().getPackageName(),
                    typeDescriptor.getClassName().getClassName());

            if (typeDescriptor.isArray()) { //only write isArray, if it is true (builder default is false)
                codeBlockBuilder.add(".withArray($L)", typeDescriptor.isArray());
            }

            if (typeDescriptor.isPrimitive()) { //only write isPrimitive, if it is true (builder default is false)
                codeBlockBuilder.add(".withPrimitive($L)", typeDescriptor.isPrimitive());
            }

            for (TypeDescriptor genericParameter : typeDescriptor.getGenericParameters()) {
                codeBlockBuilder.add(".addGenericParameter($L)", createTypeDescriptorBuilderCode(genericParameter));
            }
        }

        return codeBlockBuilder.build();
    }

}
