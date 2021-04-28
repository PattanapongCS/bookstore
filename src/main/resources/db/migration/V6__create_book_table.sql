CREATE TABLE IF NOT EXISTS migrations.book (
    id SERIAL PRIMARY KEY,
    book_name TEXT NOT NULL,
    author_name TEXT NOT NULL,
    price NUMERIC NOT NULL,
    is_recommended BOOLEAN NOT NULL
);