package ch.johannes.descriptor;

import java.util.ArrayList;
import java.util.List;

public class BeanDescriptorBuilder {

    private PackageDescriptor beanPackage;

    private ClassnameDescriptor beanName;

    private List<FieldDescriptor> beanFields = new ArrayList<>();

    public static BeanDescriptorBuilder with(ClassnameDescriptor beanName) {
        BeanDescriptorBuilder builder = new BeanDescriptorBuilder();
        return builder.setBeanName(beanName);
    }

    public BeanDescriptorBuilder setBeanPackage(PackageDescriptor beanPackage) {
        this.beanPackage = beanPackage;
        return this;
    }

    public BeanDescriptorBuilder setBeanName(ClassnameDescriptor beanName) {
        this.beanName = beanName;
        return this;
    }

    public BeanDescriptorBuilder setBeanFields(List<FieldDescriptor> beanFields) {
        this.beanFields = beanFields;
        return this;
    }

    public BeanDescriptorBuilder addBeanFields(List<FieldDescriptor> beanFields) {
        this.beanFields.addAll(beanFields);
        return this;
    }

    public BeanDescriptorBuilder addBeanField(FieldDescriptor beanField) {
        this.beanFields.add(beanField);
        return this;
    }

    public BeanDescriptor createBeanDescriptor() {
        return new BeanDescriptor(beanPackage, beanName, beanFields);
    }
}