INSERT INTO images(path, content_type)
VALUES ('book_1.jpeg', 'image/jpeg');

INSERT INTO images(path, content_type)
VALUES ('book_2.jpg', 'image/jpeg');

INSERT INTO images(path, content_type)
VALUES ('book_3.jpg', 'image/jpeg');

INSERT INTO images(path, content_type)
VALUES ('book_4.jpg', 'image/jpeg');

INSERT INTO images(path, content_type)
VALUES ('book_5.jpg', 'image/jpeg');

-- password admin123
INSERT INTO users(full_name, username, password, role, avatar_id)
VALUES ('ADMINISTRATOR', 'admin', '$2a$10$CwO.rfa4lK3G./1rpKLa9eyfxTBPMBEYh7FlPIGZAZMklyL4IINHm', 'ADMIN', 1);
