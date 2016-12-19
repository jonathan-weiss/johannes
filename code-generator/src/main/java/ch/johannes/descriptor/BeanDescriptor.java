package ch.johannes.descriptor;

import java.util.List;

public class BeanDescriptor {

    private final PackageDescriptor targetBeanPackage;

    private final ClassnameDescriptor targetBeanName;

    private final List<FieldDescriptor> targetFields;

    public BeanDescriptor(PackageDescriptor targetBeanPackage, ClassnameDescriptor targetBeanName, List<FieldDescriptor> targetFields) {
        this.targetBeanPackage = targetBeanPackage;
        this.targetBeanName = targetBeanName;
        this.targetFields = targetFields;
    }

    public PackageDescriptor getTargetBeanPackage() {
        return targetBeanPackage;
    }

    public ClassnameDescriptor getTargetBeanName() {
        return targetBeanName;
    }

    public List<FieldDescriptor> getTargetFields() {
        return targetFields;
    }
}


