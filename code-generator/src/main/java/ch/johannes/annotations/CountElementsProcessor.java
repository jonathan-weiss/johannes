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
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("*")
public class CountElementsProcessor extends AbstractProcessor {

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

    public boolean process(final Set<? extends TypeElement> types,
                           final RoundEnvironment environment) {

        if (!environment.processingOver()) {
            for (final Element element : environment.getRootElements()) {
                final CountClassesMethodsFieldsScanner scanner = new CountClassesMethodsFieldsScanner();
                scanner.scan(element);

                String message = String.format("%s: Classes %d, methods/constructors %d, fields %d",
                        element.getSimpleName(),
                        scanner.getNumberOfClasses(),
                        scanner.getNumberOfMethods(),
                        scanner.getNumberOfFields());

                messager.printMessage(Diagnostic.Kind.WARNING, "CountElementsProcessor:" + message);
            }
        }

        return true;
    }
}