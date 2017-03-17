package ch.johannes;

import ch.johannes.annotations.Metadata;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Prism representing an {@link Metadata} annotation.
 */
public class MetadataClassPrism {

    /**
     * store prism value of value
     */
    private List<TypeMirror> _value;

    /**
     * An instance of the Values inner class whose
     * methods return the AnnotationValues used to build this prism.
     * Primarily intended to support using Messager.
     */
    public final Values values;

    /**
     * Return a prism representing the {@link Metadata} annotation on 'e'.
     * similar to {@code e.getAnnotation(Metadata.class)} except that
     * an instance of this class rather than an instance of {@link Metadata}
     * is returned.
     */
    public static MetadataClassPrism getInstanceOn(Element e) {
        AnnotationMirror m = getMirror(Metadata.class.getName(), e);
        if (m == null) {
            return null;
        }
        return getInstance(m);
    }

    /**
     * Return a prism of the {@code @ch.johannes.annotations.Metadata} annotation whose mirror is mirror.
     */
    public static MetadataClassPrism getInstance(AnnotationMirror mirror) {
        return new MetadataClassPrism(mirror);
    }

    private MetadataClassPrism(AnnotationMirror mirror) {
        for (ExecutableElement key : mirror.getElementValues().keySet()) {
            memberValues.put(key.getSimpleName().toString(), mirror.getElementValues().get(key));
        }
        for (ExecutableElement member : ElementFilter.methodsIn(mirror.getAnnotationType().asElement().getEnclosedElements())) {
            defaults.put(member.getSimpleName().toString(), member.getDefaultValue());
        }
        _value = getArrayValues("value", TypeMirror.class);

        this.values = new Values(memberValues);
        this.mirror = mirror;
        this.isValid = valid;

    }

    /**
     * Returns a TypeMirror representing the value of the {@code java.lang.Class<?> value()} member of the Annotation.
     *
     * @see MetadataClassPrism#value()
     */
    public List<TypeMirror> value() {
        return _value;
    }

    /**
     * Determine whether the underlying AnnotationMirror has no errors.
     * True if the underlying AnnotationMirror has no errors.
     * When true is returned, none of the methods will return null.
     * When false is returned, a least one member will either return null, or another
     * prism that is not valid.
     */
    public final boolean isValid;

    /**
     * The underlying AnnotationMirror of the annotation
     * represented by this Prism.
     * Primarily intended to support using Messager.
     */
    public final AnnotationMirror mirror;

    /**
     * A class whose members corespond to those of org.mapstruct.DecoratedWith
     * but which each return the AnnotationValue corresponding to
     * that member in the model of the annotations. Returns null for
     * defaulted members. Used for Messager, so default values are not useful.
     */
    /**
     * A class whose members corespond to those of org.mapstruct.MapperConfig
     * but which each return the AnnotationValue corresponding to
     * that member in the model of the annotations. Returns null for
     * defaulted members. Used for Messager, so default values are not useful.
     */
    public static class Values {

        private Map<String, AnnotationValue> values;

        private Values(Map<String, AnnotationValue> values) {
            this.values = values;
        }

        /**
         * Return the AnnotationValue corresponding to the uses()
         * member of the annotation, or null when the default value is implied.
         */
        public AnnotationValue value() {
            return values.get("value");
        }

    }

    private Map<String, AnnotationValue> defaults = new HashMap<String, AnnotationValue>(10);

    private Map<String, AnnotationValue> memberValues = new HashMap<String, AnnotationValue>(10);

    private boolean valid = true;

    private static AnnotationMirror getMirror(String fqn, Element target) {
        for (AnnotationMirror m : target.getAnnotationMirrors()) {
            CharSequence mfqn = ((TypeElement) m.getAnnotationType().asElement()).getQualifiedName();
            if (fqn.contentEquals(mfqn)) {
                return m;
            }
        }
        return null;
    }

    private <T> T getValue(String name, Class<T> clazz) {
        T result = MetadataClassPrism.getValue(memberValues, defaults, name, clazz);
        if (result == null) {
            valid = false;
        }
        return result;
    }

    private static <T> T getValue(Map<String, AnnotationValue> memberValues, Map<String, AnnotationValue> defaults, String name, Class<T> clazz) {
        AnnotationValue av = memberValues.get(name);
        if (av == null) {
            av = defaults.get(name);
        }
        if (av == null) {
            return null;
        }
        if (clazz.isInstance(av.getValue())) {
            return clazz.cast(av.getValue());
        }
        return null;
    }

    private <T> List<T> getArrayValues(String name, final Class<T> clazz) {
        List<T> result = MetadataClassPrism.getArrayValues(memberValues, defaults, name, clazz);
        if (result == null) {
            valid = false;
        }
        return result;
    }

    private static <T> List<T> getArrayValues(Map<String, AnnotationValue> memberValues, Map<String, AnnotationValue> defaults, String name, final Class<T> clazz) {
        AnnotationValue av = memberValues.get(name);
        if (av == null) {
            av = defaults.get(name);
        }
        if (av == null) {
            return null;
        }
        if (av.getValue() instanceof List) {
            List<T> result = new ArrayList<T>();
            for (AnnotationValue v : getValueAsList(av)) {
                if (clazz.isInstance(v.getValue())) {
                    result.add(clazz.cast(v.getValue()));
                } else {
                    return null;
                }
            }
            return result;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static List<AnnotationValue> getValueAsList(AnnotationValue av) {
        return (List<AnnotationValue>) av.getValue();
    }
}
