INSERT INTO product (product_id, product_type, product_level, name) VALUES (5, 0, 0, 'Produit_A');
INSERT INTO product (product_id, product_type, product_level, name) VALUES (6, 1, 0, 'Produit_B');
INSERT INTO product (product_id, product_type, product_level, name) VALUES (7, 0, 1, 'Produit_Z');
INSERT INTO product (product_id, product_type, product_level, name) VALUES (8, 1, 1, 'Produit_Y');

INSERT INTO client VALUES ('PoorMen', 0);
INSERT INTO client VALUES ('MiddleMen', 0);
INSERT INTO client VALUES ('RichMen', 0);
INSERT INTO client VALUES ('NormalMenToUpgrade', 0);
INSERT INTO client VALUES ('VIPMenToUpgrade', 1);
INSERT INTO client VALUES ('NormalMenToDowngrade', 0);
INSERT INTO client VALUES ('VIPMenToDowngrade', 1);
INSERT INTO client VALUES ('NormalMenToList', 0);
INSERT INTO client VALUES ('VIPMenToList', 1);
INSERT INTO client VALUES ('MrWantAll', 1);
INSERT INTO client VALUES ('MrsNormalWantAProduct', 0);
INSERT INTO client VALUES ('MrsVipWantAProduct', 1);
INSERT INTO client VALUES ('MrWantNothing', 0);

INSERT INTO client_products (clientDto_client_id, productDto_product_id, approbation_status) VALUES ('MiddleMen', 5, 0);
INSERT INTO client_products (clientDto_client_id, productDto_product_id, approbation_status) VALUES ('RichMen', 6 , 0);
INSERT INTO client_products (clientDto_client_id, productDto_product_id, approbation_status) VALUES ('RichMen', 7, 0);
INSERT INTO client_products (clientDto_client_id, productDto_product_id, approbation_status) VALUES ('MrWantAll', 8, 1);
INSERT INTO client_products (clientDto_client_id, productDto_product_id, approbation_status) VALUES ('MrsVipWantAProduct', 8, 1);
INSERT INTO client_products (clientDto_client_id, productDto_product_id, approbation_status) VALUES ('MrWantNothing', 8, 2);
