package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.service.DefaultRulesetService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

@Service("rulesetService")
public final class DesktopRulesetService implements RulesetService {

    private final RulesetService baseService;

    @Autowired
    public DesktopRulesetService(final Properties rulesetConfig) {
        super();

        baseService = new DefaultRulesetService(rulesetConfig);
    }

    @Override
    public final Integer getBulletCost() {
        return getBaseService().getBulletCost();
    }

    @Override
    public final Integer getGangValoration(final Gang gang) {
        return getBaseService().getGangValoration(gang);
    }

    @Override
    public final Integer getMaxAllowedUnits(final Gang gang) {
        return getBaseService().getMaxAllowedUnits(gang);
    }

    @Override
    public final Integer getPackMaxSize() {
        return getBaseService().getPackMaxSize();
    }

    private final RulesetService getBaseService() {
        return baseService;
    }

}
