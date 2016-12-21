package ch.johannes.cg;

import com.squareup.javapoet.CodeBlock;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JavaPoetTest {

    @Test
    public void testStringEscaping() {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.addStatement("$T.out.println($S)", System.class, "hello");
        CodeBlock codeBlock = codeBlockBuilder.build();

        //write code
        assertThat(codeBlock.toString(), is("java.lang.System.out.println(\"hello\");\n"));
    }

    @Test
    public void testNumberToStringEscaping() {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.addStatement("$T.out.println($S)", System.class, 5);
        CodeBlock codeBlock = codeBlockBuilder.build();

        //write code
        assertThat(codeBlock.toString(), is("java.lang.System.out.println(\"5\");\n"));
    }



    @Test
    public void testNestedExpression() {
        CodeBlock.Builder innerCodeBlockBuilder = CodeBlock.builder();
        innerCodeBlockBuilder.add("$S.trim()", "hello");

        CodeBlock innerCodeBlock = innerCodeBlockBuilder.build();

        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        codeBlockBuilder.addStatement("$T.out.println($L)", System.class, innerCodeBlock);
        CodeBlock codeBlock = codeBlockBuilder.build();

        //write code
        assertThat(codeBlock.toString(), is("java.lang.System.out.println(\"hello\".trim());\n"));
    }


    @Test
    public void testVeryNestedExpression() {
        CodeBlock.Builder veryInnerCodeBlockBuilder = CodeBlock.builder();
        veryInnerCodeBlockBuilder.add("$S.toUpperCase()", "HeLlO");
        CodeBlock veryInnerCodeBlock = veryInnerCodeBlockBuilder.build();

        CodeBlock.Builder innerCodeBlockBuilder = CodeBlock.builder();
        innerCodeBlockBuilder.add("$L.trim()", veryInnerCodeBlock);
        CodeBlock innerCodeBlock = innerCodeBlockBuilder.build();

        CodeBlock.Builder outerCodeBlockBuilder = CodeBlock.builder();
        outerCodeBlockBuilder.addStatement("$T.out.println($L)", System.class, innerCodeBlock.toString());
        CodeBlock codeBlock = outerCodeBlockBuilder.build();

        //write code
        assertThat(codeBlock.toString(), is("java.lang.System.out.println(\"HeLlO\".toUpperCase().trim());\n"));
    }
}
