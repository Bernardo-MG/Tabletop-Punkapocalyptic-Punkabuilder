INSERT INTO factions (id, name) VALUES (1, 'gangers');
INSERT INTO factions (id, name) VALUES (2, 'mutards');

INSERT INTO special_rules (id, name) VALUES (1, 'cumbersome');
INSERT INTO special_rules (id, name) VALUES (2, 'two-handed');
INSERT INTO special_rules (id, name) VALUES (3, 'automatic');
INSERT INTO special_rules (id, name) VALUES (4, 'firearm');
INSERT INTO special_rules (id, name) VALUES (5, 'hard_to_use');
INSERT INTO special_rules (id, name) VALUES (6, 'dead_slow');
INSERT INTO special_rules (id, name) VALUES (7, 'pellets');
INSERT INTO special_rules (id, name) VALUES (8, 'pack');
INSERT INTO special_rules (id, name) VALUES (9, 'mong');
INSERT INTO special_rules (id, name) VALUES (10, 'beast');
INSERT INTO special_rules (id, name) VALUES (11, 'swift');
INSERT INTO special_rules (id, name) VALUES (12, 'berserk');

INSERT INTO armors (id, name, armor) VALUES (1, 'unarmored', 0);
INSERT INTO armors (id, name, armor) VALUES (2, 'thick_clothes', 1);
INSERT INTO armors (id, name, armor) VALUES (3, 'hardened_leather', 2);
INSERT INTO armors (id, name, armor) VALUES (4, 'bulletproof_vest', 3);
INSERT INTO armors (id, name, armor) VALUES (5, 'metallic_armor', 4);
INSERT INTO armors (id, name, armor) VALUES (6, 'metal_plate', 5);


INSERT INTO faction_view_config (id, faction_id, image) VALUES (1, 1, 'images/logo_gangers_small.jpg');
INSERT INTO faction_view_config (id, faction_id, image) VALUES (2, 2, 'images/logo_mutants_small.jpg');


INSERT INTO armor_rules (armor_id, rule_id) VALUES (6, 1);
