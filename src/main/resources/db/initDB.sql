DROP TABLE faction_view_config IF EXISTS;

CREATE TABLE faction_view_config (
	id			INTEGER IDENTITY PRIMARY KEY,
	faction		VARCHAR(30),
	image		VARCHAR(50),
);