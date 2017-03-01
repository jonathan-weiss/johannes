/**
 * This class is generated by jOOQ
 */
package ch.johannes.example.data.schema.tables;


import ch.johannes.example.data.schema.Keys;
import ch.johannes.example.data.schema.Public;
import ch.johannes.example.data.schema.enums.GenderTypeEnum;
import ch.johannes.example.data.schema.tables.records.PersonRecord;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Person extends TableImpl<PersonRecord> {

	private static final long serialVersionUID = -987043670;

	/**
	 * The reference instance of <code>public.person</code>
	 */
	public static final Person PERSON = new Person();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<PersonRecord> getRecordType() {
		return PersonRecord.class;
	}

	/**
	 * The column <code>public.person.id</code>.
	 */
	public final TableField<PersonRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.person.guid</code>.
	 */
	public final TableField<PersonRecord, UUID> GUID = createField("guid", org.jooq.impl.SQLDataType.UUID.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.person.firstname</code>.
	 */
	public final TableField<PersonRecord, String> FIRSTNAME = createField("firstname", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>public.person.lastname</code>.
	 */
	public final TableField<PersonRecord, String> LASTNAME = createField("lastname", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>public.person.gender</code>.
	 */
	public final TableField<PersonRecord, GenderTypeEnum> GENDER = createField("gender", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(ch.johannes.example.data.schema.enums.GenderTypeEnum.class), this, "");

	/**
	 * Create a <code>public.person</code> table reference
	 */
	public Person() {
		this("person", null);
	}

	/**
	 * Create an aliased <code>public.person</code> table reference
	 */
	public Person(String alias) {
		this(alias, PERSON);
	}

	private Person(String alias, Table<PersonRecord> aliased) {
		this(alias, aliased, null);
	}

	private Person(String alias, Table<PersonRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<PersonRecord, Integer> getIdentity() {
		return Keys.IDENTITY_PERSON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<PersonRecord> getPrimaryKey() {
		return Keys.PERSON_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<PersonRecord>> getKeys() {
		return Arrays.<UniqueKey<PersonRecord>>asList(Keys.PERSON_PKEY, Keys.PERSON_GUID_KEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Person as(String alias) {
		return new Person(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Person rename(String name) {
		return new Person(name, null);
	}
}