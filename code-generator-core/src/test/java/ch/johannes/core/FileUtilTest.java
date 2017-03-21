package ch.johannes.core;

import org.junit.Test;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void readFileInPackage() throws Exception {
        assertThat(FileUtil.readFileInPackage(this, FileUtilTest.class.getSimpleName() + ".readFileInPackage.txt"), startsWith("hello file util"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void readFileInUnknownPackage() throws Exception {
        FileUtil.readFileInPackage(this, "inexistent-file.txt");
    }

}