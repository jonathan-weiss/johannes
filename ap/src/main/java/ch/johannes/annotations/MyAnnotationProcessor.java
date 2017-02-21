package ch.johannes.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
        "ch.johannes.annotations.MyDiscoveredAnnotation"
})
public class MyAnnotationProcessor extends AbstractProcessor {

    private Elements elementUtils;

    private Types typeUtils;

    private Filer filer;

    private Messager messager;

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
        messager.printMessage(Diagnostic.Kind.WARNING, "MyAnnotationProcessor: We have started now...");

        for (TypeElement annotatedElement : annotations) {
            final Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(annotatedElement);
            if(elementsAnnotatedWith != null) {
                createANewType();
                //discoverGivenAnnotations(annotations);
            }
        }
        messager.printMessage(Diagnostic.Kind.WARNING, "MyAnnotationProcessor:...and goodbye.");
        return true;
    }

    private void createANewType() {
        try {
            final TypeElement classElement = elementUtils.getTypeElement("ch.johannes.annotations.examples.AnotherClass");
            PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();
            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(classElement.getQualifiedName() + "BeanInfo");

            BufferedWriter bw = new BufferedWriter(jfo.openWriter());
            bw.append("package ");
            bw.append(packageElement.getQualifiedName());
            bw.append(";");
            bw.newLine();
            bw.newLine();

            bw.close();
            // rest of generated class contents


        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "MyAnnotationProcessor:Couldn't write file");
        }

    }

    private void discoverGivenAnnotations(Set<? extends TypeElement> annotations) {
        TypeMirror typeMirror = elementUtils.getTypeElement("ch.johannes.annotations.examples.AnotherClass").asType();
        discoverElement(typeUtils.asElement(typeMirror), "typeMirror");
        // Itearate over all @Factory annotated elements
        for (Element annotatedElement : annotations) {
            messager.printMessage(Diagnostic.Kind.NOTE, annotatedElement.toString());
            discoverElement(annotatedElement, " ");
            // Check if a class has been annotated with @Factory
            if (annotatedElement.getKind() != ElementKind.CLASS) {

            }
        }

    }



    private void discoverElement(Element element, String intend) {
        if(element == null) {
            return;
        }

        messager.printMessage(Diagnostic.Kind.NOTE, String.format("%s-------------------------", intend));
        messager.printMessage(Diagnostic.Kind.NOTE, String.format("%sElement: %s %s %s ", intend, element.getSimpleName(), element.getKind(), element.getModifiers()));
        messager.printMessage(Diagnostic.Kind.NOTE, String.format("%sType: %s %s %s ", intend, element.asType(), element.asType().getKind(), element.asType().toString()));
        messager.printMessage(Diagnostic.Kind.NOTE, String.format("%sAnnotations: %s ", intend, element.getAnnotationMirrors()));
        //discoverElement(element.getEnclosingElement(), intend + " ");
        for(Element enclosedElement: element.getEnclosedElements()) {
            discoverElement(enclosedElement, intend + " ");
        }
        messager.printMessage(Diagnostic.Kind.NOTE, String.format("%s-------------------------", intend));
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
