package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

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
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionUnitMutationsParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.TransactionUnitWeaponsParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.UnitMutationsTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.UnitWeaponsTransactionParser;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser.XMLFileCombinerParser;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitTemplateRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponEnhancementRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponRepository;

@Component("dataBuilder")
public final class DefaultDataBuilder implements
        ApplicationListener<ContextRefreshedEvent> {

    private final XPathFactory                                 factoryXpath     = XPathFactory
                                                                                        .instance();
    private Boolean                                            loaded           = false;
    private final Map<String, Repository<?>>                   repos;
    private final Document                                     source;
    private final Map<String, Collection<Map<String, Object>>> transactionsData = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    @Autowired
    public DefaultDataBuilder(@Qualifier("dataSources") final Object sources,
            @Qualifier("repositoryMap") final Object repos) {
        super();

        checkNotNull(sources, "Received a null pointer as sources");
        checkNotNull(repos, "Received a null pointer as repositories");

        this.repos = (Map<String, Repository<?>>) repos;

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
        buildTransactions(
                filterDocument(doc, "//unit_mutations/unit_mutation"),
                new UnitMutationsTransactionParser(), "unit_mutation");
        buildTransactions(filterDocument(doc, "//unit_weapon"),
                new UnitWeaponsTransactionParser(), "unit_weapon");
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
        final UnitTemplateRepository unitRepo;

        unitRepo = (UnitTemplateRepository) repos.get("unit");

        saveTransactions("unit_mutation", new TransactionUnitMutationsParser(
                unitRepo, (MutationRepository) repos.get("mutation")));
        saveTransactions("unit_weapon", new TransactionUnitWeaponsParser(
                unitRepo, (WeaponRepository) repos.get("weapon"),
                (WeaponEnhancementRepository) repos.get("enhancement")));
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

}
