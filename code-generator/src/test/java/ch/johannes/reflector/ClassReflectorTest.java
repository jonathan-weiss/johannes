package ch.johannes.reflector;

import ch.johannes.descriptor.ClassDescriptor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClassReflectorTest {

    @Test
    public void reflectString() throws Exception {
        //Arrange
        Class<?> clazzString = String.class;

        //Act
        final ClassDescriptor classDescriptorForString = ClassReflector.reflect(clazzString);

        //Assert
        assertThat(classDescriptorForString.getTypeDescriptor().getClassName().getClassName(), is("String"));
        assertThat(classDescriptorForString.getTypeDescriptor().getClassPackage().getPackageName(), is("java.lang"));
    }

    @Test
    public void reflectMiscClass() throws Exception {
        //Arrange
        Class<?> clazzString = MyTestClassToReflect.class;

        //Act
        final ClassDescriptor classDescriptorForString = ClassReflector.reflect(clazzString);

        //Assert
        assertThat(classDescriptorForString.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflect"));
        assertThat(classDescriptorForString.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));
    }



    @Test
    public void reflectAll() throws Exception {
        //Arrange
        List<Class<?>> classList = new ArrayList<>();
        classList.add(String.class);
        classList.add(Integer.class);
        classList.add(List.class);

        //Act
        final List<ClassDescriptor> resultList = ClassReflector.reflectAll(classList);

        //Assert
        assertThat(resultList.size(), is(classList.size()));

    }

}

class MyTestClassToReflect {
    private final int number = 0;
    private String text = "";
    private List<String> tokens;
    private MyTestClassToReflect recursive;
}