package ch.johannes.descriptor;

/**
 * Common type as static constant fields
 */
public class Descriptors {

    public static final PackageDescriptor JAVA_LANG_PACKAGE_DESCRIPTOR = PackageDescriptor.of("java.lang");
    public static final PackageDescriptor JAVA_UTIL_PACKAGE_DESCRIPTOR = PackageDescriptor.of("java.util");

    public static final TypeDescriptor STRING_TYPE_DESCRIPTOR = TypeDescriptor.of(JAVA_LANG_PACKAGE_DESCRIPTOR, ClassnameDescriptor.of("String"));
    public static final TypeDescriptor INTEGER_TYPE_DESCRIPTOR = TypeDescriptor.of(JAVA_LANG_PACKAGE_DESCRIPTOR, ClassnameDescriptor.of("Integer"));
    public static final TypeDescriptor LONG_TYPE_DESCRIPTOR = TypeDescriptor.of(JAVA_LANG_PACKAGE_DESCRIPTOR, ClassnameDescriptor.of("Long"));

    public static final TypeDescriptor LIST_TYPE_DESCRIPTOR = TypeDescriptor.of(JAVA_UTIL_PACKAGE_DESCRIPTOR, ClassnameDescriptor.of("List"));
    public static final TypeDescriptor MAP_TYPE_DESCRIPTOR = TypeDescriptor.of(JAVA_UTIL_PACKAGE_DESCRIPTOR, ClassnameDescriptor.of("Map"));

}
