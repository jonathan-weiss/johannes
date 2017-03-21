package ch.johannes.core;

public class BeanUtil {

    public static String prefixAndSuffixName(String fieldname, String prefix, String suffix) {
        return prefix + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + suffix;
    }

    public static String prefixName(String fieldname, String prefix) {
        return prefixAndSuffixName(fieldname, prefix, "");
    }


    public static String createGetterName(String fieldname) {
        return prefixName(fieldname, "get");

    }

    public static String createSetterName(String fieldname) {
        return prefixName(fieldname, "set");
    }

}
