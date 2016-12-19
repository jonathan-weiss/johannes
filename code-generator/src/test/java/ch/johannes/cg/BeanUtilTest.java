package ch.johannes.cg;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BeanUtilTest {

    @Test
    public void createGetterName() throws Exception {
        assertThat(BeanUtil.createGetterName("lastname"), is("getLastname"));
    }

    @Test
    public void createSetterName() throws Exception {
        assertThat(BeanUtil.createSetterName("lastname"), is("setLastname"));
    }

}