package com.wandrell.tabletop.punkapocalyptic.punkabuilder.procedure;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.procedure.DefaultGangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.GangBuilderStatusChangedListener;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.GangChangedListener;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.UnitChangedListener;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionUnitAvailabilityRepository;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.valuebox.ValueBox;

@Component("gangBuilderManager")
public final class DesktopGangBuilderManager implements GangBuilderManager {

    private final GangBuilderManager baseManager;

    @Autowired
    public DesktopGangBuilderManager(
            final FactionUnitAvailabilityRepository unitAvaRepository,
            final RulesetService rulesetService,
            final LocalizationService localizationService) {
        super();

        baseManager = new DefaultGangBuilderManager(unitAvaRepository,
                rulesetService, localizationService);
    }

    @Override
    public final void
            addGangChangedListener(final GangChangedListener listener) {
        getGangBuilderManager().addGangChangedListener(listener);
    }

    @Override
    public final void addStatusChangedListener(
            final GangBuilderStatusChangedListener listener) {
        getGangBuilderManager().addStatusChangedListener(listener);
    }

    @Override
    public final void
            addUnitChangedListener(final UnitChangedListener listener) {
        getGangBuilderManager().addUnitChangedListener(listener);
    }

    @Override
    public final Gang getGang() {
        return getGangBuilderManager().getGang();
    }

    @Override
    public final ValueBox getMaxUnits() {
        return getGangBuilderManager().getMaxUnits();
    }

    @Override
    public final Collection<UnitTemplate> getUnitOptions() {
        return getGangBuilderManager().getUnitOptions();
    }

    @Override
    public final Collection<String> getValidationMessages() {
        return getGangBuilderManager().getValidationMessages();
    }

    @Override
    public final void removeGangChangedListener(
            final GangChangedListener listener) {
        getGangBuilderManager().removeGangChangedListener(listener);
    }

    @Override
    public final void removeStatusChangedListener(
            final GangBuilderStatusChangedListener listener) {
        getGangBuilderManager().removeStatusChangedListener(listener);
    }

    @Override
    public final void removeUnitChangedListener(
            final UnitChangedListener listener) {
        getGangBuilderManager().removeUnitChangedListener(listener);
    }

    @Override
    public final void setGang(final Gang gang) {
        getGangBuilderManager().setGang(gang);
    }

    @Override
    public final Boolean validate() {
        return getGangBuilderManager().validate();
    }

    private final GangBuilderManager getGangBuilderManager() {
        return baseManager;
    }

}
