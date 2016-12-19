package ch.johannes.descriptor;

import java.util.ArrayList;
import java.util.List;

public class BeanDescriptorBuilder {

    private String targetBeanPackage;

    private String targetBeanName;

    private List<FieldDescription> targetFields = new ArrayList<>();

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

    public BeanDescriptorBuilder setTargetFields(List<FieldDescription> targetFields) {
        this.targetFields = targetFields;
        return this;
    }

    public BeanDescriptorBuilder addTargetFields(List<FieldDescription> targetFields) {
        this.targetFields.addAll(targetFields);
        return this;
    }

    public BeanDescriptorBuilder addTargetField(FieldDescription targetField) {
        this.targetFields.add(targetField);
        return this;
    }

    public BeanDescriptor createBeanDescriptor() {
        return new BeanDescriptor(targetBeanPackage, targetBeanName, targetFields);
    }
}