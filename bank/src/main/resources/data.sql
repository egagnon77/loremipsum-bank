
INSERT INTO product (id, product_type, product_level, name) VALUES (1, 0, 0, 'ProduitA') ON CONFLICT DO NOTHING ;
INSERT INTO product (id, product_type, product_level, name) VALUES (2, 1, 0, 'ProduitB') ON CONFLICT DO NOTHING ;
INSERT INTO product (id, product_type, product_level, name) VALUES (3, 0, 1, 'ProduitC') ON CONFLICT DO NOTHING ;
INSERT INTO product (id, product_type, product_level, name) VALUES (4, 1, 1, 'ProduitD') ON CONFLICT DO NOTHING ;