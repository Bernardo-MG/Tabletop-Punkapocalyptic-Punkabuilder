package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.repository.ArmorRepository;

@Component("armorRepo")
public final class DesktopArmorRepository implements ArmorRepository {

    private final QueryableRepository<Armor, Predicate<Armor>> baseRepo;

    public DesktopArmorRepository() {
        super();

        baseRepo = new CollectionRepository<Armor>();
    }

    @Override
    public final void add(final Armor entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<Armor> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Armor getByName(final String name) {
        return getBaseRepository().getEntity(new Predicate<Armor>() {

            @Override
            public final boolean apply(final Armor armor) {
                return armor.getName().equals(name);
            }

        });
    }

    @Override
    public final Collection<Armor>
            getByNamesList(final Collection<String> names) {
        return getBaseRepository().getCollection(new Predicate<Armor>() {

            @Override
            public final boolean apply(final Armor armor) {
                return names.contains(armor.getName());
            }

        });
    }

    @Override
    public final void remove(final Armor entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final Armor entity) {
        getBaseRepository().update(entity);
    }

    private final QueryableRepository<Armor, Predicate<Armor>>
            getBaseRepository() {
        return baseRepo;
    }

}
