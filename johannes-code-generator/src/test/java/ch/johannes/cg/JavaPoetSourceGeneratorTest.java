package ch.johannes.cg;

import org.junit.Test;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

public class JavaPoetSourceGeneratorTest {

    private static final String EXPECTED_JAVA_SOURCE = "package com.example.helloworld;";

    @Test
    public void generateCode() throws Exception {
        JavaPoetSourceGenerator cg = new JavaPoetSourceGenerator();
        assertThat(cg.generateCode(), startsWith(EXPECTED_JAVA_SOURCE));
    }
}