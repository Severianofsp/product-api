CREATE TABLE product(
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION,
    weight DOUBLE,
    creation_date TIMESTAMP,
    modified_date TIMESTAMP
)