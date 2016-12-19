package ch.johannes.descriptor;

public class FieldDescriptor {

    private final String fieldName;

    private final Class<?> fieldType;

    private FieldDescriptor(String fieldName, Class<?> fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public static FieldDescriptor of(String fieldName, Class<?> fieldType) {
        return new FieldDescriptor(fieldName, fieldType);
    }

    public String getFieldName() {
        return fieldName;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }
}
