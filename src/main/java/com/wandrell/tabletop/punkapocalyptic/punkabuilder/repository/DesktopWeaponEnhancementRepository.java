package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponEnhancementRepository;

@Component("enhancementRepo")
public final class DesktopWeaponEnhancementRepository extends
        JPARepository<WeaponEnhancement> implements WeaponEnhancementRepository {

    public DesktopWeaponEnhancementRepository() {
        super(new DefaultQueryData(
                "SELECT enhancement FROM WeaponEnhancement enhancement"));
    }

    @Override
    public final Collection<WeaponEnhancement> getByNamesList(
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
        params.put("enhancements", namesResult.toString());

        return getCollection(new DefaultQueryData(
                "SELECT enhancement FROM WeaponEnhancement enhancement WHERE enhancement.name in (:enhancements)",
                params));
    }

}
