package ch.johannes.example.data.jooq;

import org.jooq.DSLContext;

@FunctionalInterface
public interface JOOQVoidCallback {

    void execute(DSLContext dslContext);
}