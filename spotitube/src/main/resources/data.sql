INSERT INTO USER
	(NAME, PASSWORD, TOKEN)
VALUES 
	('Meron', 'SecretPassword', NULL);
	
INSERT INTO USER
	(NAME, PASSWORD, TOKEN)
VALUES 
	('Dani', 'epishWW', NULL);
	
INSERT INTO PLAYLIST
	(ID, NAME, OWNER)
VALUES 
	(0, 'hiphop', 'Dani');
	
INSERT INTO PLAYLIST
	(ID, NAME, OWNER)
VALUES 
	(1, 'workout', 'Dani');
	
INSERT INTO PLAYLIST
	(ID, NAME, OWNER)
VALUES 
	(2, 'rock', 'Meron');
	
INSERT INTO PLAYLIST
	(ID, NAME, OWNER)
VALUES 
	(3, 'pop', 'Meron');
	
INSERT INTO TRACK
	(ID, TITLE, PERFORMER, DURATION, ALBUM, PLAAYCOUNT, PUBLICATIONDATE, DESCRIPTION, OFFLINEAVAILABLE)
VALUES 
	(0, 'Shook Ones', 'Mobb Deep', 326, 'Mobb Deep', 2, '01-01-2970', 'hiphop', 1);
	
INSERT INTO TRACK
	(ID, TITLE, PERFORMER, DURATION, ALBUM, PLAAYCOUNT, PUBLICATIONDATE, DESCRIPTION, OFFLINEAVAILABLE)
VALUES 
	(1, 'Bullet to the Brain', 'Lost Haven', 207, 'Bullet to the Brain', 3, '01-01-2970', 'hiphop', 0);
	
INSERT INTO TRACK
	(ID, TITLE, PERFORMER, DURATION, ALBUM, PLAAYCOUNT, PUBLICATIONDATE, DESCRIPTION, OFFLINEAVAILABLE)
VALUES 
	(3, '10Crack Commandments', 'The Notorious B.I.G.', 326, 'Lofe or Death', 6, '01-01-2970', 'hiphop', 1);
	
INSERT INTO TRACK
	(ID, TITLE, PERFORMER, DURATION, ALBUM, PLAAYCOUNT, PUBLICATIONDATE, DESCRIPTION, OFFLINEAVAILABLE)
VALUES 
	(4, 'Hell Razor', '2pac', 224, '2pac', 2, '01-01-2970', 'hiphop', 1);
	
INSERT INTO TRACK
	(ID, TITLE, PERFORMER, DURATION, ALBUM, PLAAYCOUNT, PUBLICATIONDATE, DESCRIPTION, OFFLINEAVAILABLE)
VALUES 
	(5, 'Stricken', 'Disturbed', 246, 'Disturbed', 2, '01-01-2970', 'rock', 1);
	
INSERT INTO TRACK
	(ID, TITLE, PERFORMER, DURATION, ALBUM, PLAAYCOUNT, PUBLICATIONDATE, DESCRIPTION, OFFLINEAVAILABLE)
VALUES 
	(6, 'Last Resort', 'Papa Roach', 200, 'papa roach', 2, '01-01-2970', 'rock', 1);
	
INSERT INTO TRACK
	(ID, TITLE, PERFORMER, DURATION, ALBUM, PLAAYCOUNT, PUBLICATIONDATE, DESCRIPTION, OFFLINEAVAILABLE)
VALUES 
	(7, 'New Divide', 'Linkin Park', 269, 'transformers', 5, '01-01-2970', 'rock', 1);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(0, 0);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(0, 1);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(0, 3);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(0, 4);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(1, 1);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(1, 4);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(1, 5);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(1, 6);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(1, 7);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(2, 5);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(2, 6);
	
INSERT INTO TRACKONPLAYLIST
	(TRACKID, PLAYLISTID)
VALUES 
	(3, 7);