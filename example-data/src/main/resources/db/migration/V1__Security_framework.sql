------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
------------------------------------ Access control base  --------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------

-- This is the base role (group) which all other roles (users and groups) are linked to. This role should be used as
-- the source for default access control.
DO $$
BEGIN
  IF NOT EXISTS(SELECT 1
                FROM pg_roles
                WHERE rolname = 'johannes_base_role')
  THEN
    CREATE ROLE johannes_base_role WITH NOLOGIN;
  END IF;
END
$$;

CREATE TABLE group_membership
(
  group_guid   UUID    NOT NULL,
  member_guid  UUID    NOT NULL,
  access_level INTEGER NOT NULL,
  PRIMARY KEY (group_guid, member_guid)

);

CREATE TABLE entity_access
(
  entity_guid   UUID    NOT NULL,
  accessor_guid UUID    NOT NULL,
  access_level  INTEGER NOT NULL,
  PRIMARY KEY (entity_guid, accessor_guid)
);

CREATE TABLE user_mapping
(
  access_guid        UUID UNIQUE    NOT NULL PRIMARY KEY,
  technical_username VARCHAR(64) UNIQUE
);
REVOKE ALL ON user_mapping FROM johannes_base_role; -- make sure that no restricted user ever sees/changes what is inside this table

-- This view flattens the hierarchical group membership inheritance. To make this work we have to make sure groups form
-- a DAG. Cyclic dependencies will crash the DB.

CREATE VIEW group_access_view AS
  WITH RECURSIVE search_group(member_guid, group_guid, access_level) AS (
    SELECT
      gm.member_guid,
      gm.group_guid,
      gm.access_level
    FROM group_membership gm
    UNION ALL
    SELECT
      gm2.member_guid,
      sg.group_guid,
      LEAST(gm2.access_level, sg.access_level)
    FROM search_group sg, group_membership gm2
    WHERE gm2.group_guid = sg.member_guid
  )
  SELECT DISTINCT ON (sg.member_guid, sg.group_guid) *
  FROM search_group sg
  ORDER BY sg.group_guid, sg.member_guid, sg.access_level DESC;

-- this view resolves the actual access levels. It combines the access levels specified in the entity_access table
-- with the 'static' access inheritance defined in group_access
CREATE VIEW entity_access_view AS
  SELECT
    DISTINCT ON (entity_guid, accessor_guid)
    entity_access.entity_guid,
    COALESCE(group_access_view.member_guid, entity_access.accessor_guid)   AS accessor_guid,
    MAX(LEAST(group_access_view.access_level, entity_access.access_level)) AS access_level
  FROM entity_access
    LEFT JOIN group_access_view ON entity_access.accessor_guid = group_access_view.group_guid
  GROUP BY group_access_view.member_guid, entity_access.accessor_guid, entity_access.entity_guid
  ORDER BY entity_guid, accessor_guid, access_level;
