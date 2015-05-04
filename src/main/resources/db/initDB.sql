DROP TABLE factions IF EXISTS;

DROP TABLE faction_view_config IF EXISTS;


CREATE TABLE factions (
	id				INTEGER IDENTITY PRIMARY KEY,
	nameToken		VARCHAR(30)
);


CREATE TABLE faction_view_config (
	id				INTEGER IDENTITY PRIMARY KEY,
  	faction_id		INTEGER NOT NULL,
	image			VARCHAR(50),
);
ALTER TABLE faction_view_config ADD CONSTRAINT fk_faction_view_config_faction FOREIGN KEY (faction_id) REFERENCES factions (id);
