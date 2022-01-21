CREATE TABLE ad
(
    id          SERIAL PRIMARY KEY,
    created     TIMESTAMP,
    description TEXT,
    issold      boolean,
    photoid     INT,
    price       INT,
    author_id   INT,
    car_id      INT,
    city_id     INT
)