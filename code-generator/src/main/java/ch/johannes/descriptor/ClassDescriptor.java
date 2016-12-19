package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

import java.util.List;

public class ClassDescriptor {

    private final TypeDescriptor typeDescriptor;

    private final List<FieldDescriptor> fields;

    ClassDescriptor(TypeDescriptor typeDescriptor, List<FieldDescriptor> fields) {
        Preconditions.checkNotNull(typeDescriptor, "type must not be null.");
        Preconditions.checkNotNull(fields, "fields must not be null.");
        this.typeDescriptor = typeDescriptor;
        this.fields = fields;
    }

    public List<FieldDescriptor> getFields() {
        return fields;
    }

    public TypeDescriptor getTypeDescriptor() {
        return typeDescriptor;
    }
}


