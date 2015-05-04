package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.repository.SpecialRuleRepository;

@Component("ruleRepo")
public final class DesktopSpecialRuleRepository implements
        SpecialRuleRepository {

    private final FilteredRepository<SpecialRule, Predicate<SpecialRule>> baseRepo;

    public DesktopSpecialRuleRepository() {
        super();

        baseRepo = new CollectionRepository<SpecialRule>();
    }

    @Override
    public final void add(final SpecialRule entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<SpecialRule> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Collection<SpecialRule> getByNamesList(
            final Collection<String> names) {
        return getBaseRepository().getCollection(new Predicate<SpecialRule>() {

            @Override
            public final boolean apply(final SpecialRule input) {
                return names.contains(input.getName());
            }

        });
    }

    @Override
    public final void remove(final SpecialRule entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final SpecialRule entity) {
        getBaseRepository().update(entity);
    }

    private final FilteredRepository<SpecialRule, Predicate<SpecialRule>>
            getBaseRepository() {
        return baseRepo;
    }

}
