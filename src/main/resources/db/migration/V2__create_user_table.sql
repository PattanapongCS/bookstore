CREATE TABLE IF NOT EXISTS migrations.user (
    id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    date_of_birth DATE NOT NULL
);