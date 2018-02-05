package ch.johannes.metadata.util;

import javax.annotation.processing.Messager;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.UnionType;
import javax.lang.model.type.WildcardType;
import javax.tools.Diagnostic;

public abstract class TypeSummaryUtil {

    private TypeSummaryUtil() {
        //private constructor
    }

    public static void printTypeSummary(Messager messager, TypeMirror typeMirror) {
        messager.printMessage(Diagnostic.Kind.NOTE, "..............");
        messager.printMessage(Diagnostic.Kind.NOTE, "type:" + typeMirror.toString());
        messager.printMessage(Diagnostic.Kind.NOTE, "type:kind: " + typeMirror.getKind());

        switch (typeMirror.getKind()) {
            case BOOLEAN:
            case CHAR:
            case DOUBLE:
            case FLOAT:
            case INT:
            case BYTE:
            case SHORT:
            case LONG:
                printPrimitiveType(messager, (PrimitiveType) typeMirror);
                break;
            case ARRAY:
                printArrayType(messager, (ArrayType) typeMirror);
                break;
            case DECLARED:
                printDeclaredType(messager, (DeclaredType) typeMirror);
                break;
            case INTERSECTION:
            case UNION:
                printUnionType(messager, (UnionType) typeMirror);
                break;
            case TYPEVAR:
                printTypeVariable(messager, (TypeVariable) typeMirror);
                break;
            case WILDCARD:
                printWildcardType(messager, (WildcardType) typeMirror);
                break;
            case ERROR:
            case EXECUTABLE:
            case NONE:
            case NULL:
            case OTHER:
            case PACKAGE:
            case VOID:
            default:
                messager.printMessage(Diagnostic.Kind.NOTE, "(type not specially supported)");

        }
        messager.printMessage(Diagnostic.Kind.NOTE, "..............");
    }

    private static void printWildcardType(Messager messager, WildcardType typeMirror) {
        messager.printMessage(Diagnostic.Kind.NOTE, "type:extends-bound: " + typeMirror.getExtendsBound());
        messager.printMessage(Diagnostic.Kind.NOTE, "type:super-bound: " + typeMirror.getSuperBound());
    }

    private static void printTypeVariable(Messager messager, TypeVariable typeVariable) {

        messager.printMessage(Diagnostic.Kind.NOTE, "type:lower-bound: " + typeVariable.getLowerBound());
        messager.printMessage(Diagnostic.Kind.NOTE, "type:upper-bound: " + typeVariable.getUpperBound());
    }

    private static void printUnionType(Messager messager, UnionType typeMirror) {
        messager.printMessage(Diagnostic.Kind.NOTE, "type:alternatives: " + typeMirror.getAlternatives());
        for (TypeMirror alternative : typeMirror.getAlternatives()) {
            messager.printMessage(Diagnostic.Kind.NOTE, " - type:alternative: " + alternative.toString());
        }

    }

    private static void printPrimitiveType(Messager messager, PrimitiveType primitiveType) {
        messager.printMessage(Diagnostic.Kind.NOTE, "type:primitive: " + primitiveType.toString());
    }

    private static void printArrayType(Messager messager, ArrayType arrayType) {
        messager.printMessage(Diagnostic.Kind.NOTE, "type:array-component: " + arrayType.getComponentType());
    }

    private static void printDeclaredType(Messager messager, DeclaredType declaredType) {
        messager.printMessage(Diagnostic.Kind.NOTE, "type:type-arguments: ");
        for (TypeMirror typeArgument : declaredType.getTypeArguments()) {
            messager.printMessage(Diagnostic.Kind.NOTE, " - type:type-argument: " + typeArgument.toString());
        }
    }

}
