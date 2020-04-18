
INSERT INTO product (id, product_type, product_level, name) SELECT 1, 0, 0, 'ProduitA' WHERE NOT EXISTS ( SELECT id FROM product WHERE id = 1);

INSERT INTO product (id, product_type, product_level, name) SELECT 2, 1, 0, 'ProduitB' WHERE NOT EXISTS ( SELECT id FROM product WHERE id = 2);

INSERT INTO product (id, product_type, product_level, name) SELECT 3, 0, 1, 'ProduitC' WHERE NOT EXISTS ( SELECT id FROM product WHERE id = 3);

INSERT INTO product (id, product_type, product_level, name) SELECT 4, 1, 1, 'ProduitD' WHERE NOT EXISTS ( SELECT id FROM product WHERE id = 4);