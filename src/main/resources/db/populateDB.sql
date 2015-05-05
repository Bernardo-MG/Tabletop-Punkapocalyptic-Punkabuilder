INSERT INTO factions (id, name) VALUES (1, 'gangers');
INSERT INTO factions (id, name) VALUES (2, 'mutards');

INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (1, 'addler', 6, 1, 1, 1, 0, 2, 0, 2);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (2, 'bobblehead', 80, 3, 2, 1, 3, 2, 7, 2);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (3, 'chief', 70, 3, 6, 6, 6, 4, 5, 4);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (4, 'badass', 40, 2, 5, 6, 6, 4, 4, 4);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (5, 'brute', 40, 2, 4, 5, 4, 5, 2, 4);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (6, 'ganger', 30, 2, 5, 5, 5, 4, 3, 3);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (7, 'mongrelmorph', 16, 2, 7, 4, 0, 4, 0, 3);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (8, 'mutard', 12, 2, 3, 3, 3, 3, 3, 3);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (9, 'pit_beast', 70, 2, 2, 4, 2, 6, 1, 6);
INSERT INTO unit_templates (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (10, 'scumbag', 20, 2, 4, 4, 4, 4, 3, 3);

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

INSERT INTO unit_template_rules (template_id, rule_id) VALUES (1, 8);
INSERT INTO unit_template_rules (template_id, rule_id) VALUES (1, 9);
INSERT INTO unit_template_rules (template_id, rule_id) VALUES (7, 10);
INSERT INTO unit_template_rules (template_id, rule_id) VALUES (7, 11);
INSERT INTO unit_template_rules (template_id, rule_id) VALUES (9, 12);