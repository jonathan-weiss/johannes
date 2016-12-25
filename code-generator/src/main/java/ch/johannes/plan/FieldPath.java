package ch.johannes.plan;

import ch.johannes.CollectionUtil;
import ch.johannes.descriptor.FieldDescriptor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FieldPath implements SourceFieldPath, TargetFieldPath {

    private final List<FieldDescriptor> fieldDescriptors;

    public FieldPath(List<FieldDescriptor> fieldDescriptors) {
        this.fieldDescriptors = Collections.unmodifiableList(fieldDescriptors);
    }

    public FieldPath(FieldDescriptor fieldDescriptor) {
        this.fieldDescriptors = Collections.unmodifiableList(Collections.singletonList(fieldDescriptor));
    }

    public FieldPath addField(FieldDescriptor fieldDescriptor) {
        return new FieldPath(CollectionUtil.listOf(this.fieldDescriptors, fieldDescriptor));
    }

    public FieldPath addFields(Collection<FieldDescriptor> fieldDescriptors) {
        return new FieldPath(CollectionUtil.listOf(this.fieldDescriptors, fieldDescriptors));
    }

    public List<FieldDescriptor> getFieldDescriptors() {
        return fieldDescriptors;
    }
}
