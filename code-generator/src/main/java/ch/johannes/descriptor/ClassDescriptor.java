package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

import java.util.List;

public class ClassDescriptor {

    private final TypeDescriptor typeDescriptor;

    private final List<FieldDescriptor> fields;

    /**
     * private constructor.
     * For construction, use factory method {@link ClassDescriptor#of(TypeDescriptor, List)}
     */
    private ClassDescriptor(TypeDescriptor typeDescriptor, List<FieldDescriptor> fields) {
        Preconditions.checkNotNull(typeDescriptor, "type must not be null.");
        Preconditions.checkNotNull(fields, "fields must not be null.");
        this.typeDescriptor = typeDescriptor;
        this.fields = fields;
    }

    public static ClassDescriptor of(TypeDescriptor typeDescriptor, List<FieldDescriptor> fields) {
        return new ClassDescriptor(typeDescriptor, fields);
    }

    public List<FieldDescriptor> getFields() {
        return fields;
    }

    public TypeDescriptor getTypeDescriptor() {
        return typeDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClassDescriptor that = (ClassDescriptor) o;

        return typeDescriptor.equals(that.typeDescriptor);

    }

    @Override
    public int hashCode() {
        return typeDescriptor.hashCode();
    }

    @Override
    public String toString() {
        return "ClassDescriptor{" +
                "typeDescriptor=" + typeDescriptor +
                ", fields=" + fields +
                '}';
    }
}


