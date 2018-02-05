package ch.johannes.descriptor;

import ch.johannes.CollectionUtil;
import ch.johannes.reflector.ClassReflector;
import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;

public class TypeDescriptor implements Typed, Descriptor{

    public static final boolean IS_ARRAY = true;

    public static final boolean IS_NOT_ARRAY = false;

    public static final boolean IS_PRIMITIVE = true;

    public static final boolean IS_NOT_PRIMITIVE = false;

    private final PackageDescriptor classPackage;

    private final ClassnameDescriptor className;

    private final List<TypeDescriptor> genericParameters; //TODO use comparable (unmodifiable) list

    private final boolean array; //default

    private final boolean primitive; //default

    /**
     * private constructor.
     * For construction, use factory methods
     */
    private TypeDescriptor(PackageDescriptor classPackage, ClassnameDescriptor className) {
        this(classPackage, className, false, false, Collections.emptyList());
    }

    /**
     * private constructor.
     * For construction, use factory methods
     */
    private TypeDescriptor(PackageDescriptor classPackage, ClassnameDescriptor className, boolean isArray, boolean isPrimitive, List<TypeDescriptor> genericParameters) {
        Preconditions.checkNotNull(classPackage, "package must not be null.");
        Preconditions.checkNotNull(className, "classname must not be null.");
        Preconditions.checkNotNull(genericParameters, "genericParameters must not be null.");
        this.classPackage = classPackage;
        this.className = className;
        this.array = isArray;
        this.primitive = isPrimitive;
        this.genericParameters = Collections.unmodifiableList(genericParameters);
    }

    /**
     * Factory method
     */
    public static TypeDescriptor of(PackageDescriptor packageDescriptor, ClassnameDescriptor classnameDescriptor, boolean isArray, boolean isPrimitive, List<TypeDescriptor> genericParameters) {
        return new TypeDescriptor(packageDescriptor, classnameDescriptor, isArray, isPrimitive, genericParameters);
    }

    /**
     * Factory method
     */
    public static TypeDescriptor of(PackageDescriptor packageDescriptor, ClassnameDescriptor classnameDescriptor) {
        return new TypeDescriptor(packageDescriptor, classnameDescriptor);
    }

    /**
     * Factory method
     */
    public static TypeDescriptor of(Class<?> clazz) {
        return ClassReflector.reflectClass(clazz).getTypeDescriptor();
    }
    /**
     * Factory method
     */
    public static TypeDescriptor of(String packageName, String className) {
        return new TypeDescriptor(PackageDescriptor.of(packageName), ClassnameDescriptor.of(className));
    }


    /**
     * Prototype method
     */
    public TypeDescriptor with(PackageDescriptor packageDescriptor) {
        return new TypeDescriptor(packageDescriptor, this.getClassName(), this.isArray(), this.isPrimitive(), this.getGenericParameters());
    }

    /**
     * Prototype method
     */
    public TypeDescriptor withPackage(String packageName) {
        return new TypeDescriptor(PackageDescriptor.of(packageName), this.getClassName(), this.isArray(), this.isPrimitive(), this.getGenericParameters());
    }

    /**
     * Prototype method
     */
    public TypeDescriptor with(ClassnameDescriptor classnameDescriptor) {
        return new TypeDescriptor(this.getClassPackage(), classnameDescriptor, this.isArray(), this.isPrimitive(), this.getGenericParameters());
    }

    /**
     * Prototype method
     */
    public TypeDescriptor withClassname(String className) {
        return new TypeDescriptor(this.getClassPackage(), ClassnameDescriptor.of(className), this.isArray(), this.isPrimitive(), this.getGenericParameters());
    }

    /**
     * Prototype method
     */
    public TypeDescriptor withArray(boolean isArray) {
        return new TypeDescriptor(this.getClassPackage(), this.getClassName(), isArray, this.isPrimitive(), this.getGenericParameters());
    }

    /**
     * Prototype method
     */
    public TypeDescriptor withPrimitive(boolean isPrimitive) {
        return new TypeDescriptor(this.getClassPackage(), this.getClassName(), this.isArray(), isPrimitive, this.getGenericParameters());
    }

    /**
     * Prototype method
     */
    public TypeDescriptor addGenericParameter(TypeDescriptor genericParameter) {
        return new TypeDescriptor(this.getClassPackage(), this.getClassName(), this.isArray(), this.isPrimitive(), CollectionUtil.listOf(this.getGenericParameters(), genericParameter));
    }

    /**
     * Prototype method
     */
    public TypeDescriptor addGenericParameters(List<TypeDescriptor> genericParameters) {
        return new TypeDescriptor(this.getClassPackage(), this.getClassName(), this.isArray(), this.isPrimitive(), CollectionUtil.listOf(this.getGenericParameters(), genericParameters));
    }

    /**
     * Prototype method
     */
    public TypeDescriptor withGenericParameters(List<TypeDescriptor> genericParameters) {
        return new TypeDescriptor(this.getClassPackage(), this.getClassName(), this.isArray(), this.isPrimitive(), genericParameters);
    }

    public PackageDescriptor getClassPackage() {
        return classPackage;
    }

    public ClassnameDescriptor getClassName() {
        return className;
    }

    public boolean isArray() {
        return array;
    }

    public boolean isPrimitive() {
        return primitive;
    }

    public List<TypeDescriptor> getGenericParameters() {
        return genericParameters;
    }

    @Override
    public String toString() {
        return "TypeDescriptor{" +
                "classPackage=" + classPackage +
                ", className=" + className +
                ", genericParameters=" + genericParameters +
                ", array=" + array +
                ", primitive=" + primitive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeDescriptor that = (TypeDescriptor) o;

        if (array != that.array) {
            return false;
        }
        if (primitive != that.primitive) {
            return false;
        }
        if (!classPackage.equals(that.classPackage)) {
            return false;
        }
        if (!className.equals(that.className)) {
            return false;
        }
        return genericParameters.equals(that.genericParameters);

    }

    @Override
    public int hashCode() {
        int result = classPackage.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + genericParameters.hashCode();
        result = 31 * result + (array ? 1 : 0);
        result = 31 * result + (primitive ? 1 : 0);
        return result;
    }
}


