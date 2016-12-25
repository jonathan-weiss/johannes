package ch.johannes.examples.mapper.complex;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Has the following cases and conrnercases:
 * - Simple field (firstname, lastname)
 * - Primitive field (age)
 * - Enumeration (gender)
 * - List of String (nicknames)
 * - Map of Entities (addresses)
 * - Self-Reference (spouse)
 * - nested Model (Address)
 */
public class Person {

    private Gender gender;

    private String firstname;

    private String lastname;

    private List<String> nicknames;

    private LocalDate birthday;

    private Map<LocalDate, Address> addresses;

    private Person spouse;

    private Address currentAddress;

}
