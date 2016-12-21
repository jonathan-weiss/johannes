package ch.johannes.plan;

import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;

public abstract class AbstractBeanCreationPlan implements Plan{

    protected void withField(FieldDescriptor fieldDescriptor) {

    }

    protected void withClassname(String className) {
    }

    protected void inPackage(PackageDescriptor packageDescriptor) {

    }

}
