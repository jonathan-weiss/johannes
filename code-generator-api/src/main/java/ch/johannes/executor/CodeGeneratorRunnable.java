package ch.johannes.executor;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;

public interface CodeGeneratorRunnable {

    void process(Filer filer, Messager messager);
}
