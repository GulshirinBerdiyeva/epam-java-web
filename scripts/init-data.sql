SELECT * FROM user WHERE login = 'admin' AND password = MD5('admin');

INSERT INTO music (title, artist, audio_path, image_path)
    VALUES ('Perfect', 'Ed Sheeran', 'musics/EdSheeran.mp3', 'images/EdSheeran.jpg');

SELECT * FROM music;