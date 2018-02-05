package ch.johannes.descriptor;

import ch.johannes.reflector.ClassReflector;
import com.google.common.base.Preconditions;

public class MethodDescriptor implements Descriptor {

    private final String methodName;

    private final TypeDescriptor methodReturnType;

    /**
     * private constructor.
     * For construction, use factory method {@link #of(String, TypeDescriptor)}
     */

    private MethodDescriptor(String methodName, TypeDescriptor methodReturnType) {
        Preconditions.checkNotNull(methodName, "field name must not be null.");
        Preconditions.checkState(!methodName.isEmpty(), "field name must not be empty.");
        Preconditions.checkNotNull(methodReturnType, "field type must not be null.");

        this.methodName = methodName;
        this.methodReturnType = methodReturnType;
    }

    /**
     * Factory method
     */
    public static MethodDescriptor of(String fieldName, TypeDescriptor fieldType) {
        return new MethodDescriptor(fieldName, fieldType);
    }

    /**
     * Factory method
     */
    public static MethodDescriptor of(String fieldName, Class<?> clazz) {
        final ClassDescriptor classDescriptor = ClassReflector.reflectClass(clazz);
        return of(fieldName, classDescriptor.getTypeDescriptor());
    }


    /**
     * Prototype method
     */
    public MethodDescriptor with(String fieldName) {
        return new MethodDescriptor(fieldName, this.getMethodReturnType());
    }

    /**
     * Prototype method
     */
    public MethodDescriptor with(TypeDescriptor fieldType) {
        return new MethodDescriptor(this.getMethodName(), fieldType);
    }

    public String getMethodName() {
        return methodName;
    }

    public TypeDescriptor getMethodReturnType() {
        return methodReturnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MethodDescriptor that = (MethodDescriptor) o;

        if (!methodName.equals(that.methodName)) {
            return false;
        }
        return methodReturnType.equals(that.methodReturnType);

    }

    @Override
    public int hashCode() {
        int result = methodName.hashCode();
        result = 31 * result + methodReturnType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FieldDescriptor{" +
                "methodName='" + methodName + '\'' +
                ", methodReturnType=" + methodReturnType +
                '}';
    }
}
