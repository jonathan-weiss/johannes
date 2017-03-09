package ch.johannes.plan;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

import java.util.List;

public class BeanSourcePlan {

    private final PackageDescriptor packageDescriptor;

    private final ClassnameDescriptor classnameDescriptor;

    private final List<FieldDescriptor> fieldDescriptors;

    public BeanSourcePlan(PackageDescriptor packageDescriptor, ClassnameDescriptor classnameDescriptor, List<FieldDescriptor> fieldDescriptors) {
        this.packageDescriptor = packageDescriptor;
        this.classnameDescriptor = classnameDescriptor;
        this.fieldDescriptors = fieldDescriptors;
    }

    public BeanSourcePlan(ClassDescriptor classDescriptor) {
        this(classDescriptor.getTypeDescriptor().getClassPackage(), classDescriptor.getTypeDescriptor().getClassName(), classDescriptor.getFields());
    }

    public PackageDescriptor getPackageDescriptor() {
        return packageDescriptor;
    }

    public ClassnameDescriptor getClassnameDescriptor() {
        return classnameDescriptor;
    }

    public List<FieldDescriptor> getFieldDescriptors() {
        return fieldDescriptors;
    }
}
