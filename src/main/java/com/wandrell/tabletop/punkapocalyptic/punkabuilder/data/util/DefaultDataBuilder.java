package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.ArmorTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.EquipmentTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.FactionTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.FactionUnitsTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.FactionViewTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.MeleeWeaponTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.MutationTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.RangedWeaponTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.RuleTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionArmorParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionEnhancementParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionEquipmentParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionFactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionFactionUnitsParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionFactionViewParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionMeleeWeaponParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionMutationParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionRangedWeaponParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionRuleParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionUnitArmorsParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionUnitEquipmentParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionUnitMutationsParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionUnitParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionUnitWeaponsParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.UnitArmorsTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.UnitEquipmentTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.UnitMutationsTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.UnitTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.UnitWeaponsTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.WeaponEnhancementTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.XMLFileCombinerParser;
import com.wandrell.tabletop.punkapocalyptic.repository.ArmorRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.EquipmentRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.SpecialRuleRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponEnhancementRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponRepository;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

@Component("dataBuilder")
public final class DefaultDataBuilder implements
        ApplicationListener<ContextRefreshedEvent> {

    private final Map<String, ResourceBundle>                  bundles;
    private final XPathFactory                                 factoryXpath     = XPathFactory
                                                                                        .instance();
    private Boolean                                            loaded           = false;
    private final ModelService                                 modelService;
    private final Map<String, Repository<?>>                   repos;
    private final RulesetService                               rulesetService;
    private final Document                                     source;
    private final Map<String, Collection<Map<String, Object>>> transactionsData = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    @Autowired
    public DefaultDataBuilder(@Qualifier("dataSources") final Object sources,
            @Qualifier("repositoryMap") final Object repos,
            @Qualifier("dataBundles") final Object bundles,
            final RulesetService rulesetService, final ModelService modelService) {
        super();

        checkNotNull(sources, "Received a null pointer as sources");
        checkNotNull(repos, "Received a null pointer as repositories");
        checkNotNull(bundles, "Received a null pointer as bundles");
        checkNotNull(modelService, "Received a null pointer as model service");
        checkNotNull(rulesetService,
                "Received a null pointer as ruleset service");

        this.repos = (Map<String, Repository<?>>) repos;
        this.bundles = (Map<String, ResourceBundle>) bundles;

        this.modelService = modelService;
        this.rulesetService = rulesetService;

        final Parser<Collection<Reader>, Document> parser;

        parser = new XMLFileCombinerParser();

        try {
            this.source = parser.parse((Collection<Reader>) sources);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO: Is it needed a repository for constraints?
    }

    @Override
    public final void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!loaded) {
            loaded = true;
            buildTransactions(getSource());
            translateTransactions();
            saveTransactions();
        }
    }

    private final void buildTransactions(final Collection<Element> nodes,
            final Parser<Element, Map<String, Object>> transParser,
            final String tag) {
        final Collection<Map<String, Object>> transactions;

        transactions = new LinkedList<>();
        for (final Element node : nodes) {
            try {
                transactions.add(transParser.parse(node));
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }

        transactionsData.put(tag, transactions);
    }

    private final void buildTransactions(final Document doc) {
        buildTransactions(filterDocument(doc, "//faction_profile"),
                new FactionTransactionParser(), "faction");
        buildTransactions(filterDocument(doc, "//rule"),
                new RuleTransactionParser(), "rule");
        buildTransactions(filterDocument(doc, "//weapon_melee_profile"),
                new MeleeWeaponTransactionParser(), "weapon_melee");
        buildTransactions(filterDocument(doc, "//weapon_ranged_profile"),
                new RangedWeaponTransactionParser(), "weapon_ranged");
        buildTransactions(filterDocument(doc, "//armor_profile"),
                new ArmorTransactionParser(), "armor");
        buildTransactions(filterDocument(doc, "//equipment_profile"),
                new EquipmentTransactionParser(), "equipment");
        buildTransactions(filterDocument(doc, "//weapon_enhancement_profile"),
                new WeaponEnhancementTransactionParser(), "enhancement");
        buildTransactions(filterDocument(doc, "//mutations/mutation"),
                new MutationTransactionParser(), "mutation");
        buildTransactions(filterDocument(doc, "//unit_profiles/unit_profile"),
                new UnitTransactionParser(), "unit");
        buildTransactions(filterDocument(doc, "//faction_unit"),
                new FactionUnitsTransactionParser(), "faction_unit");
        buildTransactions(
                filterDocument(doc, "//unit_mutations/unit_mutation"),
                new UnitMutationsTransactionParser(), "unit_mutation");
        buildTransactions(filterDocument(doc, "//unit_weapon"),
                new UnitWeaponsTransactionParser(), "unit_weapon");
        buildTransactions(filterDocument(doc, "//unit_armor"),
                new UnitArmorsTransactionParser(), "unit_armor");
        buildTransactions(filterDocument(doc, "//unit_equipment_piece"),
                new UnitEquipmentTransactionParser(), "unit_equipment");

        buildTransactions(filterDocument(doc, "//faction_view"),
                new FactionViewTransactionParser(), "faction_view");
    }

    private final Collection<Element> filterDocument(final Document doc,
            final String xpath) {
        final XPathExpression<Element> xpathExp;

        xpathExp = getXPathFactory().compile(xpath, Filters.element());
        return xpathExp.evaluate(doc);
    }

    private final Document getSource() {
        return source;
    }

    private final XPathFactory getXPathFactory() {
        return factoryXpath;
    }

    private final void saveTransactions() {
        final MeleeWeapon defaultWeaponMelee;
        final SpecialRuleRepository ruleRepo;
        final UnitRepository unitRepo;

        ruleRepo = (SpecialRuleRepository) repos.get("rule");
        unitRepo = (UnitRepository) repos.get("unit");

        saveTransactions("faction", new TransactionFactionParser(modelService));
        saveTransactions("rule", new TransactionRuleParser());
        saveTransactions("weapon_melee", new TransactionMeleeWeaponParser(
                ruleRepo));

        defaultWeaponMelee = ((WeaponRepository) repos.get("weapon"))
                .getRangedMeleeWeapon();

        saveTransactions("weapon_ranged", new TransactionRangedWeaponParser(
                ruleRepo, defaultWeaponMelee));
        saveTransactions("armor", new TransactionArmorParser(ruleRepo));
        saveTransactions("equipment", new TransactionEquipmentParser());
        saveTransactions("enhancement", new TransactionEnhancementParser());
        saveTransactions("mutation", new TransactionMutationParser());
        saveTransactions("unit", new TransactionUnitParser(ruleRepo,
                rulesetService));

        saveTransactions("faction_unit", new TransactionFactionUnitsParser(
                unitRepo, (FactionRepository) repos.get("faction")));
        saveTransactions("unit_mutation", new TransactionUnitMutationsParser(
                unitRepo, (MutationRepository) repos.get("mutation")));
        saveTransactions("unit_weapon", new TransactionUnitWeaponsParser(
                unitRepo, (WeaponRepository) repos.get("weapon"),
                (WeaponEnhancementRepository) repos.get("enhancement")));
        saveTransactions("unit_armor", new TransactionUnitArmorsParser(
                unitRepo, (ArmorRepository) repos.get("armor")));
        saveTransactions("unit_equipment", new TransactionUnitEquipmentParser(
                unitRepo, (EquipmentRepository) repos.get("equipment")));

        saveTransactions("faction_view", new TransactionFactionViewParser(
                (FactionRepository) repos.get("faction")));
    }

    @SuppressWarnings("unchecked")
    private final <V> void saveTransactions(final String tag,
            final Parser<Map<String, Object>, V> parser) {
        final Collection<V> entities;
        final Collection<Map<String, Object>> transactions;
        final Repository<V> repository;

        transactions = transactionsData.get(tag);
        repository = (Repository<V>) repos.get(tag);

        entities = new LinkedList<>();
        for (final Map<String, Object> transaction : transactions) {
            try {
                entities.add(parser.parse(transaction));
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }

        for (final V entity : entities) {
            repository.add(entity);
        }
    }

    private final void translateTransactions() {
        String text;
        ResourceBundle bundle;

        bundle = bundles.get("faction");
        for (final Map<String, Object> transaction : transactionsData
                .get("faction")) {
            text = transaction.get("name").toString();
            text = bundle.getString(text);
            transaction.put("name", text);
        }
        for (final Map<String, Object> transaction : transactionsData
                .get("faction_unit")) {
            text = transaction.get("faction").toString();
            text = bundle.getString(text);
            transaction.put("faction", text);
        }
        for (final Map<String, Object> transaction : transactionsData
                .get("faction_view")) {
            text = transaction.get("faction").toString();
            text = bundle.getString(text);
            transaction.put("faction", text);
        }
    }

}
