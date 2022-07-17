INSERT INTO user (username, password, role) VALUES ('Admin', MD5('Admin_007'), 'admin');

SELECT * FROM user WHERE username = 'Admin' AND password = MD5('Admin_007');

INSERT INTO music (title, artist, audio_file_name, image_file_name, price) VALUES ('Baila conmigo', 'Selena Gomez', 'SelenaGomez.mp3', 'SelenaGomez.jpg', 3);

SELECT * FROM music;