CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    phoneNumber VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    customerName VARCHAR(255) NOT NULL
);
