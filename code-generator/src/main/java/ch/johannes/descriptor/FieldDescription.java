package ch.johannes.descriptor;

public class FieldDescription {

    private final String fieldName;

    private final Class<?> fieldType;

    private FieldDescription(String fieldName, Class<?> fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public static FieldDescription of(String fieldName, Class<?> fieldType) {
        return new FieldDescription(fieldName, fieldType);
    }

    public String getFieldName() {
        return fieldName;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }
}
