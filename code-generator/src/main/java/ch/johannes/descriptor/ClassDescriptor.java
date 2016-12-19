package ch.johannes.descriptor;

import java.util.List;

public class ClassDescriptor {

    private final TypeDescriptor typeDescriptor;

    private final List<FieldDescriptor> fields;

    ClassDescriptor(TypeDescriptor typeDescriptor, List<FieldDescriptor> fields) {
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


