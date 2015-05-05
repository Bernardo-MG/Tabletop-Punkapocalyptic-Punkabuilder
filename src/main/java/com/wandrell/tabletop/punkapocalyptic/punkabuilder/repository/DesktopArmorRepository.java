package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.ArmorRepository;

@Component("armorRepo")
public final class DesktopArmorRepository extends JPARepository<Armor>
        implements ArmorRepository {

    public DesktopArmorRepository() {
        super(new DefaultQueryData("SELECT armor FROM Armor armor"));
    }

    @Override
    public final Armor getByName(final String name) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("armor", name);

        return getEntity(new DefaultQueryData(
                "SELECT armor FROM Armor armor WHERE armor.armorName = :armor",
                params));
    }

    @Override
    public final Collection<Armor>
            getByNamesList(final Collection<String> names) {
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
        params.put("rules", namesResult.toString());

        return getCollection(new DefaultQueryData(
                "SELECT armor FROM Armor armor WHERE armor.armorName IN :rules",
                params));
    }

}
