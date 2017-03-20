package ch.johannes.metadata;

public class JavaNameUtil {

    public static String fieldNameToConstant(String fieldname) {
        return camelCaseNameToConstant(fieldname);
    }

    public static String classNameToConstant(String className) {
        return camelCaseNameToConstant(className);
    }

    private static String camelCaseNameToConstant(String camelCaseName) {
        if(camelCaseName == null || camelCaseName.isEmpty()) {
            return camelCaseName;
        }

        return camelCaseName.replaceAll("([a-z]{1})([A-Z]{1})", "$1_$2").toUpperCase();
    }


}
