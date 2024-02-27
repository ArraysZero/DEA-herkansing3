DROP TABLE trackOnPlaylist;
DROP TABLE playlist;
DROP TABLE track;
DROP TABLE users;

CREATE TABLE users(name VARCHAR(20), password VARCHAR(20), token VARCHAR(20));
CREATE TABLE track (id INT, title VARCHAR(20), performer VARCHAR(20), duration INT, album VARCHAR(20), playcount INT, publicationDate VARCHAR(20), description VARCHAR(20), offlineAvailable BOOLEAN);
CREATE TABLE playlist (id INT, name VARCHAR(20), owner VARCHAR(20));
CREATE TABLE trackOnPlaylist (trackId INT, playlistId INT);