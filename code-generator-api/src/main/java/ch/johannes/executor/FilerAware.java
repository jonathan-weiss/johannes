package ch.johannes.executor;

import javax.annotation.processing.Filer;

public interface FilerAware {

    void initFiler(Filer filer);

}
