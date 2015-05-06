DROP TABLE factions IF EXISTS;
DROP TABLE unit_templates IF EXISTS;
DROP TABLE armors IF EXISTS;
DROP TABLE equipment IF EXISTS;
DROP TABLE weapon_enhancement IF EXISTS;
DROP TABLE special_rules IF EXISTS;
DROP TABLE mutations IF EXISTS;

DROP TABLE faction_view_config IF EXISTS;

DROP TABLE armor_rules IF EXISTS;
DROP TABLE unit_template_rules IF EXISTS;

DROP TABLE faction_units IF EXISTS;
DROP TABLE faction_unit_constraints IF EXISTS;
DROP TABLE constraints_data IF EXISTS;


CREATE TABLE factions (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30)
);

CREATE TABLE unit_templates (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	cost			INTEGER,
	actions			INTEGER,
	agility			INTEGER,
	combat			INTEGER,
	precision		INTEGER,
	strength		INTEGER,
	tech			INTEGER,
	toughness		INTEGER
);

CREATE TABLE armors (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	armor			INTEGER
);

CREATE TABLE equipment (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	cost			INTEGER
);

CREATE TABLE weapon_enhancement (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	cost			INTEGER,
	firearm			BOOLEAN
);

CREATE TABLE mutations (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(50),
	cost			INTEGER,
	actions			INTEGER,
	agility			INTEGER,
	combat			INTEGER,
	precision		INTEGER,
	strength		INTEGER,
	tech			INTEGER,
	toughness		INTEGER
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

CREATE TABLE unit_template_rules (
	template_id		INTEGER NOT NULL,
  	rule_id			INTEGER NOT NULL
);
ALTER TABLE unit_template_rules ADD CONSTRAINT fk_unit_template_rules_template FOREIGN KEY (template_id) REFERENCES unit_templates (id);
ALTER TABLE unit_template_rules ADD CONSTRAINT fk_unit_template_rules_rule FOREIGN KEY (rule_id) REFERENCES special_rules (id);


CREATE TABLE faction_units (
	id				INTEGER IDENTITY PRIMARY KEY,
  	faction_id		INTEGER NOT NULL,
	template_id		INTEGER NOT NULL
);
ALTER TABLE faction_units ADD CONSTRAINT fk_faction_units_faction FOREIGN KEY (faction_id) REFERENCES factions (id);
ALTER TABLE faction_units ADD CONSTRAINT fk_faction_units_template FOREIGN KEY (template_id) REFERENCES unit_templates (id);

CREATE TABLE constraints_data (
	id				INTEGER IDENTITY PRIMARY KEY,
  	name			VARCHAR(30),
	context			VARCHAR(50)
);

CREATE TABLE faction_unit_constraints (
	faction_unit_id	INTEGER NOT NULL,
  	constraint_id	INTEGER NOT NULL
);
ALTER TABLE faction_unit_constraints ADD CONSTRAINT fk_faction_unit_constraints_faction_unit FOREIGN KEY (faction_unit_id) REFERENCES faction_units (id);
ALTER TABLE faction_unit_constraints ADD CONSTRAINT fk_faction_unit_constraints_constraint FOREIGN KEY (constraint_id) REFERENCES constraints_data (id);
