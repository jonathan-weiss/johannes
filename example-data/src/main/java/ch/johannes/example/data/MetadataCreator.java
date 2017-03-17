package ch.johannes.example.data;

import ch.johannes.annotations.Metadata;
import ch.johannes.annotations.Plan;
import ch.johannes.example.data.dao.person.Gender;
import ch.johannes.example.data.dao.person.Person;
import ch.johannes.example.data.schema.tables.Address;
import ch.johannes.example.data.schema.tables.UserMapping;
import ch.johannes.example.data.schema.tables.records.PersonRecord;

@Plan
@Metadata({Gender.class, Person.class, PersonRecord.class, Address.class, UserMapping.class})
public interface MetadataCreator {

}
