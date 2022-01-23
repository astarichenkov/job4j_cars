DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE ad
(
    id          SERIAL PRIMARY KEY,
    created     TIMESTAMP,
    description TEXT,
    is_sold      boolean,
    photo_id     INT,
    price       INT,
    author_id   INT,
    car_id      INT,
    city_id     INT
)
