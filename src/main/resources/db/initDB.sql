DROP TABLE factions IF EXISTS;
DROP TABLE armors IF EXISTS;
DROP TABLE special_rules IF EXISTS;

DROP TABLE faction_view_config IF EXISTS;

DROP TABLE armor_rules IF EXISTS;


CREATE TABLE factions (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30)
);

CREATE TABLE armors (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	armor			INTEGER
);

CREATE TABLE special_rules (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(50),
);


CREATE TABLE faction_view_config (
	id				INTEGER IDENTITY PRIMARY KEY,
  	faction_id		INTEGER NOT NULL,
	image			VARCHAR(50),
);
ALTER TABLE faction_view_config ADD CONSTRAINT fk_faction_view_config_faction FOREIGN KEY (faction_id) REFERENCES factions (id);


CREATE TABLE armor_rules (
	armor_id		INTEGER NOT NULL,
  	rule_id			INTEGER NOT NULL
);
ALTER TABLE armor_rules ADD CONSTRAINT fk_armor_rules_armor FOREIGN KEY (armor_id) REFERENCES armors (id);
ALTER TABLE armor_rules ADD CONSTRAINT fk_armor_rules_rule FOREIGN KEY (rule_id) REFERENCES special_rules (id);

