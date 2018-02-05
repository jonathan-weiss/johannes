package ch.johannes.descriptor;

import com.google.common.base.Preconditions;

public abstract class VariableDescriptor<T extends VariableDescriptor> implements Descriptor {

    private final String variableName;

    private final TypeDescriptor variableType;

    /**
     * private constructor.
     * For construction, use factory methods
     */
    protected VariableDescriptor(String variableName, TypeDescriptor variableType) {
        Preconditions.checkNotNull(variableName, "variable name must not be null.");
        Preconditions.checkState(!variableName.isEmpty(), "variable name must not be empty.");
        Preconditions.checkNotNull(variableType, "variable type must not be null.");

        this.variableName = variableName;
        this.variableType = variableType;
    }

    protected abstract T createVariable(String variableName, TypeDescriptor variableType);

    /**
     * Prototype method
     */
    public T with(String variableName) {
        return this.createVariable(variableName, this.getVariableType());
    }

    /**
     * Prototype method
     */
    public T with(TypeDescriptor variableType) {
        return createVariable(this.getVariableName(), variableType);
    }

    public String getVariableName() {
        return variableName;
    }

    public TypeDescriptor getVariableType() {
        return variableType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VariableDescriptor that = (VariableDescriptor) o;

        if (!variableName.equals(that.variableName)) {
            return false;
        }
        return variableType.equals(that.variableType);

    }

    @Override
    public int hashCode() {
        int result = variableName.hashCode();
        result = 31 * result + variableType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VariableDescriptor{" +
                "variableName='" + variableName + '\'' +
                ", variableType=" + variableType +
                '}';
    }
}
