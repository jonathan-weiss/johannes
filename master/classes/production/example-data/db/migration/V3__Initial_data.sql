INSERT INTO person (firstname, lastname, gender) VALUES ('Fritz', 'Müller', 'MALE');
INSERT INTO person (firstname, lastname, gender) VALUES ('Vera', 'Hinterseen', 'FEMALE');

INSERT INTO address (street, zip_code, city, person_id) VALUES ('Ackerstrasse 4', '8000', 'Zürich', (SELECT id FROM person WHERE firstname = 'Fritz'));
INSERT INTO address (street, zip_code, city, person_id) VALUES ('Stachelstrasse 3', '8000', 'Zürich', (SELECT id FROM person WHERE firstname = 'Vera'));


