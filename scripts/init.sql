CREATE DATABASE order-music;

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25),
    login VARCHAR(25),
    password VARCHAR(32),
    role ENUM('admin', 'client'),
    cash DECIMAL(9,2),
    musical_amount INT
);

CREATE TABLE music (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    artist VARCHAR(25),
    audio_path VARCHAR(50),
    image_path VARCHAR(50)
);

CREATE TABLE music_order (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    music_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY(music_id) REFERENCES music(id),
    FOREIGN KEY(user_id) REFERENCES user(id),
    price DECIMAL(9,2),
    date DATETIME DEFAULT CURRENT_TIMESTAMP,
    bonus BOOLEAN,
    payment BOOLEAN
);

CREATE TABLE album (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    music_id BIGINT,
    FOREIGN KEY(music_id) REFERENCES music(id),
    title VARCHAR(25)
);

CREATE TABLE playlist (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    music_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY(music_id) REFERENCES music(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
);

CREATE TABLE comment (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    music_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY(music_id) REFERENCES music(id),
    FOREIGN KEY(user_id) REFERENCES user(id),
    comment VARCHAR(100),
    date DATETIME DEFAULT CURRENT_TIMESTAMP
);
