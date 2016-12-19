package ch.johannes.descriptor;

import java.util.List;

public class BeanDescriptor {

    private final String targetBeanPackage;

    private final String targetBeanName;

    private final List<FieldDescription> targetFields;

    public BeanDescriptor(String targetBeanPackage, String targetBeanName, List<FieldDescription> targetFields) {
        this.targetBeanPackage = targetBeanPackage;
        this.targetBeanName = targetBeanName;
        this.targetFields = targetFields;
    }

    public String getTargetBeanPackage() {
        return targetBeanPackage;
    }

    public String getTargetBeanName() {
        return targetBeanName;
    }

    public List<FieldDescription> getTargetFields() {
        return targetFields;
    }
}


