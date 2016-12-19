package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

public class FieldDescriptor {

    private final String fieldName;

    private final TypeDescriptor fieldType;

    private FieldDescriptor(String fieldName, TypeDescriptor fieldType) {
        Preconditions.checkNotNull(fieldName, "field name must not be null.");
        Preconditions.checkState(!fieldName.isEmpty(), "field name must not be empty.");
        Preconditions.checkNotNull(fieldType, "field type must not be null.");

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
