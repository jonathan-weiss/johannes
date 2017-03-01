package ch.johannes.example.data;

import ch.johannes.annotations.Metadata;
import ch.johannes.annotations.Plan;
import ch.johannes.example.data.dao.person.Gender;
import ch.johannes.example.data.dao.person.Person;

@Plan
@Metadata({Gender.class, Person.class})
public interface MetadataCreator {

}
