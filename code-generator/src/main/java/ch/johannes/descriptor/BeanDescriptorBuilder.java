package ch.johannes.descriptor;

import java.util.ArrayList;
import java.util.List;

public class BeanDescriptorBuilder {

    private String targetBeanPackage;

    private String targetBeanName;

    private List<FieldDescriptor> targetFields = new ArrayList<>();

    public static BeanDescriptorBuilder with(String targetBeanName) {
        BeanDescriptorBuilder builder = new BeanDescriptorBuilder();
        return builder.setTargetBeanName(targetBeanName);
    }

    public BeanDescriptorBuilder setTargetBeanPackage(String targetBeanPackage) {
        this.targetBeanPackage = targetBeanPackage;
        return this;
    }


    public BeanDescriptorBuilder setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
        return this;
    }

    public BeanDescriptorBuilder setTargetFields(List<FieldDescriptor> targetFields) {
        this.targetFields = targetFields;
        return this;
    }

    public BeanDescriptorBuilder addTargetFields(List<FieldDescriptor> targetFields) {
        this.targetFields.addAll(targetFields);
        return this;
    }

    public BeanDescriptorBuilder addTargetField(FieldDescriptor targetField) {
        this.targetFields.add(targetField);
        return this;
    }

    public BeanDescriptor createBeanDescriptor() {
        return new BeanDescriptor(targetBeanPackage, targetBeanName, targetFields);
    }
}