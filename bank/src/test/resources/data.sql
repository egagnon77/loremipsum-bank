INSERT INTO product (id, category, name) VALUES (1, 1, 'A');
INSERT INTO product (id, category, name) VALUES (2, 1, 'B');
INSERT INTO product (id, category, name) VALUES (3, 2, 'Z');
INSERT INTO product (id, category, name) VALUES (4, 2, 'Y');

INSERT INTO client VALUES ('PoorMen');
INSERT INTO client VALUES ('MiddleMen');
INSERT INTO client VALUES ('RichMen');

INSERT INTO client_products (id, client_id, product_id) VALUES (1, 'MiddleMen', 1);
INSERT INTO client_products (id, client_id, product_id) VALUES (2, 'RichMen', 2);
INSERT INTO client_products (id, client_id, product_id) VALUES (3, 'RichMen', 3);