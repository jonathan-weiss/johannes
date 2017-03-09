package ch.johannes.plan;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

import java.util.List;

public class ImmutableBeanSourcePlan extends BeanSourcePlan {

    public ImmutableBeanSourcePlan(PackageDescriptor packageDescriptor, ClassnameDescriptor classnameDescriptor, List<FieldDescriptor> fieldDescriptors) {
        super(packageDescriptor, classnameDescriptor, fieldDescriptors);
    }

    public ImmutableBeanSourcePlan(ClassDescriptor classDescriptor) {
        super(classDescriptor);
    }
}
