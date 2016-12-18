package ch.johannes.cg;

import org.junit.Test;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void readFileInPackage() throws Exception {
        assertThat(FileUtil.readFileInPackage(this, "FileUtilTest.readFileInPackage.txt"), startsWith("hello file util"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void readFileInUnknownPackage() throws Exception {
        assertThat(FileUtil.readFileInPackage(this, "inexistent-file.txt"), startsWith("hello file util"));
    }

}