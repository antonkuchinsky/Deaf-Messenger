CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    UNIQUE (username)
    );

CREATE TABLE IF NOT EXISTS contacts (
    id UUID PRIMARY KEY,
    user_id UUID,
    username VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id)
    );
