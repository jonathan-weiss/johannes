package ch.johannes;

public class FieldNameUtil {

    public static String fieldNameToConstant(String fieldname) {
        if(fieldname == null || fieldname.isEmpty()) {
            return fieldname;
        }

        String firstLetter = fieldname.substring(0, 1).toUpperCase();
        return firstLetter + fieldname.substring(1).replaceAll("([A-Z]{1}[^A-Z]+)", "_$1").toUpperCase();
    }
}
