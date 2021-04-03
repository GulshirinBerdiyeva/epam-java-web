CREATE DATABASE order_music;

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(25),
    password VARCHAR(32),
    role ENUM('admin', 'client'),
    cash DECIMAL(9,2),
    music_amount INT
);

CREATE TABLE music (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    artist VARCHAR(25),
    audio_path VARCHAR(50),
    image_path VARCHAR(50),
    price DECIMAL(9,2)
);

CREATE TABLE music_order (
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     music_id BIGINT,
     user_id BIGINT,
     FOREIGN KEY(music_id) REFERENCES music(id),
     FOREIGN KEY(user_id) REFERENCES user(id),
     date DATETIME DEFAULT CURRENT_TIMESTAMP,
     discount INT,
     CHECK (discount>=0 AND discount<100),
     final_price DECIMAL(9,2),
     payment BOOLEAN
);

CREATE TABLE playlist (
      id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
      music_id BIGINT,
      FOREIGN KEY(music_id) REFERENCES music(id),
      user_id BIGINT,
      FOREIGN KEY(user_id) REFERENCES user(id)
);

CREATE TABLE album (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    music_id BIGINT,
    FOREIGN KEY(music_id) REFERENCES music(id),
    title VARCHAR(25)
);

CREATE TABLE comment (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    music_id BIGINT,
    FOREIGN KEY(music_id) REFERENCES music(id),
    user_id BIGINT,
    FOREIGN KEY(user_id) REFERENCES user(id),
    comment VARCHAR(100),
    date DATETIME DEFAULT CURRENT_TIMESTAMP
);
