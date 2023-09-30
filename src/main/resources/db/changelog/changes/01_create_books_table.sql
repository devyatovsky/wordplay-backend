create table authors
(
    id   bigserial
        primary key,
    name varchar(255)
);
create index IDX_AUTHORS on authors (name);

create table publisher
(
    id   bigserial
        primary key,
    name varchar(255)
);

CREATE TABLE images
(
    id           SERIAL PRIMARY KEY,
    path         VARCHAR(255) NOT NULL,
    content_type VARCHAR(255) NOT NULL
);

CREATE TABLE books
(
    id           SERIAL PRIMARY KEY,
    book_name    VARCHAR(255)   NOT NULL,
    author_id    BIGINT         NOT NULL,
    cover_id     BIGINT         NOT NULL,
    publisher_id BIGINT         NOT NULL,
    publish_year VARCHAR(4)     NOT NULL,
    price        NUMERIC(10, 2) NOT NULL,
    description  TEXT           NOT NULL,
    category     VARCHAR        NOT NULL,

    FOREIGN KEY (author_id) REFERENCES authors (id),
    FOREIGN KEY (cover_id) REFERENCES images (id),
    FOREIGN KEY (publisher_id) REFERENCES publisher (id)
);

CREATE TABLE users
(
    id        SERIAL PRIMARY KEY NOT NULL,
    full_name VARCHAR(255)       NOT NULL,
    username  VARCHAR(255)       NOT NULL UNIQUE,
    password  VARCHAR(255)       NOT NULL,
    role      VARCHAR(10)        NOT NULL,
    avatar_id BIGINT,
    FOREIGN KEY (avatar_id) REFERENCES images (id) -- Предполагается, что есть таблица image с полем id
);

CREATE TABLE cart
(
    id           SERIAL PRIMARY KEY    NOT NULL,
    user_id      BIGINT,
    created_date DATE    DEFAULT now() NOT NULL,
    released     BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Создание таблицы cart_books (связь между корзиной и книгами)
CREATE TABLE cart_books
(
    cart_id BIGINT,
    book_id BIGINT,
    PRIMARY KEY (cart_id, book_id),
    FOREIGN KEY (cart_id) REFERENCES cart (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

-- Создание таблицы book_purchase_type (связь между книгой и типом покупки)
CREATE TABLE book_purchase_type
(
    id            SERIAL PRIMARY KEY NOT NULL,
    book_id       BIGINT,
    purchase_type VARCHAR(255), -- Тип покупки для книги
    cart_id       BIGINT,
    FOREIGN KEY (book_id) REFERENCES books (id),
    FOREIGN KEY (cart_id) REFERENCES cart (id)
);

INSERT INTO authors (name)
VALUES ('Джон Грей');
INSERT INTO authors (name)
VALUES ('Джейн Остин');
INSERT INTO authors (name)
VALUES ('Джордж Оруэлл');
INSERT INTO authors (name)
VALUES ('Маргарет Этвуд');
INSERT INTO authors (name)
VALUES ('Эрнест Хемингуэй');

INSERT INTO publisher (name)
VALUES ('Издательство ABC');
INSERT INTO publisher (name)
VALUES ('Издательство XYZ');
INSERT INTO publisher (name)
VALUES ('Издательство 123');
INSERT INTO publisher (name)
VALUES ('Издательство 456');
INSERT INTO publisher (name)
VALUES ('Издательство QRS');

