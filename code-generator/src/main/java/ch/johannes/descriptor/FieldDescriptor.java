package ch.johannes.descriptor;

public class FieldDescriptor {

    private final String fieldName;

    private final TypeDescriptor fieldType;

    private FieldDescriptor(String fieldName, TypeDescriptor fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public static FieldDescriptor of(String fieldName, TypeDescriptor fieldType) {
        return new FieldDescriptor(fieldName, fieldType);
    }

    public String getFieldName() {
        return fieldName;
    }

    public TypeDescriptor getFieldType() {
        return fieldType;
    }
}
