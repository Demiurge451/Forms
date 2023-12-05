create table users(
    id serial primary key,
    login varchar UNIQUE,
    password varchar,
    email varchar UNIQUE,
    phone varchar UNIQUE
);

ALTER TABLE forms
ADD COLUMN user_id INT,
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id) REFERENCES users(id);