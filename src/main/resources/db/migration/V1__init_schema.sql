CREATE TABLE categories
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NULL,
    deleted_at datetime NULL,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE order_products
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NULL,
    deleted_at datetime NULL,
    quantity   INT NULL,
    order_id   BIGINT   NOT NULL,
    product_id BIGINT   NOT NULL,
    CONSTRAINT pk_order_products PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NULL,
    deleted_at datetime NULL,
    status     SMALLINT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime     NOT NULL,
    updated_at    datetime NULL,
    deleted_at    datetime NULL,
    title         VARCHAR(255) NOT NULL,
    `description` TEXT NULL,
    price         DECIMAL      NOT NULL,
    image         VARCHAR(255) NULL,
    category_id   BIGINT       NOT NULL,
    rating        DECIMAL      NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDER_PRODUCTS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDER_PRODUCTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);