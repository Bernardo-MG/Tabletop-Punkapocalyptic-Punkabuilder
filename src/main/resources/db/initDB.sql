DROP TABLE factions IF EXISTS;
DROP TABLE unit_templates IF EXISTS;
DROP TABLE armors IF EXISTS;
DROP TABLE weapons IF EXISTS;
DROP TABLE equipment IF EXISTS;
DROP TABLE weapon_enhancement IF EXISTS;
DROP TABLE special_rules IF EXISTS;
DROP TABLE mutations IF EXISTS;

DROP TABLE faction_view_config IF EXISTS;

DROP TABLE armor_rules IF EXISTS;
DROP TABLE weapon_rules IF EXISTS;
DROP TABLE unit_template_rules IF EXISTS;

DROP TABLE faction_units IF EXISTS;
DROP TABLE faction_unit_constraints IF EXISTS;
DROP TABLE constraints_data IF EXISTS;

DROP TABLE unit_armors IF EXISTS;
DROP TABLE armor_options IF EXISTS;
DROP TABLE unit_armor_options IF EXISTS;

DROP TABLE unit_equipment_equipment IF EXISTS;
DROP TABLE unit_equipment IF EXISTS;

DROP TABLE unit_mutations_mutations IF EXISTS;
DROP TABLE unit_mutations IF EXISTS;

DROP TABLE weapon_options_enhancements IF EXISTS;
DROP TABLE weapon_options IF EXISTS;
DROP TABLE unit_weapons_options IF EXISTS;
DROP TABLE unit_weapons IF EXISTS;

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

CREATE TABLE weapons (
	id					INTEGER IDENTITY PRIMARY KEY,
	weapon_type			VARCHAR(30),
	name				VARCHAR(30),
	cost				INTEGER,
	two_handed			BOOLEAN,
	firearm				BOOLEAN,
	combat				INTEGER,
	penetration			INTEGER,
	strength			INTEGER,
	short_cm			INTEGER,
	medium_cm			INTEGER,
	long_cm				INTEGER,
	short_inches		INTEGER,
	medium_inches		INTEGER,
	long_inches			INTEGER,
	short_penetration	INTEGER,
	medium_penetration	INTEGER,
	long_penetration	INTEGER,
	short_strength		INTEGER,
	medium_strength		INTEGER,
	long_strength		INTEGER
);

CREATE TABLE equipment (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	cost			INTEGER
);

CREATE TABLE weapon_enhancement (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	cost			INTEGER
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

CREATE TABLE weapon_rules (
	weapon_id		INTEGER NOT NULL,
  	rule_id			INTEGER NOT NULL
);
ALTER TABLE weapon_rules ADD CONSTRAINT fk_weapon_rules_weapon FOREIGN KEY (weapon_id) REFERENCES weapons (id);
ALTER TABLE weapon_rules ADD CONSTRAINT fk_weapon_rules_rule FOREIGN KEY (rule_id) REFERENCES special_rules (id);

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

CREATE TABLE armor_options (
	id				INTEGER IDENTITY PRIMARY KEY,
  	armor			INTEGER NOT NULL,
	cost			INTEGER
);
ALTER TABLE armor_options ADD CONSTRAINT fk_armor_options_armor FOREIGN KEY (armor) REFERENCES armors (id);

CREATE TABLE unit_armors (
	id				INTEGER IDENTITY PRIMARY KEY,
  	initial_armor	INTEGER NOT NULL,
	unit			INTEGER NOT NULL
);
ALTER TABLE unit_armors ADD CONSTRAINT fk_unit_armors_armor FOREIGN KEY (initial_armor) REFERENCES armor_options (id);
ALTER TABLE unit_armors ADD CONSTRAINT fk_unit_armors_unit FOREIGN KEY (unit) REFERENCES unit_templates (id);

CREATE TABLE unit_armor_options (
	unit_armor_id	INTEGER NOT NULL,
  	option_id		INTEGER NOT NULL
);
ALTER TABLE unit_armor_options ADD CONSTRAINT fk_unit_armor_options_unit_armor FOREIGN KEY (unit_armor_id) REFERENCES unit_armors (id);
ALTER TABLE unit_armor_options ADD CONSTRAINT fk_unit_armor_options_option FOREIGN KEY (option_id) REFERENCES armor_options (id);


CREATE TABLE unit_equipment (
	id				INTEGER IDENTITY PRIMARY KEY,
  	unit			INTEGER NOT NULL
);
ALTER TABLE unit_equipment ADD CONSTRAINT fk_unit_equipment_unit FOREIGN KEY (unit) REFERENCES unit_templates (id);

CREATE TABLE unit_equipment_equipment (
	unit_equipment_id	INTEGER NOT NULL,
  	equipment_id		INTEGER NOT NULL
);
ALTER TABLE unit_equipment_equipment ADD CONSTRAINT fk_unit_equipment_equipment_unit_equipment FOREIGN KEY (unit_equipment_id) REFERENCES unit_equipment (id);
ALTER TABLE unit_equipment_equipment ADD CONSTRAINT fk_unit_equipment_equipment_equipment FOREIGN KEY (equipment_id) REFERENCES equipment (id);


CREATE TABLE unit_mutations (
	id				INTEGER IDENTITY PRIMARY KEY,
  	unit			INTEGER NOT NULL,
  	max				INTEGER
);
ALTER TABLE unit_mutations ADD CONSTRAINT fk_unit_mutations_unit FOREIGN KEY (unit) REFERENCES unit_templates (id);

CREATE TABLE unit_mutations_mutations (
	unit_mutation_id	INTEGER NOT NULL,
  	mutation_id			INTEGER NOT NULL
);
ALTER TABLE unit_mutations_mutations ADD CONSTRAINT fk_unit_mutations_mutations_unit_mutation FOREIGN KEY (unit_mutation_id) REFERENCES unit_mutations (id);
ALTER TABLE unit_mutations_mutations ADD CONSTRAINT fk_unit_unit_mutations_mutations_mutation FOREIGN KEY (mutation_id) REFERENCES mutations (id);


CREATE TABLE unit_weapons (
	id				INTEGER IDENTITY PRIMARY KEY,
  	unit			INTEGER NOT NULL,
  	max				INTEGER,
  	min				INTEGER
);
ALTER TABLE unit_weapons ADD CONSTRAINT fk_unit_weapons_unit FOREIGN KEY (unit) REFERENCES unit_templates (id);

CREATE TABLE weapon_options (
	id				INTEGER IDENTITY PRIMARY KEY,
  	weapon			INTEGER NOT NULL
);
ALTER TABLE weapon_options ADD CONSTRAINT fk_weapon_options_weapon FOREIGN KEY (weapon) REFERENCES weapons (id);

CREATE TABLE weapon_options_enhancements (
	weapon_option_id	INTEGER NOT NULL,
  	enhancement_id		INTEGER NOT NULL
);
ALTER TABLE weapon_options_enhancements ADD CONSTRAINT fk_weapon_options_enhancements_weapon_option FOREIGN KEY (weapon_option_id) REFERENCES weapon_options (id);
ALTER TABLE weapon_options_enhancements ADD CONSTRAINT fk_weapon_options_enhancements_enhancement FOREIGN KEY (enhancement_id) REFERENCES weapon_enhancement (id);

CREATE TABLE unit_weapons_options (
	unit_weapon_id		INTEGER NOT NULL,
  	option_id			INTEGER NOT NULL
);
ALTER TABLE unit_weapons_options ADD CONSTRAINT fk_unit_weapons_options_unit_weapon FOREIGN KEY (unit_weapon_id) REFERENCES unit_weapons (id);
ALTER TABLE unit_weapons_options ADD CONSTRAINT fk_unit_weapons_options_option FOREIGN KEY (option_id) REFERENCES weapon_options (id);
