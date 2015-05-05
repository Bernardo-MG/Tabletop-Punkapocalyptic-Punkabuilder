package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.SpecialRuleRepository;

@Component("ruleRepo")
public final class DesktopSpecialRuleRepository extends
        JPARepository<SpecialRule> implements SpecialRuleRepository {

    public DesktopSpecialRuleRepository() {
        super(new DefaultQueryData("SELECT rule FROM SpecialRule rule"));
    }

    @Override
    public final Collection<SpecialRule> getByNamesList(
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
        params.put("rules", namesResult.toString());

        return getCollection(new DefaultQueryData(
                "SELECT rule FROM SpecialRule rule WHERE rule.nameToken IN (:rules)",
                params));
    }

}
