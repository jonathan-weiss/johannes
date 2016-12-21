package ch.johannes.reflector;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClassReflectorFieldTest {

    @Test
    public void reflectGenericAndArray() throws Exception {
        //Arrange
        class MyTestClassToReflectGenericAndArray {
            private List<String>[] genericAndArray;
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
        //array
        assertThat(fieldDescriptor.getFieldName(), is("genericAndArray"));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.TRUE));

        //type of array
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("List"));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.FALSE));

        //inner generic params
        assertThat(fieldDescriptor.getFieldType().getGenericParameters().size(), is(1));
        final TypeDescriptor firstGenericParamOfList = fieldDescriptor.getFieldType().getGenericParameters().get(0);
        assertThat(firstGenericParamOfList.getClassName().getClassName(), is("String"));
        assertThat(firstGenericParamOfList.getGenericParameters().size(), is(0));

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
            private String[] myArray;
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
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("String"));
        assertThat(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), is("java.lang"));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.TRUE));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.FALSE));
    }

    @Test
    public void reflectPrimitiveArray() throws Exception {
        //Arrange
        class MyTestClassToReflectPrimitiveArray {
            private char[] myArray;
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
        assertThat(fieldDescriptor.getFieldType().getClassName().getClassName(), is("char"));
        assertThat(fieldDescriptor.getFieldType().getClassPackage().getPackageName(), is(""));
        assertThat(fieldDescriptor.getFieldType().isArray(), is(Boolean.TRUE));
        assertThat(fieldDescriptor.getFieldType().isPrimitive(), is(Boolean.TRUE));
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
    public void reflectClassWithMiscClass() throws Exception {
        class MyTestClassToReflectMixedFields {
            private final int number = 0;
            private String text = "";
            private List<String> tokenList;
            private String [] tokenArray;
            private List<String> [] tokenListArray;
            private List<List<Class<String>>> genericInGeneric;
            private MyTestClassToReflectMixedFields recursive;
        }

        //Arrange
        Class<?> clazzString = MyTestClassToReflectMixedFields.class;

        //Act
        final ClassDescriptor classDescriptorForString = ClassReflector.reflectClass(clazzString);

        //Assert
        assertThat(classDescriptorForString.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflectMixedFields"));
        assertThat(classDescriptorForString.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));

        final TypeDescriptor stringTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, Collections.emptyList());
        final TypeDescriptor arrayOfStringTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String"), TypeDescriptor.IS_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, Collections.emptyList());
        final TypeDescriptor listOfStringTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of("java.util"), ClassnameDescriptor.of("List"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, Collections.singletonList(stringTypeDescriptor));
        final TypeDescriptor arrayOfListOfStringTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of("java.util"), ClassnameDescriptor.of("List"), TypeDescriptor.IS_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, Collections.singletonList(stringTypeDescriptor));
        final TypeDescriptor primitiveIntTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of(""), ClassnameDescriptor.of("int"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_PRIMITIVE, Collections.emptyList());
        final TypeDescriptor classOfStringTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("Class"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, Collections.singletonList(stringTypeDescriptor));
        final TypeDescriptor listOfClassOfStringTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of("java.util"), ClassnameDescriptor.of("List"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, Collections.singletonList(classOfStringTypeDescriptor));
        final TypeDescriptor listOfListOfClassOfStringTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of("java.util"), ClassnameDescriptor.of("List"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, Collections.singletonList(listOfClassOfStringTypeDescriptor));
        final TypeDescriptor myTestClassToReflectTypeDescriptor = TypeDescriptor.of(PackageDescriptor.of(this.getClass().getPackage().getName()), ClassnameDescriptor.of("MyTestClassToReflectMixedFields"), TypeDescriptor.IS_NOT_ARRAY, TypeDescriptor.IS_NOT_PRIMITIVE, Collections.emptyList());

        FieldDescriptor primitiveIntDescriptor = FieldDescriptor.of("number", primitiveIntTypeDescriptor);
        FieldDescriptor stringDescriptor = FieldDescriptor.of("text", stringTypeDescriptor);
        FieldDescriptor listOfStringDescriptor = FieldDescriptor.of("tokenList", listOfStringTypeDescriptor);
        FieldDescriptor arrayOfStringDescriptor = FieldDescriptor.of("tokenArray", arrayOfStringTypeDescriptor);
        FieldDescriptor arrayOfListOfStringDescriptor = FieldDescriptor.of("tokenListArray", arrayOfListOfStringTypeDescriptor);
        FieldDescriptor listOfListOfClassOfStringDescriptor = FieldDescriptor.of("genericInGeneric", listOfListOfClassOfStringTypeDescriptor);
        FieldDescriptor myTestClassToReflectDescriptor = FieldDescriptor.of("recursive", myTestClassToReflectTypeDescriptor);
        assertThat(classDescriptorForString.getFields(), containsInAnyOrder(
                primitiveIntDescriptor,
                stringDescriptor,
                listOfStringDescriptor,
                arrayOfStringDescriptor,
                arrayOfListOfStringDescriptor,
                listOfListOfClassOfStringDescriptor,
                myTestClassToReflectDescriptor));
    }

}