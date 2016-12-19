package ch.johannes.cg;

public class BeanUtil {

    public static String createGetterName(String fieldname) {
        return "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
    }

    public static String createSetterName(String fieldname) {
        return "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
    }

}
