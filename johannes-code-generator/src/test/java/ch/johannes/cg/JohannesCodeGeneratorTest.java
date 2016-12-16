package ch.johannes.cg;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JohannesCodeGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        JohannesCodeGenerator cg = new JohannesCodeGenerator();
        assertThat(cg.generateCode(), is("Hello World"));
    }

}