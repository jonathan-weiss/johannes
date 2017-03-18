package ch.johannes.executor;

import javax.annotation.processing.Messager;

public interface MessagerAware {

    void initMessager(Messager messager);

}
