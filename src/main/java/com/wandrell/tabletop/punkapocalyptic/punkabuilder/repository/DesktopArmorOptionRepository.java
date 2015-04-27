package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.repository.ArmorOptionRepository;

@Component("armorOptionRepo")
public final class DesktopArmorOptionRepository implements
        ArmorOptionRepository {

    private final QueryableRepository<ArmorOption, Predicate<ArmorOption>> baseRepo;

    public DesktopArmorOptionRepository() {
        super();

        baseRepo = new CollectionRepository<ArmorOption>();
    }

    @Override
    public final void add(final ArmorOption entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<ArmorOption> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final void remove(final ArmorOption entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final ArmorOption entity) {
        getBaseRepository().update(entity);
    }

    private final QueryableRepository<ArmorOption, Predicate<ArmorOption>>
            getBaseRepository() {
        return baseRepo;
    }

}
