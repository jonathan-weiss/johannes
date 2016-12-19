package ch.johannes.descriptor;

import java.util.List;

public class BeanDescriptor {

    private final PackageDescriptor beanPackage;

    private final ClassnameDescriptor beanName;

    private final List<FieldDescriptor> beanFields;

    BeanDescriptor(PackageDescriptor beanPackage, ClassnameDescriptor beanName, List<FieldDescriptor> beanFields) {
        this.beanPackage = beanPackage;
        this.beanName = beanName;
        this.beanFields = beanFields;
    }

    public PackageDescriptor getBeanPackage() {
        return beanPackage;
    }

    public ClassnameDescriptor getBeanName() {
        return beanName;
    }

    public List<FieldDescriptor> getBeanFields() {
        return beanFields;
    }
}


