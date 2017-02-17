package ch.johannes.example.data.jooq;

import org.jooq.DSLContext;

@FunctionalInterface
public interface JOOQCallback<T> {

    T execute(DSLContext dslContext);
}