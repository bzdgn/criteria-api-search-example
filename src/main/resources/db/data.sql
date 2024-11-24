INSERT INTO product (name, price, category) VALUES ('Laptop',         1500, 'electronics')  ON CONFLICT (name, category) DO NOTHING;
INSERT INTO product (name, price, category) VALUES ('ACME Phone',     900,  'electronics')  ON CONFLICT (name, category) DO NOTHING;
INSERT INTO product (name, price, category) VALUES ('Headphones',     120,  'electronics')  ON CONFLICT (name, category) DO NOTHING;
INSERT INTO product (name, price, category) VALUES ('ACME OS',        99,   'software')     ON CONFLICT (name, category) DO NOTHING;
INSERT INTO product (name, price, category) VALUES ('ACME Antivirus', 56,   'software')     ON CONFLICT (name, category) DO NOTHING;
INSERT INTO product (name, price, category) VALUES ('BW Draw',        278,  'software')     ON CONFLICT (name, category) DO NOTHING;
INSERT INTO product (name, price, category) VALUES ('Cheap Antivirus',33,   'software')     ON CONFLICT (name, category) DO NOTHING;
INSERT INTO product (name, price, category) VALUES ('VZD OS',         56,   'software')     ON CONFLICT (name, category) DO NOTHING;
