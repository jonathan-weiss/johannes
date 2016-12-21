package ch.johannes.reflector;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClassReflectorClassTest {

    @Test
    public void reflectClassWithString() throws Exception {
        //Arrange
        Class<?> clazzString = String.class;

        //Act
        final ClassDescriptor classDescriptorForString = ClassReflector.reflectClass(clazzString);

        //Assert
        assertThat(classDescriptorForString.getTypeDescriptor().getClassName().getClassName(), is("String"));
        assertThat(classDescriptorForString.getTypeDescriptor().getClassPackage().getPackageName(), is("java.lang"));
        assertThat(classDescriptorForString.getFields().size(), is(5));
    }

    @Test
    public void reflectAllClasses() throws Exception {
        //Arrange
        List<Class<?>> classList = new ArrayList<>();
        classList.add(String.class);
        classList.add(Integer.class);
        classList.add(List.class);
        classList.add(Boolean.class);
        classList.add(Collection.class);

        //Act
        final List<ClassDescriptor> resultList = ClassReflector.reflectAllClasses(classList);

        //Assert
        assertThat(resultList.size(), is(classList.size()));
    }

    @Test
    public void reflectTypeWithListOfStrings() throws Exception {
        //Arrange
        List<String> listOfString = new ArrayList<>();

        //Act
        final TypeDescriptor typeDescriptor = ClassReflector.reflectClassAsTypeDescriptor(listOfString.getClass());

        //Assert
        assertThat(typeDescriptor.getClassName().getClassName(), is("ArrayList"));
        assertThat(typeDescriptor.getClassPackage().getPackageName(), is("java.util"));
    }


    @Test
    public void reflectTypeListOfStrings() throws Exception {
        //Arrange
        List<String> instanceToInspect = new ArrayList<>();

        //Act
        final TypeDescriptor typeDescriptor = ClassReflector.reflectClassAsTypeDescriptor(instanceToInspect.getClass());

        //Assert
        assertThat(typeDescriptor.getClassName().getClassName(), is("ArrayList"));
        assertThat(typeDescriptor.getClassPackage().getPackageName(), is("java.util"));
        assertThat(typeDescriptor.isArray(), is(Boolean.FALSE));
        assertThat(typeDescriptor.isPrimitive(), is(Boolean.FALSE));
        assertThat(typeDescriptor.getGenericParameters().isEmpty(), is(Boolean.TRUE)); //only not empty for fields!
    }

    @Test
    public void reflectTypeArrayOfStrings() throws Exception {
        //Arrange
        String [] instanceToInspect = new String [] {};

        //Act
        final TypeDescriptor typeDescriptor = ClassReflector.reflectClassAsTypeDescriptor(instanceToInspect.getClass());

        //Assert
        assertThat(typeDescriptor.getClassName().getClassName(), is("String"));
        assertThat(typeDescriptor.getClassPackage().getPackageName(), is("java.lang"));
        assertThat(typeDescriptor.isArray(), is(Boolean.TRUE));
        assertThat(typeDescriptor.isPrimitive(), is(Boolean.FALSE));
        assertThat(typeDescriptor.getGenericParameters().isEmpty(), is(Boolean.TRUE));
    }

}