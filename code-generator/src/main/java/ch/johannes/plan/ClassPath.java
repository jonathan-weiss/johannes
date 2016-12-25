package ch.johannes.plan;

import ch.johannes.descriptor.ClassDescriptor;

public class ClassPath {

    private final ClassDescriptor classDescriptor;

    public ClassPath(ClassDescriptor classDescriptor) {
        this.classDescriptor = classDescriptor;
    }

    public ClassDescriptor getClassDescriptor() {
        return classDescriptor;
    }
}
