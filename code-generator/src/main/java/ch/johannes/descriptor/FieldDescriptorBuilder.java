package ch.johannes.descriptor;

public class FieldDescriptorBuilder implements DescriptorBuilder<FieldDescriptor>{

    private String fieldName;

    private TypeDescriptor fieldType;

    private FieldDescriptorBuilder() {
        //private constructor
    }

    public static FieldDescriptorBuilder with() {
        return new FieldDescriptorBuilder();
    }

    public static FieldDescriptorBuilder with(FieldDescriptor fieldDescriptor) {
        if(fieldDescriptor == null) {
            return with();
        }

        return with()
                .setFieldName(fieldDescriptor.getFieldName())
                .setFieldType(fieldDescriptor.getFieldType());
    }

    public static FieldDescriptorBuilder with(String fieldName) {
        return with()
                .setFieldName(fieldName);
    }

    public static FieldDescriptorBuilder with(String fieldName, TypeDescriptor typeDescriptor) {
        return with(fieldName)
                .setFieldType(typeDescriptor);
    }



    public FieldDescriptorBuilder setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public FieldDescriptorBuilder setFieldType(TypeDescriptor fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public FieldDescriptor build() {
        return FieldDescriptor.of(fieldName, fieldType);
    }
}