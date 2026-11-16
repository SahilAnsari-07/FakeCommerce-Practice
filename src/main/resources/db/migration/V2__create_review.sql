CREATE TABLE reviews
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NULL,
    deleted_at datetime NULL,
    content    TEXT NULL,
    rating     DECIMAL      NOT NULL,
    product_id BIGINT       NOT NULL,
    order_id   BIGINT       NOT NULL,
    CONSTRAINT pk_reviews PRIMARY KEY (id),
    CONSTRAINT FK_REVIEWS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT FK_REVIEWS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id)
)