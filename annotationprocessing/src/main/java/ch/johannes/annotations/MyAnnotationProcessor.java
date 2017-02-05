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
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
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

        messager.printMessage(Diagnostic.Kind.OTHER, "MyAnnotationProcessor: We have started now...");

        // Itearate over all @Factory annotated elements
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(MyDiscoveredAnnotation.class)) {
  		    messager.printMessage(Diagnostic.Kind.NOTE, annotatedElement.toString());
            // Check if a class has been annotated with @Factory
            if (annotatedElement.getKind() != ElementKind.CLASS) {

            }
        }

        messager.printMessage(Diagnostic.Kind.OTHER, "MyAnnotationProcessor:...and goodbye.");
        return false;
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
