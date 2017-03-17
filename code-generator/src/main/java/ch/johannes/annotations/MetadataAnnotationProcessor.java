package ch.johannes.annotations;

import ch.johannes.MetadataClassPrism;
import ch.johannes.cg.MetadataSourceGenerator;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
        "ch.johannes.annotations.Metadata"
})
public class MetadataAnnotationProcessor extends AbstractProcessor {

    private Elements elementUtils;

    private Types typeUtils;

    private Filer filer;

    private Messager messager;

    private MetadataSourceGenerator metadataSourceGenerator = new MetadataSourceGenerator();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotatedElement : annotations) {
            final Set<? extends Element> elementsAnnotatedWithMetadata = roundEnv.getElementsAnnotatedWith(annotatedElement);
            for (Element element : elementsAnnotatedWithMetadata) {
                final Set<? extends AnnotationMirror> metadataAnnotationMirrors = element.getAnnotationMirrors()
                        .stream()
                        .filter(annotationMirror -> annotationMirror.getAnnotationType().toString().equals(Metadata.class.getName()))
                        .collect(Collectors.toSet());

                for (AnnotationMirror metadataAnnotation : metadataAnnotationMirrors) {
                    final MetadataClassPrism annotationMirror = MetadataClassPrism.getInstance(metadataAnnotation);
                    if (annotationMirror.value().isEmpty()) {
                        if (element.asType().getKind() == TypeKind.DECLARED) {
                            generateMetadataSource((DeclaredType) element.asType());
                        }
                    } else {
                        annotationMirror.value()
                                .stream()
                                .filter(typeMirror -> typeMirror.getKind() == TypeKind.DECLARED)
                                .map(typeMirror -> (DeclaredType) typeMirror)
                                .forEach(this::generateMetadataSource);
                    }
                }

            }

        }
        return true;
    }

    private void generateMetadataSource(DeclaredType declaredType) {
        //TODO create metadata for this type


        ClassDescriptor sourceClassDescriptor = createGenerationModel(declaredType);
        PackageDescriptor targetPackage = sourceClassDescriptor.getTypeDescriptor().getClassPackage();
        final String javaSourceCode = metadataSourceGenerator.generateCode(sourceClassDescriptor, targetPackage);

        String fullQualifiedName = String.format("%s.%sMetadata",targetPackage.getPackageName(), sourceClassDescriptor.getTypeDescriptor().getClassName());
        messager.printMessage(Diagnostic.Kind.NOTE, "MetadataAnnotationProcessor: generate: " + fullQualifiedName);
        try {
        Writer writer = null;
            try {
                JavaFileObject jfo = filer.createSourceFile(fullQualifiedName);
                writer = jfo.openWriter();
                writer.write(javaSourceCode);
            } finally {
                if(writer != null) {
                    writer.close();
                }
            }
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, String.format("Couldn't write file %s (%s)", sourceClassDescriptor, targetPackage));
        }

    }

    private ClassDescriptor createGenerationModel(DeclaredType declaredType) {

        final Element element = declaredType.asElement();
        final PackageElement packageOf = elementUtils.getPackageOf(element);

        final List<VariableElement> variableElements = ElementFilter.fieldsIn(element.getEnclosedElements());
        final List<FieldDescriptor> fieldDescriptors = variableElements.stream().map(this::convertVariableElement).collect(Collectors.toList());
        ClassDescriptor descriptor = ClassDescriptor.of(packageOf.toString(), element.getSimpleName().toString());
        descriptor = descriptor.addFields(fieldDescriptors);

        return descriptor;
    }

    private FieldDescriptor convertVariableElement(VariableElement variableElement) {
        String fieldName = variableElement.getSimpleName().toString();
        TypeDescriptor fieldType = convertType(variableElement.asType());
        return FieldDescriptor.of(fieldName, fieldType);
    }

    private TypeDescriptor convertType(TypeMirror typeMirror) {

        if(typeMirror.getKind().isPrimitive()) {
            return TypeDescriptor.of("", typeMirror.toString()).withPrimitive(true);
        }

        final Element element = typeUtils.asElement(typeMirror);
        if(element == null) {
            messager.printMessage(Diagnostic.Kind.ERROR, String.format("Couldn't fetch element for type %s.", typeMirror));
        }
        final PackageElement packageElement = elementUtils.getPackageOf(element);
        PackageDescriptor packageOf = PackageDescriptor.of(packageElement.getQualifiedName().toString());
        ClassnameDescriptor typeName = ClassnameDescriptor.of(element.getSimpleName().toString());
        return TypeDescriptor.of(packageOf, typeName);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return super.getSupportedAnnotationTypes();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }

}
