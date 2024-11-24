CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE PRECISION,
    category VARCHAR(255),
    CONSTRAINT unique_name_category UNIQUE (name, category)
);
