package ch.johannes.descriptor;

import java.util.List;

public class BeanDescriptor {

    private final PackageDescriptor targetBeanPackage;

    private final String targetBeanName;

    private final List<FieldDescriptor> targetFields;

    public BeanDescriptor(PackageDescriptor targetBeanPackage, String targetBeanName, List<FieldDescriptor> targetFields) {
        this.targetBeanPackage = targetBeanPackage;
        this.targetBeanName = targetBeanName;
        this.targetFields = targetFields;
    }

    public PackageDescriptor getTargetBeanPackage() {
        return targetBeanPackage;
    }

    public String getTargetBeanName() {
        return targetBeanName;
    }

    public List<FieldDescriptor> getTargetFields() {
        return targetFields;
    }
}


