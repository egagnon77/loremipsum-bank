INSERT INTO product (id, product_type, product_level, name) VALUES (5, 0, 0, 'Produit_A');
INSERT INTO product (id, product_type, product_level, name) VALUES (6, 1, 0, 'Produit_B');
INSERT INTO product (id, product_type, product_level, name) VALUES (7, 0, 1, 'Produit_Z');
INSERT INTO product (id, product_type, product_level, name) VALUES (8, 1, 1, 'Produit_Y');

INSERT INTO client VALUES ('PoorMen', 0);
INSERT INTO client VALUES ('MiddleMen', 0);
INSERT INTO client VALUES ('RichMen', 0);
INSERT INTO client VALUES ('NormalMen', 0);
INSERT INTO client VALUES ('VIPMen', 1);

INSERT INTO client_products (id, client_id, product_id, approbation_status) VALUES (1, 'MiddleMen', 5, 0);
INSERT INTO client_products (id, client_id, product_id, approbation_status) VALUES (2, 'RichMen', 6 , 0);
INSERT INTO client_products (id, client_id, product_id, approbation_status) VALUES (3, 'RichMen', 7, 0);

