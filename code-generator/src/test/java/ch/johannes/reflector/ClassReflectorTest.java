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

public class ClassReflectorTest {

    @Test
    public void reflectString() throws Exception {
        //Arrange
        Class<?> clazzString = String.class;

        //Act
        final ClassDescriptor classDescriptorForString = ClassReflector.reflectClass(clazzString);

        //Assert
        assertThat(classDescriptorForString.getTypeDescriptor().getClassName().getClassName(), is("String"));
        assertThat(classDescriptorForString.getTypeDescriptor().getClassPackage().getPackageName(), is("java.lang"));
        assertThat(classDescriptorForString.getFields().size(), is(1));
    }

    @Test
    public void reflectMiscClass() throws Exception {
        //Arrange
        Class<?> clazzString = MyTestClassToReflect.class;

        //Act
        final ClassDescriptor classDescriptorForString = ClassReflector.reflectClass(clazzString);

        //Assert
        assertThat(classDescriptorForString.getTypeDescriptor().getClassName().getClassName(), is("MyTestClassToReflect"));
        assertThat(classDescriptorForString.getTypeDescriptor().getClassPackage().getPackageName(), is(this.getClass().getPackage().getName()));

        FieldDescriptor primitiveIntDescription = FieldDescriptor.of("number", TypeDescriptor.of(PackageDescriptor.of(""), ClassnameDescriptor.of("int")));
        FieldDescriptor stringDescription = FieldDescriptor.of("text", TypeDescriptor.of(PackageDescriptor.of("java.lang"), ClassnameDescriptor.of("String")));
        FieldDescriptor listDescription = FieldDescriptor.of("tokens", TypeDescriptor.of(PackageDescriptor.of("java.util"), ClassnameDescriptor.of("List")));
        FieldDescriptor myTestClassToReflectDescription = FieldDescriptor.of("recursive", TypeDescriptor.of(PackageDescriptor.of(this.getClass().getPackage().getName()), ClassnameDescriptor.of("MyTestClassToReflect")));
        assertThat(classDescriptorForString.getFields(), containsInAnyOrder(primitiveIntDescription, stringDescription, listDescription, myTestClassToReflectDescription));
    }



    @Test
    public void reflectAll() throws Exception {
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

}

class MyTestClassToReflect {
    private final int number = 0;
    private String text = "";
    private List<String> tokens;
    private MyTestClassToReflect recursive;
}