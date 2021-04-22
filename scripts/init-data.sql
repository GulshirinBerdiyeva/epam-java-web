INSERT INTO user (username, password, role) VALUES ('admin', MD5('admin'), 'admin');

SELECT * FROM user WHERE username = 'admin' AND password = MD5('admin');

INSERT INTO music (title, artist, audio_file_name, image_file_name, price)
            VALUES ('Baila conmigo', 'Selena Gomez', 'SelenaGomez.mp3', 'SelenaGomez.jpg', 2);

SELECT * FROM music;