package ch.johannes.annotations;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;

public class CountClassesMethodsFieldsScanner extends ElementScanner8<Void, Void> {

    private int numberOfClasses;

    private int numberOfMethods;

    private int numberOfFields;

    public Void visitType(final TypeElement type, final Void p) {
        ++numberOfClasses;

        return super.visitType(type, p);
    }

    public Void visitExecutable(final ExecutableElement executable, final Void p) {
        ++numberOfMethods;
        return super.visitExecutable(executable, p);
    }

    public Void visitVariable(final VariableElement variable, final Void p) {
        if (variable.getEnclosingElement().getKind() == ElementKind.CLASS) {
            ++numberOfFields;
        }

        return super.visitVariable(variable, p);
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public int getNumberOfMethods() {
        return numberOfMethods;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }
}
