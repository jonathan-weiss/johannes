package ch.johannes;

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

        String firstLetter = camelCaseName.substring(0, 1).toUpperCase();
        return firstLetter + camelCaseName.substring(1).replaceAll("([A-Z]{1}[^A-Z]+)", "_$1").toUpperCase();
    }


}
