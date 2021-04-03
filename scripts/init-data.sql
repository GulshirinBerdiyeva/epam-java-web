INSERT INTO user (name, password, role)
            VALUES ('admin', MD5('admin'), 'admin');

SELECT * FROM user WHERE login = 'admin' AND password = MD5('admin');

INSERT INTO music (title, artist, audio_path, image_path, price)
            VALUES ('Perfect', 'Ed Sheeran', 'musics/EdSheeran.mp3', 'images/EdSheeran.jpg', 2);

SELECT * FROM music;