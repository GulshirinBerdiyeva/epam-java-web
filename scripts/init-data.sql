INSERT INTO user (username, password, role) VALUES ('admin', MD5('admin'), 'admin');

SELECT * FROM user WHERE username = 'admin' AND password = MD5('admin');

INSERT INTO music (title, artist, audio_path, image_path, price)
            VALUES ('Baila conmigo', 'Selena Gomez', 'musics/SelenaGomez.mp3', 'images/SelenaGomez.jpg', 2);

SELECT * FROM music;