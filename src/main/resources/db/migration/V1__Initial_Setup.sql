-- 1. SQL script for the ec_category table
CREATE TABLE ec_category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    create_user_id BIGINT,
    update_user_id BIGINT
);

-- 2. SQL script for the ec_product table
CREATE TABLE ec_product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE PRECISION NOT NULL,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    create_user_id BIGINT,
    update_user_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES ec_category(id)
);

-- 3. SQL script for the ec_shopper table (assuming this table exists based on foreign key in ec_order)
CREATE TABLE ec_shopper (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- 4. SQL script for the ec_order table
CREATE TABLE ec_order (
    id BIGSERIAL PRIMARY KEY,
    indicationOrderNumber VARCHAR(255) NOT NULL,
    orderDate TIMESTAMP NOT NULL,
    paymentDate TIMESTAMP,
    paymentType VARCHAR(255),
    status VARCHAR(255) NOT NULL DEFAULT 'Shipping Complete',
    shopperType VARCHAR(255) NOT NULL,
    shopperName VARCHAR(255) NOT NULL,
    shopperPaidAmount DOUBLE PRECISION NOT NULL,
    discountTotal DOUBLE PRECISION DEFAULT 0.0,
    shopper_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    create_user_id BIGINT,
    update_user_id BIGINT,
    FOREIGN KEY (shopper_id) REFERENCES ec_shopper(id)
);

-- 5. SQL script for the ec_order_item table
CREATE TABLE ec_order_item (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    shipping_type VARCHAR(255) NOT NULL,
    shipping_status VARCHAR(255) NOT NULL,
    payment_type VARCHAR(255) NOT NULL,
    payment_status VARCHAR(255) NOT NULL,
    sku VARCHAR(255) NOT NULL,
    discount_price DOUBLE PRECISION NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    create_user_id BIGINT,
    update_user_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES ec_order(id)
);

-- 6. SQL script for the ec_attribute table
CREATE TABLE ec_attribute (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    create_user_id BIGINT,
    update_user_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES ec_product(id)
);

-- 7. SQL script for the ec_image table
CREATE TABLE ec_image (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    create_user_id BIGINT,
    update_user_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES ec_product(id)
);

-- 8. SQL script for the ec_merchant table
CREATE TABLE ec_merchant (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    create_user_id BIGINT,
    update_user_id BIGINT
);

-- 9. SQL script for the ec_refund_record table
CREATE TABLE ec_refund_record (
    id SERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    status VARCHAR(255) NOT NULL,
    complete_time TIMESTAMP,
    payment_type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    create_user_id BIGINT,
    update_user_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES ec_order(id)
);

-- 10. SQL script for the google_user table
CREATE TABLE google_user (
  id SERIAL PRIMARY KEY,
  name VARCHAR(128) NOT NULL,
  email VARCHAR(128) NOT NULL
);
