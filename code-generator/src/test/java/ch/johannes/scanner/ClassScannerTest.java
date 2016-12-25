package ch.johannes.scanner;

import ch.johannes.FileUtil;
import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.Descriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClassScannerTest {


    private static final String ROOT_PACKAGE = "ch.johannes";

    @Test
    public void scanForClassFileUtil() throws Exception {
        //Arrange
        List<String> wildcardPatternList = new ArrayList<>();
        wildcardPatternList.add("FileUtil");
        ClassScanner classScanner = new ClassScanner(ROOT_PACKAGE, wildcardPatternList);
        //Act
        final Collection<Class<?>> classes = classScanner.scanForClasses();

        //Assert
        assertThat(classes.isEmpty(), is(Boolean.FALSE));
        assertThat(classes, contains(FileUtil.class));
    }

    @Test
    public void scanForDescriptorClasses() throws Exception {
        //Arrange
        List<String> wildcardPatternList = new ArrayList<>();
        wildcardPatternList.add("*Descriptor");
        ClassScanner classScanner = new ClassScanner(ROOT_PACKAGE, wildcardPatternList);
        //Act
        final Collection<Class<?>> classes = classScanner.scanForClasses();

        //Assert
        assertThat(classes, containsInAnyOrder(
                Descriptor.class,
                ClassDescriptor.class,
                ClassnameDescriptor.class,
                FieldDescriptor.class,
                PackageDescriptor.class,
                TypeDescriptor.class));
    }
}