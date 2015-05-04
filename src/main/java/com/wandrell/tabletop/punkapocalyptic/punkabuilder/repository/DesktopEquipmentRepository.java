package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.repository.EquipmentRepository;

@Component("equipmentRepo")
public final class DesktopEquipmentRepository implements EquipmentRepository {

    private final FilteredRepository<Equipment, Predicate<Equipment>> baseRepo;

    public DesktopEquipmentRepository() {
        super();

        baseRepo = new CollectionRepository<Equipment>();
    }

    @Override
    public final void add(final Equipment entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<Equipment> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Collection<Equipment> getByNamesList(
            final Collection<String> names) {
        return getBaseRepository().getCollection(new Predicate<Equipment>() {

            @Override
            public final boolean apply(final Equipment piece) {
                return names.contains(piece.getName());
            }

        });
    }

    @Override
    public final void remove(final Equipment entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final Equipment entity) {
        getBaseRepository().update(entity);
    }

    private final FilteredRepository<Equipment, Predicate<Equipment>>
            getBaseRepository() {
        return baseRepo;
    }

}
