package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponEnhancementRepository;

@Component("enhancementRepo")
public final class DesktopWeaponEnhancementRepository implements
        WeaponEnhancementRepository {

    private final QueryableRepository<WeaponEnhancement, Predicate<WeaponEnhancement>> baseRepo;

    public DesktopWeaponEnhancementRepository() {
        super();

        baseRepo = new CollectionRepository<WeaponEnhancement>();
    }

    @Override
    public final void add(final WeaponEnhancement entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<WeaponEnhancement> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Collection<WeaponEnhancement> getByNamesList(
            final Collection<String> names) {
        return getBaseRepository().getCollection(
                new Predicate<WeaponEnhancement>() {

                    @Override
                    public final boolean apply(
                            final WeaponEnhancement enhancement) {
                        return names.contains(enhancement.getName());
                    }

                });
    }

    @Override
    public final void remove(final WeaponEnhancement entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final WeaponEnhancement entity) {
        getBaseRepository().update(entity);
    }

    private final
            QueryableRepository<WeaponEnhancement, Predicate<WeaponEnhancement>>
            getBaseRepository() {
        return baseRepo;
    }

}
