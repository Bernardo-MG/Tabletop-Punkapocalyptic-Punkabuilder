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

INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (1, 'melee', 'claws_and_teeth', 0, false, 0, 2, 0);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (2, 'melee', 'heavy_blade', 8, true, 0, 4, 3);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (3, 'melee', 'heavy_mace', 6, true, -1, 2, 4);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (4, 'melee', 'improvised_weapon', 0, false, -1, 0, 0);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (5, 'melee', 'light_mace', 3, false, 0, 0, 1);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (6, 'melee', 'mace', 5, false, 0, 1, 2);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (7, 'melee', 'medium_blade', 10, false, 1, 3, 2);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (8, 'melee', 'shield', 6, true, 0, 0, 0);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (9, 'melee', 'small_blade', 5, false, 0, 2, 1);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, combat, penetration, strength) 
	VALUES (10, 'melee', 'unarmed', 0, false, -2, 0, 0);

INSERT INTO weapons (id, weapon_type, name, cost, two_handed, firearm, short_cm, medium_cm, long_cm, short_inches, medium_inches, long_inches, short_penetration, medium_penetration, long_penetration, short_strength, medium_strength, long_strength) 
	VALUES (11, 'ranged', 'automatic_rifle', 10, true, true, 30, 60, 90, 12, 24, 36, 5, 5, 5, 6, 6, 6);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, firearm, short_cm, medium_cm, long_cm, short_inches, medium_inches, long_inches, short_penetration, medium_penetration, long_penetration, short_strength, medium_strength, long_strength) 
	VALUES (12, 'ranged', 'bow', 10, true, false, 20, 40, 60, 8, 16, 24, 2, 2, 2, 3, 3, 3);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, firearm, short_cm, medium_cm, long_cm, short_inches, medium_inches, long_inches, short_penetration, medium_penetration, long_penetration, short_strength, medium_strength, long_strength) 
	VALUES (13, 'ranged', 'crossbow', 8, true, false, 15, 30, 45, 6, 12, 18, 3, 3, 3, 4, 4, 4);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, firearm, short_cm, medium_cm, long_cm, short_inches, medium_inches, long_inches, short_penetration, medium_penetration, long_penetration, short_strength, medium_strength, long_strength) 
	VALUES (14, 'ranged', 'pistol', 5, false, true, 15, 30, 45, 6, 12, 18, 4, 4, 4, 6, 6, 6);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, firearm, short_cm, medium_cm, long_cm, short_inches, medium_inches, long_inches, short_penetration, medium_penetration, long_penetration, short_strength, medium_strength, long_strength) 
	VALUES (15, 'ranged', 'rifle', 8, true, true, 30, 60, 90, 12, 24, 36, 5, 5, 5, 6, 6, 6);
INSERT INTO weapons (id, weapon_type, name, cost, two_handed, firearm, short_cm, medium_cm, long_cm, short_inches, medium_inches, long_inches, short_penetration, medium_penetration, long_penetration, short_strength, medium_strength, long_strength) 
	VALUES (16, 'ranged', 'shotgun', 6, true, true, 10, 20, 30, 4, 8, 12, 3, 3, 3, 7, 6, 5);

INSERT INTO weapons (id, weapon_type, name, cost, two_handed, firearm, short_cm, medium_cm, long_cm, short_inches, medium_inches, long_inches, short_penetration, medium_penetration, long_penetration, short_strength, medium_strength, long_strength) 
	VALUES (17, 'ranged_strength', 'throwing_knife', 3, false, false, 5, 10, 15, 2, 4, 6, 2, 1, 0, 0, -1, -2);

INSERT INTO equipment (id, name, cost) VALUES (1, 'biohazard_protection', 8);

INSERT INTO weapon_enhancement (id, name, cost) VALUES (1, 'bayonet', 4);

INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (1, 'able', 20, 1, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (2, 'acid_spit', 5, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (3, 'brutal_charge', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (4, 'claws', 2, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (5, 'extra_limbs', 10, 0, 0, 2, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (6, 'frog', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (7, 'hunk', 10, 0, 0, 0, 0, 2, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (8, 'mimetism', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (9, 'organic_missile', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (10, 'poisoned_claws', 8, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (11, 'prehensile_limb', 5, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (12, 'psychic_echo', 20, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (13, 'resilient', 10, 0, 0, 0, 0, 0, 0, 2);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (14, 'runner', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (15, 'sharp_senses', 8, 0, 2, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (16, 'sharp_tail', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (17, 'sonar', 5, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (18, 'sticky', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (19, 'sure_feet', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (20, 'thorns', 6, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (21, 'tough_skin_1', 5, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (22, 'tough_skin_3', 10, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO mutations (id, name, cost, actions_bonus, agility_bonus, combat_bonus, precision_bonus, strength_bonus, tech_bonus, toughness_bonus) VALUES (23, 'tough_skin_5', 20, 0, 0, 0, 0, 0, 0, 0);

INSERT INTO faction_view_config (id, faction_id, image) VALUES (1, 1, 'images/logo_gangers_small.jpg');
INSERT INTO faction_view_config (id, faction_id, image) VALUES (2, 2, 'images/logo_mutants_small.jpg');


INSERT INTO armor_rules (armor_id, rule_id) VALUES (6, 1);

INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (2, 1);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (2, 2);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (3, 2);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (8, 2);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (11, 2);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (11, 3);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (11, 4);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (12, 2);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (12, 5);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (13, 2);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (13, 6);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (14, 4);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (15, 2);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (15, 4);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (16, 2);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (16, 4);
INSERT INTO weapon_rules (weapon_id, rule_id) VALUES (16, 7);

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


INSERT INTO armor_options (id, armor, cost) VALUES (1, 1, 0);
INSERT INTO armor_options (id, armor, cost) VALUES (2, 4, 10);
INSERT INTO armor_options (id, armor, cost) VALUES (3, 5, 10);
INSERT INTO armor_options (id, armor, cost) VALUES (4, 3, 5);
INSERT INTO armor_options (id, armor, cost) VALUES (5, 5, 15);
INSERT INTO armor_options (id, armor, cost) VALUES (6, 5, 10);
INSERT INTO armor_options (id, armor, cost) VALUES (7, 6, 15);
INSERT INTO armor_options (id, armor, cost) VALUES (8, 3, 5);
INSERT INTO armor_options (id, armor, cost) VALUES (9, 2, 0);
INSERT INTO armor_options (id, armor, cost) VALUES (10, 3, 0);

INSERT INTO unit_armors (id, initial_armor, unit) VALUES (1, 1, 1);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (2, 1, 2);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (3, 10, 3);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (4, 9, 4);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (5, 10, 5);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (6, 9, 6);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (7, 1, 7);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (8, 1, 8);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (9, 1, 9);
INSERT INTO unit_armors (id, initial_armor, unit) VALUES (10, 9, 10);

INSERT INTO unit_armor_options (unit_armor_id, option_id) VALUES (3, 2);
INSERT INTO unit_armor_options (unit_armor_id, option_id) VALUES (3, 3);
INSERT INTO unit_armor_options (unit_armor_id, option_id) VALUES (4, 4);
INSERT INTO unit_armor_options (unit_armor_id, option_id) VALUES (4, 5);
INSERT INTO unit_armor_options (unit_armor_id, option_id) VALUES (5, 6);
INSERT INTO unit_armor_options (unit_armor_id, option_id) VALUES (5, 7);
INSERT INTO unit_armor_options (unit_armor_id, option_id) VALUES (6, 8);


INSERT INTO unit_equipment (id, unit) VALUES (1, 3);
INSERT INTO unit_equipment (id, unit) VALUES (2, 4);
INSERT INTO unit_equipment (id, unit) VALUES (3, 5);
INSERT INTO unit_equipment (id, unit) VALUES (4, 6);
INSERT INTO unit_equipment (id, unit) VALUES (5, 10);

INSERT INTO unit_equipment_equipment (unit_equipment_id, equipment_id) VALUES (1, 1);
INSERT INTO unit_equipment_equipment (unit_equipment_id, equipment_id) VALUES (2, 1);
INSERT INTO unit_equipment_equipment (unit_equipment_id, equipment_id) VALUES (3, 1);
INSERT INTO unit_equipment_equipment (unit_equipment_id, equipment_id) VALUES (4, 1);
INSERT INTO unit_equipment_equipment (unit_equipment_id, equipment_id) VALUES (5, 1);


INSERT INTO unit_mutations (id, unit, max) VALUES (1, 8, 3);
INSERT INTO unit_mutations (id, unit, max) VALUES (2, 9, 1);

INSERT INTO unit_mutations_mutations (unit_mutation_id, mutation_id) VALUES (2, 2);
INSERT INTO unit_mutations_mutations (unit_mutation_id, mutation_id) VALUES (2, 3);
INSERT INTO unit_mutations_mutations (unit_mutation_id, mutation_id) VALUES (2, 5);
INSERT INTO unit_mutations_mutations (unit_mutation_id, mutation_id) VALUES (2, 10);
INSERT INTO unit_mutations_mutations (unit_mutation_id, mutation_id) VALUES (2, 19);
INSERT INTO unit_mutations_mutations (unit_mutation_id, mutation_id) VALUES (2, 20);


INSERT INTO unit_weapons (id, unit, min, max) VALUES (1, 3, 1, 2);
INSERT INTO unit_weapons (id, unit, min, max) VALUES (2, 4, 1, 2);
INSERT INTO unit_weapons (id, unit, min, max) VALUES (3, 5, 1, 2);
INSERT INTO unit_weapons (id, unit, min, max) VALUES (4, 6, 1, 2);
INSERT INTO unit_weapons (id, unit, min, max) VALUES (5, 8, 0, 1);
INSERT INTO unit_weapons (id, unit, min, max) VALUES (6, 9, 0, 1);
INSERT INTO unit_weapons (id, unit, min, max) VALUES (7, 10, 1, 2);

INSERT INTO weapon_options (id, weapon) VALUES (1, 11);
INSERT INTO weapon_options (id, weapon) VALUES (2, 13);
INSERT INTO weapon_options (id, weapon) VALUES (3, 6);
INSERT INTO weapon_options (id, weapon) VALUES (4, 7);
INSERT INTO weapon_options (id, weapon) VALUES (5, 14);
INSERT INTO weapon_options (id, weapon) VALUES (6, 15);
INSERT INTO weapon_options (id, weapon) VALUES (7, 16);
INSERT INTO weapon_options (id, weapon) VALUES (8, 2);
INSERT INTO weapon_options (id, weapon) VALUES (9, 3);
INSERT INTO weapon_options (id, weapon) VALUES (10, 8);
INSERT INTO weapon_options (id, weapon) VALUES (11, 12);
INSERT INTO weapon_options (id, weapon) VALUES (12, 5);
INSERT INTO weapon_options (id, weapon) VALUES (13, 9);
INSERT INTO weapon_options (id, weapon) VALUES (14, 17);
INSERT INTO weapon_options (id, weapon) VALUES (15, 9);

INSERT INTO weapon_options_enhancements (weapon_option_id, enhancement_id) VALUES (1, 1);
INSERT INTO weapon_options_enhancements (weapon_option_id, enhancement_id) VALUES (5, 1);
INSERT INTO weapon_options_enhancements (weapon_option_id, enhancement_id) VALUES (6, 1);
INSERT INTO weapon_options_enhancements (weapon_option_id, enhancement_id) VALUES (7, 1);

INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (1, 1);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (1, 2);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (1, 3);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (1, 4);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (1, 5);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (1, 6);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (1, 7);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (2, 1);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (2, 2);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (2, 3);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (2, 4);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (2, 5);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (2, 6);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (2, 7);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (3, 8);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (3, 9);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (3, 3);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (3, 10);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (3, 7);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 11);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 2);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 12);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 3);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 5);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 6);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 7);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 13);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (4, 14);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (5, 3);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (5, 12);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (5, 4);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (5, 15);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (5, 14);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (6, 8);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (6, 9);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (6, 3);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (7, 11);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (7, 12);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (7, 5);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (7, 15);
INSERT INTO unit_weapons_options (unit_weapon_id, option_id) VALUES (7, 14);