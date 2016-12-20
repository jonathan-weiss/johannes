package ch.johannes.reflector;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClassReflectorTypeTest {

    class MyTestClassToReflect {

        //private List<String> [] tokenListArray;
    }

    @Test
    public void reflectGenericAndArray() throws Exception {
        //Arrange
        class MyTestClassToReflectGenericAndArray {
            private List<String> [] genericAndArray;
        }

        Object instanceToInspect = new MyTestClassToReflectGenericAndArray();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectGenericAndArray"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("genericAndArray"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("List<String>[]"));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.FALSE));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.FALSE));
        assertThat(fieldDescriptor.getFieldType().getGenericParameters().size(), is(0));
    }


    @Test
    public void reflectRecursiveField() throws Exception {
        //Arrange
        class MyTestClassToReflectRecursiveField {
            private MyTestClassToReflectRecursiveField recursive;
        }

        Object instanceToInspect = new MyTestClassToReflectRecursiveField();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectRecursiveField"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("recursive"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("MyTestClassToReflectRecursiveField"));
        assertThat(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.FALSE));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.FALSE));
        assertThat(fieldDescriptor.getFieldType().getGenericParameters().size(), is(0));
    }


    @Test
    public void reflectPrimitiveIntField() throws Exception {
        //Arrange
        class MyTestClassToReflectPrimitives {
            private int number;
        }

        Object instanceToInspect = new MyTestClassToReflectPrimitives();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectPrimitives"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("number"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("int"));
        assertThat(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), is(""));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.FALSE));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.TRUE));
        assertThat(fieldDescriptor.getFieldType().getGenericParameters().size(), is(0));
    }

    @Test
    public void reflectNonPrimitiveIntegerField() throws Exception {
        //Arrange
        class MyTestClassToReflectNonPrimitives {
            private Integer number;
        }

        Object instanceToInspect = new MyTestClassToReflectNonPrimitives();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectNonPrimitives"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("number"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("Integer"));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.FALSE));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.FALSE));
        assertThat(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), is("java.lang"));
        assertThat(fieldDescriptor.getFieldType().getGenericParameters().size(), is(0));
    }


    @Test
    public void reflectGenericInGenericParams() throws Exception {
        //Arrange
        class MyTestClassToReflectGenericInGeneric {
            private List<Function<String, Class<Byte>>> genericInGeneric;
        }

        Object instanceToInspect = new MyTestClassToReflectGenericInGeneric();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectGenericInGeneric"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("genericInGeneric"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("List"));
        assertThat(fieldDescriptor.getFieldType().getGenericParameters().size(), is(1));
        final TypeDescriptor firstGenericParamOfList = fieldDescriptor.getFieldType().getGenericParameters().get(0);
        assertThat(firstGenericParamOfList.getClassName().getClassName(), is("Function"));
        assertThat(firstGenericParamOfList.getGenericParameters().size(), is(2));
        final TypeDescriptor firstGenericParam = firstGenericParamOfList.getGenericParameters().get(0);
        final TypeDescriptor secondGenericParam = firstGenericParamOfList.getGenericParameters().get(1);
        assertThat(firstGenericParam.getClassName().getClassName(), is("String"));
        assertThat(firstGenericParam.getGenericParameters().size(), is(0));
        assertThat(secondGenericParam.getClassName().getClassName(), is("Class"));
        assertThat(secondGenericParam.getGenericParameters().size(), is(1));
        final TypeDescriptor innerParamOfSecondGenericParam = secondGenericParam.getGenericParameters().get(0);
        assertThat(innerParamOfSecondGenericParam.getClassName().getClassName(), is("Byte"));

    }

    @Test
    public void reflectArray() throws Exception {
        //Arrange
        class MyTestClassToReflectArray {
            private String [] myArray;
        }

        Object instanceToInspect = new MyTestClassToReflectArray();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectArray"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("myArray"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("String[]"));
        assertThat(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), is(""));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.TRUE));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.FALSE));
    }

    @Test
    public void reflectPrimitiveArray() throws Exception {
        //Arrange
        class MyTestClassToReflectPrimitiveArray {
            private char [] myArray;
        }

        Object instanceToInspect = new MyTestClassToReflectPrimitiveArray();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectPrimitiveArray"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("myArray"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("char[]"));
        assertThat(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), is(""));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.TRUE));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.FALSE)); //array itself is never primitive, only it's type
    }


    @Test
    public void reflectSingleGenericParams() throws Exception {
        //Arrange
        class MyTestClassToReflectGenericList {
            private List<String> tokenList;
        }

        Object instanceToInspect = new MyTestClassToReflectGenericList();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectGenericList"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("tokenList"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("List"));
        assertThat(fieldDescriptor.getFieldType().getGenericParameters().size(), is(1));
        final TypeDescriptor firstGenericParam = fieldDescriptor.getFieldType().getGenericParameters().get(0);
        assertThat(firstGenericParam.getClassName().getClassName(), is("String"));
        assertThat(firstGenericParam.getGenericParameters().size(), is(0));
    }

    @Test
    public void reflectMultipleGenericParams() throws Exception {
        //Arrange
        class MyTestClassToReflectGenericMultiFunction {
            private Function<String, Class<Integer>> genericWithTwoTypes;
        }

        Object instanceToInspect = new MyTestClassToReflectGenericMultiFunction();

        //Act
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(instanceToInspect.getClass());

        //Assert
        assertThat(classDescriptor.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectGenericMultiFunction"));
        assertThat(classDescriptor.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
        assertThat(classDescriptor.getTypeDescriptor().isArray(), is(Boolean.FALSE));
        assertThat(classDescriptor.getTypeDescriptor().isPrimitive(), is(Boolean.FALSE));
        assertThat(classDescriptor.getFields().size(), is(1));
        final FieldDescriptor fieldDescriptor = classDescriptor.getFields().get(0);
        assertThat(fieldDescriptor.getFieldName(), is("genericWithTwoTypes"));
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("Function"));
        assertThat(fieldDescriptor.getFieldType().getGenericParameters().size(), is(2));
        final TypeDescriptor firstGenericParam = fieldDescriptor.getFieldType().getGenericParameters().get(0);
        final TypeDescriptor secondGenericParam = fieldDescriptor.getFieldType().getGenericParameters().get(1);
        assertThat(firstGenericParam.getClassName().getClassName(), is("String"));
        assertThat(firstGenericParam.getGenericParameters().size(), is(0));
        assertThat(secondGenericParam.getClassName().getClassName(), is("Class"));
        assertThat(secondGenericParam.getGenericParameters().size(), is(1));
        final TypeDescriptor innerParamOfSecondGenericParam = secondGenericParam.getGenericParameters().get(0);
        assertThat(innerParamOfSecondGenericParam.getClassName().getClassName(), is("Integer"));
    }


    @Test
    public void reflectTypeListOfStrings() throws Exception {
        //Arrange
        List<String> instanceToInspect = new ArrayList<>();

        //Act
        final TypeDescriptor typeDescriptor = ClassReflector.reflectType(instanceToInspect.getClass());

        //Assert
        assertThat(typeDescriptor.getClassName().getClassName(), is("ArrayList"));
        assertThat(typeDescriptor.getClassPackage().getPackageName(), is("java.util"));
        assertThat(typeDescriptor.isArray(), is(Boolean.FALSE));
        assertThat(typeDescriptor.isPrimitive(), is(Boolean.FALSE));
        assertThat(typeDescriptor.getGenericParameters().isEmpty(), is(Boolean.TRUE)); //only not empty for fields!
    }

    @Test
    public void reflectTypeArrayOfStrings() throws Exception {
        //Arrange
        String [] instanceToInspect = new String [] {};

        //Act
        final TypeDescriptor typeDescriptor = ClassReflector.reflectType(instanceToInspect.getClass());

        //Assert
        assertThat(typeDescriptor.getClassName().getClassName(), is("String[]"));
        assertThat(typeDescriptor.getClassPackage().getPackageName(), is(""));
        assertThat(typeDescriptor.isArray(), is(Boolean.TRUE));
        assertThat(typeDescriptor.isPrimitive(), is(Boolean.FALSE));
        assertThat(typeDescriptor.getGenericParameters().isEmpty(), is(Boolean.TRUE));
    }


    private static void printClassInformation(Object instance) {
        Class<?> clazz = instance.getClass();
        System.out.println(String.format("-%s---------", clazz.getSimpleName()));
        System.out.println(String.format("class: %s", clazz));
        System.out.println(String.format("getName: %s", clazz.getName()));
        System.out.println(String.format("getSimpleName: %s", clazz.getSimpleName()));
        System.out.println(String.format("getPackage: %s", clazz.getPackage()));
        System.out.println(String.format("getPackageName: %s", clazz.getPackage() != null ? clazz.getPackage().getName() : null));
        System.out.println(String.format("getCanonicalName: %s", clazz.getCanonicalName()));
        System.out.println(String.format("getComponentType: %s", clazz.getComponentType()));
        System.out.println(String.format("getGenericInterfaces: %s", clazz.getGenericInterfaces().length));
        for (Type genericInterface : clazz.getGenericInterfaces()) {
            System.out.println(String.format(" - type: %s", genericInterface));
            System.out.println(String.format(" - type name: %s", genericInterface.getTypeName()));
        }
        System.out.println(String.format("getGenericInterfaces: %s", clazz.getGenericInterfaces().length));
        System.out.println(String.format("getGenericSuperclass: %s", clazz.getGenericSuperclass()));
        System.out.println(String.format("----------"));
        System.out.println(String.format("%s", structureDump(instance)));
        System.out.println(String.format("----------"));
    }

    private static final int MAX_DEEP = 2;

    private static String structureDump(Object o) {
        return structureDump(o, 0);
    }

    private static String structureDump(Object inspectedInstance, int depth) {
        Class inspectedClass = inspectedInstance.getClass();

        StringBuffer buffer = new StringBuffer();

        boolean isArray = inspectedClass.isArray();
        boolean isSimpleTypeOrPrimitive = isSimpleTypeOrPrimitive(inspectedInstance);

        appendLine(buffer, "isArray", isArray, depth);
        appendLine(buffer, "isSimpleTypeOrPrimitive", isSimpleTypeOrPrimitive, depth);

        if (!isArray) {
            appendLine(buffer, "name", inspectedClass.getSimpleName(), depth);

            if (isSimpleTypeOrPrimitive) {
                appendLine(buffer, "simple/primitive", "TODO simple type inspection is missing", depth);

            } else {
                Field[] fields = inspectedClass.getDeclaredFields();
                for (Field field : fields) {
                    appendLine(buffer, "field", "--------", depth);
                    appendLine(buffer, "field", field.getName(), depth + 1);
                    appendLine(buffer, "field-type", field.getType(), depth + 1);
                    final Type genericType = field.getGenericType();
                    appendLine(buffer, "field-generic-type", genericType, depth + 1);
                    appendLine(buffer, "field-generic-type-name", genericType.getTypeName(), depth + 1);
                    appendLine(buffer, "field-generic-type-name", field.toGenericString(), depth + 1);

                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType type = (ParameterizedType) genericType;
                        Type[] typeArguments = type.getActualTypeArguments();
                        for (Type typeArgument : typeArguments) {
                            appendLine(buffer, "type-arguments", typeArgument, depth + 2);
                        }
                    }
                }
            }
        } else {
            appendLine(buffer, "array", "TODO array inspection is missing", depth);
        }

        return buffer.toString();
    }

    private static void appendLine(StringBuffer buffer, String name, Object value, int depth) {
        String tabs = getSpaces(depth);
        buffer.append(String.format("%s%s:%s\n", tabs, name, value));

    }

    private static boolean isSimpleTypeOrPrimitive(Object inspectedInstance) {
        return (inspectedInstance.getClass().isPrimitive() ||
                inspectedInstance.getClass() == java.lang.Long.class ||
                inspectedInstance.getClass() == java.lang.String.class ||
                inspectedInstance.getClass() == java.lang.Integer.class ||
                inspectedInstance.getClass() == java.lang.Boolean.class
        );
    }

    private static String getSpaces(int depth) {
        StringBuffer tabs = new StringBuffer();
        for (int k = 0; k < depth; k++) {
            tabs.append("\t");
        }
        return tabs.toString();
    }
}