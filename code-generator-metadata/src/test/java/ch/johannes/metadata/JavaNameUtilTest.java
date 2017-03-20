package ch.johannes.metadata;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class JavaNameUtilTest {

    @Test
    public void fieldNameToConstant() throws Exception {
        assertThat(JavaNameUtil.fieldNameToConstant("firstname"), is("FIRSTNAME"));
        assertThat(JavaNameUtil.fieldNameToConstant("myFirstNameIsJohannes"), is("MY_FIRST_NAME_IS_JOHANNES"));
        assertThat(JavaNameUtil.fieldNameToConstant("firstname22"), is("FIRSTNAME22"));
        assertThat(JavaNameUtil.fieldNameToConstant("firstName"), is("FIRST_NAME"));
        assertThat(JavaNameUtil.fieldNameToConstant("_firstName"), is("_FIRST_NAME"));
        assertThat(JavaNameUtil.fieldNameToConstant("firstnameTO"), is("FIRSTNAME_TO"));
        assertThat(JavaNameUtil.fieldNameToConstant("FIRSTNAME_TO"), is("FIRSTNAME_TO"));
        assertThat(JavaNameUtil.fieldNameToConstant("f"), is("F"));
        assertThat(JavaNameUtil.fieldNameToConstant("first name"), is("FIRST NAME"));
        assertThat(JavaNameUtil.fieldNameToConstant("FirstName"), is("FIRST_NAME"));
        assertThat(JavaNameUtil.fieldNameToConstant("FIRSTNAME"), is("FIRSTNAME"));
        assertThat(JavaNameUtil.fieldNameToConstant("F"), is("F"));

    }

    @Test
    public void fieldNameToConstantNull() throws Exception {
        assertThat(JavaNameUtil.fieldNameToConstant(null), is(nullValue()));
    }

    @Test
    public void fieldNameToConstantEmpty() throws Exception {
        assertThat(JavaNameUtil.fieldNameToConstant(""), is(""));
    }

}