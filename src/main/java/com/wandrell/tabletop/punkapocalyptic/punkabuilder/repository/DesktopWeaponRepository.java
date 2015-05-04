package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponRepository;

@Component("weaponRepo")
public final class DesktopWeaponRepository implements WeaponRepository {

    private final FilteredRepository<Weapon, Predicate<Weapon>> baseRepo;

    public DesktopWeaponRepository() {
        super();

        baseRepo = new CollectionRepository<Weapon>();
    }

    @Override
    public final void add(final Weapon entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<Weapon> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Collection<Weapon> getByNamesList(
            final Collection<String> names) {
        return getBaseRepository().getCollection(new Predicate<Weapon>() {

            @Override
            public final boolean apply(final Weapon weapon) {
                return names.contains(weapon.getName());
            }

        });
    }

    @Override
    public final void remove(final Weapon entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final Weapon entity) {
        getBaseRepository().update(entity);
    }

    private final FilteredRepository<Weapon, Predicate<Weapon>>
            getBaseRepository() {
        return baseRepo;
    }

}
