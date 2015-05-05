package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.EquipmentRepository;

@Component("equipmentRepo")
public final class DesktopEquipmentRepository extends JPARepository<Equipment>
        implements EquipmentRepository {

    public DesktopEquipmentRepository() {
        super(new DefaultQueryData("SELECT equipment FROM Equipment equipment"));
    }

    @Override
    public final Collection<Equipment> getByNamesList(
            final Collection<String> names) {
        final Map<String, Object> params;
        final StringBuilder namesResult;

        namesResult = new StringBuilder();
        for (final String name : names) {
            if (namesResult.length() > 0) {
                namesResult.append(", ");
            }
            namesResult.append(name);
        }

        params = new LinkedHashMap<>();
        params.put("names", namesResult.toString());

        return getCollection(new DefaultQueryData(
                "SELECT equipment FROM Equipment equipment WHERE equipment.name in (:names)",
                params));
    }

}
