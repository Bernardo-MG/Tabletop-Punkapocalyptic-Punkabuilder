package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("localizationService")
public final class DefaultDesktopLocalizationService implements
        DesktopLocalizationService {

    private final ResourceBundle armorBundle;
    private final ResourceBundle bundleMessage;
    private final ResourceBundle bundleReport;
    private final ResourceBundle bundleView;
    private final ResourceBundle equipmentBundle;
    private final ResourceBundle factionNameBundle;
    private final ResourceBundle mutationNameBundle;
    private final ResourceBundle specialRuleBundle;
    private final ResourceBundle unitNameBundle;
    private final ResourceBundle weaponBundle;

    @Autowired
    public DefaultDesktopLocalizationService(
            final ResourceBundle messageBundle,
            final ResourceBundle viewBundle, final ResourceBundle reportBundle,
            final ResourceBundle factionNameBundle,
            final ResourceBundle unitNameBundle,
            final ResourceBundle specialRuleBundle,
            final ResourceBundle mutationNameBundle,
            final ResourceBundle armorBundle,
            final ResourceBundle weaponBundle,
            final ResourceBundle equipmentBundle) {
        super();

        checkNotNull(messageBundle, "Received a null pointer as message bundle");
        checkNotNull(viewBundle, "Received a null pointer as view bundle");
        checkNotNull(reportBundle,
                "Received a null pointer as the report bundle");
        checkNotNull(factionNameBundle,
                "Received a null pointer as the faction name bundle");
        checkNotNull(unitNameBundle,
                "Received a null pointer as the unit name bundle");
        checkNotNull(specialRuleBundle,
                "Received a null pointer as the special rule bundle");
        checkNotNull(mutationNameBundle,
                "Received a null pointer as the mutation name bundle");
        checkNotNull(armorBundle,
                "Received a null pointer as the armor name bundle");
        checkNotNull(weaponBundle,
                "Received a null pointer as the weapon name bundle");
        checkNotNull(equipmentBundle,
                "Received a null pointer as the equipment name bundle");

        this.bundleMessage = messageBundle;
        this.bundleReport = reportBundle;
        this.bundleView = viewBundle;

        this.factionNameBundle = factionNameBundle;
        this.unitNameBundle = unitNameBundle;
        this.specialRuleBundle = specialRuleBundle;
        this.mutationNameBundle = mutationNameBundle;
        this.armorBundle = armorBundle;
        this.weaponBundle = weaponBundle;
        this.equipmentBundle = equipmentBundle;
    }

    @Override
    public final String getArmorNameString(final String name) {
        return getValue(name, armorBundle);
    }

    @Override
    public final String getEquipmentNameString(final String name) {
        return getValue(name, equipmentBundle);
    }

    @Override
    public final String getFactionNameString(final String name) {
        return getValue(name, factionNameBundle);
    }

    @Override
    public final String getMessageString(final String key) {
        return getValue(key, bundleMessage);
    }

    @Override
    public final String getMutationNameString(final String name) {
        return getValue(name, mutationNameBundle);
    }

    @Override
    public String getReportString(String key) {
        return getValue(key, bundleReport);
    }

    @Override
    public final String getSpecialRuleNameString(final String name) {
        return getValue(name, specialRuleBundle);
    }

    @Override
    public final String getUnitNameString(final String name) {
        return getValue(name, unitNameBundle);
    }

    @Override
    public final String getViewString(final String key) {
        return getValue(key, bundleView);
    }

    @Override
    public final String getWeaponNameString(final String name) {
        return getValue(name, weaponBundle);
    }

    private final String
            getValue(final String key, final ResourceBundle bundle) {
        final String value;

        if (bundle.containsKey(key)) {
            value = bundle.getString(key);
        } else {
            value = key;
        }

        return value;
    }

}
