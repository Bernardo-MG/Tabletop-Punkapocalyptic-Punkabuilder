package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponRepository;

@Component("weaponRepo")
public final class DesktopWeaponRepository extends JPARepository<Weapon>
        implements WeaponRepository {

    public DesktopWeaponRepository() {
        super(new DefaultQueryData("SELECT weapon FROM Weapon weapon"));
    }

    @Override
    public final Collection<Weapon> getByNamesList(
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
        params.put("weapons", namesResult.toString());

        return getCollection(new DefaultQueryData(
                "SELECT weapon FROM Weapon weapon WHERE weapon.name IN (:weapons)",
                params));
    }

}
