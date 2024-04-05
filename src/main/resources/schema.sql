CREATE TABLE conversion_history(
    transaction_id BIGINT NOT NULL,
    from_currency VARCHAR(4) NOT NULL,
    to_currency VARCHAR(4) NOT NULL,
    amount_converted INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO conversion_history (transaction_id, from_currency, to_currency, amount_converted) VALUES (1, 'USD', 'BGN', 10);
INSERT INTO conversion_history (transaction_id, from_currency, to_currency, amount_converted) VALUES (2, 'BGN', 'EUR', 20);
INSERT INTO conversion_history (transaction_id, from_currency, to_currency, amount_converted) VALUES (3, 'EUR', 'GBP', 30);
INSERT INTO conversion_history (transaction_id, from_currency, to_currency, amount_converted) VALUES (4, 'EUR', 'BGN', 40);