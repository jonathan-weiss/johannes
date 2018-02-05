package ch.johannes.metadata.util;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.Parameterizable;
import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ElementSummaryUtil {

    private ElementSummaryUtil() {
        //private constructor
    }

    public static void printElementSummary(Messager messager, Element element) {
        printElementSummary(0, messager, element);
    }

    private static String createIdent(int ident) {
        return IntStream.range(0, ident).mapToObj(i -> " ").collect(Collectors.joining(""));
    }

    private static void printElementSummary(int ident, Messager messager, Element element) {
        String identString = createIdent(ident);
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "..............");
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:" + element.toString());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:simple-name:" + element.getSimpleName());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:modifiers:" + element.getModifiers());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:kind: " + element.getKind());

        if(element instanceof Parameterizable) {
            messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:type-parameter:" + ((Parameterizable)element).getTypeParameters());
        }

        if(element instanceof QualifiedNameable) {
            messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:qualified-name:" + ((QualifiedNameable)element).getQualifiedName());
        }

        if(element instanceof ExecutableElement) {
            printExecutableElementAttributes(ident, messager, (ExecutableElement) element);
        } else if (element instanceof TypeElement) {
            printTypeElementAttributes(ident, messager, (TypeElement) element);
        } else if (element instanceof TypeParameterElement) {
            printTypeParameterElementAttributes(ident, messager, (TypeParameterElement) element);
        } else if (element instanceof VariableElement) {
            printTypeVariableElementAttributes(ident, messager, (VariableElement) element);
        } else if(element instanceof PackageElement) {
            printPackageElementAttributes(ident, messager, (PackageElement) element);
        }

        element.getEnclosedElements().forEach(enclosedElement -> printElementSummary(ident + 4, messager, enclosedElement));


        switch (element.getKind()) {
            case CLASS:
            case CONSTRUCTOR:
            case ENUM_CONSTANT:
            case ANNOTATION_TYPE:
            case INTERFACE:
            case ENUM:
            case EXCEPTION_PARAMETER:
            case INSTANCE_INIT:
            case LOCAL_VARIABLE:
            case FIELD:
            case METHOD:
            case PARAMETER:
            case RESOURCE_VARIABLE:
            case STATIC_INIT:
            case TYPE_PARAMETER:
            case OTHER:
            case PACKAGE:
                break;
            default:
                messager.printMessage(Diagnostic.Kind.NOTE, identString + "(element not specially supported)");

        }
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "..............");
    }

    private static void printPackageElementAttributes(int ident, Messager messager, PackageElement element) {

    }

    private static void printTypeVariableElementAttributes(int ident, Messager messager, VariableElement element) {
        String identString = createIdent(ident);
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:constant-value:" + element.getConstantValue());

    }

    private static void printTypeParameterElementAttributes(int ident, Messager messager, TypeParameterElement element) {
        String identString = createIdent(ident);
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:generic-element:" + element.getGenericElement());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:bounds:" + element.getBounds());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:enclosing-elements:" + element.getEnclosingElement());

    }

    private static void printTypeElementAttributes(int ident, Messager messager, TypeElement element) {
        String identString = createIdent(ident);
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:interfaces:" + element.getInterfaces());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:nesting-kind:" + element.getNestingKind());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:super-class:" + element.getSuperclass());
    }

    private static void printExecutableElementAttributes(int ident, Messager messager, ExecutableElement element) {
        String identString = createIdent(ident);
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:default-value:" + element.getDefaultValue());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:thrown-types:" + element.getThrownTypes());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:receiver-type:" + element.getReceiverType());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:return-type:" + element.getReturnType());
        messager.printMessage(Diagnostic.Kind.NOTE, identString + "element:parameters:");
        element.getParameters().forEach(variableElement -> printElementSummary(ident + 4, messager, variableElement));
    }

}
