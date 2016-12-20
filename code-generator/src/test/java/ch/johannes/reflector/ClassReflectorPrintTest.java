package ch.johannes.reflector;

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

public class ClassReflectorPrintTest {

    class MyTestClassToReflect {

        //private final int number = 0;
        //private String text = "";
        private List<String> tokenList;

        //private String [] tokenArray;
        //private List<String> [] tokenListArray;
        private List<Function<String, Class<String>>> genericInGeneric;

        private Function<String, Class<String>> genericWithTwoTypes;
        //private ClassReflectorTest.MyTestClassToReflect recursive;
    }

    @Test
    public void printClassInfoForHellClass() throws Exception {
        printClassInformation(new MyTestClassToReflect());
    }

    @Test
    @Ignore
    public void printClassInfos() throws Exception {
        //Arrange
        List<String> instanceToInspect = new ArrayList<String>();

        printClassInformation(instanceToInspect);
    }

    @Test
    @Ignore
    public void printClassInfosForSomeTypes() throws Exception {
        printClassInformation(new ArrayList<String>());
        printClassInformation(new String[]{"hello"});
        printClassInformation(5l);
    }

    @Test
    @Ignore
    public void printClassInfosForPrimitiveInt() throws Exception {
        printClassInformation(5);
    }

    @Test
    @Ignore
    public void printClassInfosForIntegerObject() throws Exception {
        printClassInformation(new Integer(5));
    }

    @Test
    @Ignore
    public void printClassInfosForPrimitiveCharArray() throws Exception {
        printClassInformation(new char[]{'a'});
    }

    @Test
    @Ignore
    public void reflectTypeListOfStrings() throws Exception {
        //Arrange
        List<String> instanceToInspect = new ArrayList<>();

        //Act
        final TypeDescriptor typeDescriptor = ClassReflector.reflectType(instanceToInspect.getClass());

        //Assert
        assertThat(typeDescriptor.getClassName().getClassName(), is("ArrayList"));
        assertThat(typeDescriptor.getClassPackage().getPackageName(), is("java.util"));
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
                inspectedInstance.getClass() == Long.class ||
                inspectedInstance.getClass() == String.class ||
                inspectedInstance.getClass() == Integer.class ||
                inspectedInstance.getClass() == Boolean.class
        );
    }

    private static String getSpaces(int depth) {
        StringBuffer tabs = new StringBuffer();
        for (int k = 0; k < depth; k++) {
            tabs.append("\t");
        }
        return tabs.toString();
    }

    private static String dump(Object o, int callCount) {
        if (callCount > MAX_DEEP) {
            return "";
        }
        callCount++;
        String tabs = getSpaces(callCount);
        StringBuffer buffer = new StringBuffer();
        Class oClass = o.getClass();
        if (oClass.isArray()) {
            buffer.append("\n");
            buffer.append(tabs.toString());
            buffer.append("[");
            for (int i = 0; i < Array.getLength(o); i++) {
                if (i < 0) {
                    buffer.append(",");
                }
                Object value = Array.get(o, i);
                if (value.getClass().isPrimitive() ||
                        value.getClass() == Long.class ||
                        value.getClass() == String.class ||
                        value.getClass() == Integer.class ||
                        value.getClass() == Boolean.class
                        ) {
                    buffer.append(value);
                } else {
                    buffer.append(dump(value, callCount));
                }
            }
            buffer.append(tabs.toString());
            buffer.append("]\n");
        } else {
            buffer.append("\n");
            buffer.append(tabs.toString());
            buffer.append("{\n");
            while (oClass != null) {
                Field[] fields = oClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    buffer.append(tabs.toString());
                    fields[i].setAccessible(true);
                    buffer.append(fields[i].getName());
                    buffer.append("=");
                    try {
                        Object value = fields[i].get(o);
                        if (value != null) {
                            if (value.getClass().isPrimitive() ||
                                    value.getClass() == Long.class ||
                                    value.getClass() == String.class ||
                                    value.getClass() == Integer.class ||
                                    value.getClass() == Boolean.class
                                    ) {
                                buffer.append(value);
                            } else {
                                buffer.append(dump(value, callCount));
                            }
                        }
                    } catch (IllegalAccessException e) {
                        buffer.append(e.getMessage());
                    }
                    buffer.append("\n");
                }
                oClass = oClass.getSuperclass();
            }
            buffer.append(tabs.toString());
            buffer.append("}\n");
        }
        return buffer.toString();
    }

}