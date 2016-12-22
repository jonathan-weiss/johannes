package ch.johannes.descriptor;

import ch.johannes.CollectionUtil;
import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;

public class ClassDescriptor {

    private final TypeDescriptor typeDescriptor;

    private final List<FieldDescriptor> fields;

    /**
     * private constructor.
     * For construction, use factory method
     */
    private ClassDescriptor(TypeDescriptor typeDescriptor, List<FieldDescriptor> fields) {
        Preconditions.checkNotNull(typeDescriptor, "type must not be null.");
        Preconditions.checkNotNull(fields, "fields must not be null.");
        this.typeDescriptor = typeDescriptor;
        this.fields = fields;
    }

    /**
     * Factory method
     */
    public static ClassDescriptor of(TypeDescriptor typeDescriptor, List<FieldDescriptor> fields) {
        return new ClassDescriptor(typeDescriptor, fields);
    }

    /**
     * Factory method
     */
    public static ClassDescriptor of(TypeDescriptor typeDescriptor) {
        return new ClassDescriptor(typeDescriptor, Collections.emptyList());
    }

    /**
     * Factory method
     */
    public static ClassDescriptor of(String packageName, String className) {
        return ClassDescriptor.of(TypeDescriptor.of(packageName, className));
    }

    /**
     * Factory method
     */
    public static ClassDescriptor of(PackageDescriptor packageDescriptor, String className) {
        return ClassDescriptor.of(TypeDescriptor.of(packageDescriptor, ClassnameDescriptor.of(className)));
    }


    /**
     * Prototype method
     */
    public ClassDescriptor with(TypeDescriptor typeDescriptor) {
        return new ClassDescriptor(typeDescriptor, this.fields);
    }


    /**
     * Prototype method
     */
    public ClassDescriptor addField(FieldDescriptor fieldDescriptor) {
        return new ClassDescriptor(this.typeDescriptor, CollectionUtil.listOf(this.fields, fieldDescriptor));
    }

    /**
     * Prototype method
     */
    public ClassDescriptor addFields(List<FieldDescriptor> fieldDescriptors) {
        return new ClassDescriptor(this.typeDescriptor, CollectionUtil.listOf(this.fields, fieldDescriptors));
    }

    /**
     * Prototype method
     */
    public ClassDescriptor withFields(List<FieldDescriptor> fieldDescriptors) {
        return new ClassDescriptor(this.typeDescriptor, fieldDescriptors);
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


