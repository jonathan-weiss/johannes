package ch.johannes.core;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BeanUtilTest {

    @Test
    public void prefixAndSuffixName() throws Exception {
        assertThat(BeanUtil.prefixAndSuffixName("lastname", "fetch", ""), is("fetchLastname"));
        assertThat(BeanUtil.prefixAndSuffixName("lastname", "fetch", "ById"), is("fetchLastnameById"));
    }

    @Test
    public void prefixName() throws Exception {
        assertThat(BeanUtil.prefixName("lastname", "fetch"), is("fetchLastname"));
    }

    @Test
    public void createGetterName() throws Exception {
        assertThat(BeanUtil.createGetterName("lastname"), is("getLastname"));
    }

    @Test
    public void createSetterName() throws Exception {
        assertThat(BeanUtil.createSetterName("lastname"), is("setLastname"));
    }

}