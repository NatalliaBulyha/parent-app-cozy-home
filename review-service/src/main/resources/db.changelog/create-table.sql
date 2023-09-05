CREATE TABLE IF NOT EXISTS reviews (
    id                uuid         NOT NULL PRIMARY KEY,
    rating            char         NOT NULL,
    comment           varchar(130) NOT NULL,
    created_at        timestamp    NOT NULL,
    modified_at       timestamp    NOT NULL,
    customer_id       uuid         NOT NULL,
    product_sku_code  varchar(6)   NOT NULL
    )