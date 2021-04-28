CREATE TABLE IF NOT EXISTS migrations.order (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    created_date DATE NOT NULL
);