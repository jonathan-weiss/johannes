/**
 * This class is generated by jOOQ
 */
package ch.johannes.example.data.schema.tables;


import ch.johannes.example.data.schema.Public;
import ch.johannes.example.data.schema.tables.records.UserMappingRecord;

import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
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
public class UserMapping extends TableImpl<UserMappingRecord> {

	private static final long serialVersionUID = -93782720;

	/**
	 * The reference instance of <code>public.user_mapping</code>
	 */
	public static final UserMapping USER_MAPPING = new UserMapping();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<UserMappingRecord> getRecordType() {
		return UserMappingRecord.class;
	}

	/**
	 * The column <code>public.user_mapping.access_guid</code>.
	 */
	public final TableField<UserMappingRecord, UUID> ACCESS_GUID = createField("access_guid", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>public.user_mapping.technical_username</code>.
	 */
	public final TableField<UserMappingRecord, String> TECHNICAL_USERNAME = createField("technical_username", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * Create a <code>public.user_mapping</code> table reference
	 */
	public UserMapping() {
		this("user_mapping", null);
	}

	/**
	 * Create an aliased <code>public.user_mapping</code> table reference
	 */
	public UserMapping(String alias) {
		this(alias, USER_MAPPING);
	}

	private UserMapping(String alias, Table<UserMappingRecord> aliased) {
		this(alias, aliased, null);
	}

	private UserMapping(String alias, Table<UserMappingRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserMapping as(String alias) {
		return new UserMapping(alias, this);
	}

	/**
	 * Rename this table
	 */
	public UserMapping rename(String name) {
		return new UserMapping(name, null);
	}
}
