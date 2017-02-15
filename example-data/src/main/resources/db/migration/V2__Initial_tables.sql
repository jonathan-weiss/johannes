CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE GENDER_TYPE_ENUM AS ENUM ('FEMALE', 'MALE', 'UNDEFINED');

------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
------------------------------------ Tables and Types  -----------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------


-- person ----------------

CREATE SEQUENCE person_sequence;
GRANT ALL ON person_sequence TO johannes_base_role;

CREATE TABLE person (
  id        INTEGER PRIMARY KEY       DEFAULT nextval('person_sequence'),
  guid      UUID UNIQUE      NOT NULL DEFAULT uuid_generate_v4(),
  firstname VARCHAR(255)     NOT NULL,
  lastname  VARCHAR(255)     NOT NULL,
  gender    GENDER_TYPE_ENUM NOT NULL DEFAULT 'UNDEFINED'
);
GRANT ALL ON person TO johannes_base_role;

-- address ----------------

CREATE SEQUENCE address_sequence;
GRANT ALL ON address_sequence TO johannes_base_role;


CREATE TABLE address (
  id        INTEGER PRIMARY KEY       DEFAULT nextval('address_sequence'),
  person_id INTEGER REFERENCES person (id) NOT NULL,
  street    VARCHAR(255)                   NOT NULL,
  zip_code  INTEGER,
  city      VARCHAR(255)                   NOT NULL
);
GRANT ALL ON address TO johannes_base_role;


CREATE VIEW person_and_address_view AS
  SELECT
    person.id        AS person_id,
    person.firstname AS firstname,
    person.lastname  AS lastname,
    address.id       AS address_id,
    address.street   AS street,
    address.zip_code AS zip_code,
    address.city     AS city
  FROM person
    LEFT JOIN address
      ON person.id = address.person_id;

GRANT SELECT ON person_and_address_view TO johannes_base_role;
