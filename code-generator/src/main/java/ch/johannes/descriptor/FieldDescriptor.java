package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

public class FieldDescriptor implements Descriptor {

    private final String fieldName;

    private final TypeDescriptor fieldType;

    /**
     * private constructor.
     * For construction, use factory method {@link #of(String, TypeDescriptor)}
     */

    private FieldDescriptor(String fieldName, TypeDescriptor fieldType) {
        Preconditions.checkNotNull(fieldName, "field name must not be null.");
        Preconditions.checkState(!fieldName.isEmpty(), "field name must not be empty.");
        Preconditions.checkNotNull(fieldType, "field type must not be null.");

        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    /**
     * Factory method
     */
    public static FieldDescriptor of(String fieldName, TypeDescriptor fieldType) {
        return new FieldDescriptor(fieldName, fieldType);
    }

    /**
     * Prototype method
     */
    public FieldDescriptor with(String fieldName) {
        return new FieldDescriptor(fieldName, this.getFieldType());
    }

    /**
     * Prototype method
     */
    public FieldDescriptor with(TypeDescriptor fieldType) {
        return new FieldDescriptor(this.getFieldName(), fieldType);
    }

    public String getFieldName() {
        return fieldName;
    }

    public TypeDescriptor getFieldType() {
        return fieldType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FieldDescriptor that = (FieldDescriptor) o;

        if (!fieldName.equals(that.fieldName)) {
            return false;
        }
        return fieldType.equals(that.fieldType);

    }

    @Override
    public int hashCode() {
        int result = fieldName.hashCode();
        result = 31 * result + fieldType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FieldDescriptor{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType=" + fieldType +
                '}';
    }
}
