create table invoice
(
    invoice_id      IDENTITY NOT NULL PRIMARY KEY,
    invoice_no      VARCHAR(50),
    invoice_date    TIMESTAMP,
    customer_id     VARCHAR(256),
    country         VARCHAR(256),
    stock_code      VARCHAR(50),
    description     VARCHAR(256),
    quantity        INTEGER,
    unit_price      DOUBLE,
    );
