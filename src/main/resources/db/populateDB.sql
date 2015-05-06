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

INSERT INTO equipment (id, name, cost) VALUES (1, 'biohazard_protection', 8);

INSERT INTO weapon_enhancement (id, name, firearm) VALUES (1, 'bayonet', true);

INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (1, 'able', 20, 1, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (2, 'acid_spit', 5, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (3, 'brutal_charge', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (4, 'claws', 2, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (5, 'extra_limbs', 10, 0, 0, 2, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (6, 'frog', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (7, 'hunk', 10, 0, 0, 0, 0, 2, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (8, 'mimetism', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (9, 'organic_missile', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (10, 'poisoned_claws', 8, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (11, 'prehensile_limb', 5, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (12, 'psychic_echo', 20, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (13, 'resilient', 10, 0, 0, 0, 0, 0, 0, 2);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (14, 'runner', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (15, 'sharp_senses', 8, 0, 2, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (16, 'sharp_tail', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (17, 'sonar', 5, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (18, 'sticky', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (19, 'sure_feet', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (20, 'thorns', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (21, 'tough_skin_1', 5, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (22, 'tough_skin_3', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions, agility, combat, precision, strength, tech, toughness) VALUES (23, 'tough_skin_5', 20, 0, 0, 0, 0, 0, 0, 0);

INSERT INTO faction_view_config (id, faction_id, image) VALUES (1, 1, 'images/logo_gangers_small.jpg');
INSERT INTO faction_view_config (id, faction_id, image) VALUES (2, 2, 'images/logo_mutants_small.jpg');


INSERT INTO armor_rules (armor_id, rule_id) VALUES (6, 1);

INSERT INTO unit_template_rules (template_id, rule_id) VALUES (1, 8);
INSERT INTO unit_template_rules (template_id, rule_id) VALUES (1, 9);
INSERT INTO unit_template_rules (template_id, rule_id) VALUES (7, 10);
INSERT INTO unit_template_rules (template_id, rule_id) VALUES (7, 11);
INSERT INTO unit_template_rules (template_id, rule_id) VALUES (9, 12);


INSERT INTO faction_units (id, faction_id, template_id) VALUES (1, 1, 3);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (2, 1, 4);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (3, 1, 5);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (4, 1, 6);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (5, 1, 10);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (6, 2, 1);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (7, 2, 8);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (8, 2, 2);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (9, 2, 7);
INSERT INTO faction_units (id, faction_id, template_id) VALUES (10, 2, 9);

INSERT INTO constraints_data (id, name, context) VALUES (1, 'unique', '');
INSERT INTO constraints_data (id, name, context) VALUES (2, 'up_to_half_points', '');
INSERT INTO constraints_data (id, name, context) VALUES (3, 'dependant', 'bobblehead,2');

INSERT INTO faction_unit_constraints (faction_unit_id, constraint_id) VALUES (1, 1);
INSERT INTO faction_unit_constraints (faction_unit_id, constraint_id) VALUES (5, 2);
INSERT INTO faction_unit_constraints (faction_unit_id, constraint_id) VALUES (6, 3);