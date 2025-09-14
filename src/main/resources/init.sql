CREATE TABLE club (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    established_date DATE,
    stadium_name VARCHAR(100),
    city VARCHAR(50),
    league VARCHAR(50),
    titles_won INT
);

CREATE TABLE manager (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nationality VARCHAR(50),
    date_of_birth DATE,
    titles_won INT,
    club_id BIGINT UNIQUE REFERENCES club(id)
);

CREATE TABLE player (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(20),
    jersey_number INT,
    date_of_birth DATE,
    nationality VARCHAR(50),
    club_id BIGINT REFERENCES club(id)
);
