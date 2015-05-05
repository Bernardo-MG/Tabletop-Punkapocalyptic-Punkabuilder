package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitTemplateRepository;

@Component("unitRepo")
public final class DesktopUnitTemplateRepository extends
        JPARepository<UnitTemplate> implements UnitTemplateRepository {

    public DesktopUnitTemplateRepository() {
        super(
                new DefaultQueryData(
                        "SELECT template FROM UnitTemplate template"));
    }

    @Override
    public UnitTemplate getByNameToken(String name) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("template", name);

        return getEntity(new DefaultQueryData(
                "SELECT template FROM UnitTemplate template WHERE template.name = :template",
                params));
    }

}
