DO $$
DECLARE
  rolename TEXT;
BEGIN
  -- A database server can contain multiple databases. But the the roles are defined on database server level.
  -- Only delete the roles that are used in this database
  IF EXISTS(SELECT relname
            FROM pg_class
            WHERE relname = 'group_membership')
  THEN
    FOR rolename IN SELECT member_guid
                    FROM group_membership
    LOOP
      RAISE NOTICE 'Deleting role % ...', quote_ident(rolename);
      EXECUTE 'DROP ROLE ' || quote_ident(rolename);
    END LOOP;
  END IF;
END $$;