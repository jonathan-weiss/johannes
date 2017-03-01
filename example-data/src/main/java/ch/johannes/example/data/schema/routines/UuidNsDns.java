/**
 * This class is generated by jOOQ
 */
package ch.johannes.example.data.schema.routines;


import ch.johannes.example.data.schema.Public;

import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;


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
public class UuidNsDns extends AbstractRoutine<UUID> {

	private static final long serialVersionUID = -1670323871;

	/**
	 * The parameter <code>public.uuid_ns_dns.RETURN_VALUE</code>.
	 */
	public static final Parameter<UUID> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.UUID, false);

	/**
	 * Create a new routine call instance
	 */
	public UuidNsDns() {
		super("uuid_ns_dns", Public.PUBLIC, org.jooq.impl.SQLDataType.UUID);

		setReturnParameter(RETURN_VALUE);
	}
}