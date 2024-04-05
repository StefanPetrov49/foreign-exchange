CREATE TABLE conversion_history(
    transaction_id BIGINT NOT NULL,
    from_currency VARCHAR(4) NOT NULL,
    to_currency VARCHAR(4) NOT NULL,
    amount_converted INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);