package ch.johannes.examples.mapper;

import ch.johannes.examples.mapper.oneone.MyGeneratedPersonMapper;
import ch.johannes.examples.mapper.oneone.MyGeneratedPersonMapperTest;
import ch.johannes.examples.mapper.oneone.MyPersonTO;
import ch.johannes.examples.mapper.oneone.Person;
import ch.johannes.examples.mapper.oneone.PersonTO;
import org.junit.Test;

public class PersonMapperDependencyTest {
  @Test
  public void testDependenciesCanBeResolved() {
      Person person = new Person("Johannes", "Generator");
      PersonTO personTO = new PersonTO();
      MyPersonTO myPersonTO = new MyPersonTO();
      MyGeneratedPersonMapper myGeneratedPersonMapper = new MyGeneratedPersonMapper();
      final MyPersonTO myPersonTOResult = myGeneratedPersonMapper.apply(person);
      MyGeneratedPersonMapperTest myGeneratedPersonMapperTest = new MyGeneratedPersonMapperTest();
  }
}
