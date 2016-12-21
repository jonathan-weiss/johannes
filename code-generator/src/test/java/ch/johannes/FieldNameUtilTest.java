package ch.johannes;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class FieldNameUtilTest {

    @Test
    public void fieldNameToConstant() throws Exception {
        assertThat(FieldNameUtil.fieldNameToConstant("firstname"), is("FIRSTNAME"));
        assertThat(FieldNameUtil.fieldNameToConstant("firstname22"), is("FIRSTNAME22"));
        assertThat(FieldNameUtil.fieldNameToConstant("firstName"), is("FIRST_NAME"));
        assertThat(FieldNameUtil.fieldNameToConstant("_firstName"), is("_FIRST_NAME"));
        assertThat(FieldNameUtil.fieldNameToConstant("f"), is("F"));

    }

    @Test
    public void fieldNameToConstantWithWrongFieldname() throws Exception {
        assertThat(FieldNameUtil.fieldNameToConstant("first name"), is("FIRST NAME"));
        assertThat(FieldNameUtil.fieldNameToConstant("FirstName"), is("FIRST_NAME"));
        assertThat(FieldNameUtil.fieldNameToConstant("FIRSTNAME"), is("FIRSTNAME"));
        assertThat(FieldNameUtil.fieldNameToConstant("F"), is("F"));
    }


    @Test
    public void fieldNameToConstantNull() throws Exception {
        assertThat(FieldNameUtil.fieldNameToConstant(null), is(nullValue()));
    }

    @Test
    public void fieldNameToConstantEmpty() throws Exception {
        assertThat(FieldNameUtil.fieldNameToConstant(""), is(""));
    }

}