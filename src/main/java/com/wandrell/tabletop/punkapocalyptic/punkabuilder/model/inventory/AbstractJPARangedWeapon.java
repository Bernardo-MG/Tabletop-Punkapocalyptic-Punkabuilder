/**
 * Copyright 2014 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.RangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;

public abstract class AbstractJPARangedWeapon extends AbstractJPAWeapon
        implements RangedWeapon {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "long",
                    column = @Column(name = "long_cm")),
            @AttributeOverride(name = "medium", column = @Column(
                    name = "medium_cm")),
            @AttributeOverride(name = "short", column = @Column(
                    name = "short_cm")) })
    private RangedValue distancesCM;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "long", column = @Column(
                    name = "long_inches")),
            @AttributeOverride(name = "medium", column = @Column(
                    name = "medium_inches")),
            @AttributeOverride(name = "short", column = @Column(
                    name = "short_inches")) })
    private RangedValue distancesInches;
    private Boolean     firearm;
    @Column(name = "long_penetration")
    private Integer     penetrationLong;
    @Column(name = "medium_penetration")
    private Integer     penetrationMedium;
    @Column(name = "short_penetration")
    private Integer     penetrationShort;
    @Column(name = "long_strength")
    private Integer     strengthLong;
    @Column(name = "medium_strength")
    private Integer     strengthMedium;
    @Column(name = "short_strength")
    private Integer     strengthShort;

    public AbstractJPARangedWeapon() {
        super();
    }

    public AbstractJPARangedWeapon(final AbstractJPARangedWeapon weapon) {
        super(weapon);

        checkNotNull(weapon, "Received a null pointer as weapon");

        distancesCM = weapon.distancesCM;
        distancesInches = weapon.distancesInches;

        penetrationShort = weapon.penetrationShort;
        penetrationMedium = weapon.penetrationMedium;
        penetrationLong = weapon.penetrationLong;

        strengthShort = weapon.strengthShort;
        strengthMedium = weapon.strengthMedium;
        strengthLong = weapon.strengthLong;

        firearm = weapon.firearm;
    }

    @Override
    public final RangedValue getDistancesImperialUnits() {
        return distancesInches;
    }

    @Override
    public final RangedValue getDistancesMetricSystem() {
        return distancesCM;
    }

    @Override
    public final Integer getLongPenetration() {
        return penetrationLong;
    }

    @Override
    public Integer getLongStrength() {
        return strengthLong;
    }

    @Override
    public final Integer getMediumPenetration() {
        return penetrationMedium;
    }

    @Override
    public Integer getMediumStrength() {
        return strengthMedium;
    }

    @Override
    public final Integer getShortPenetration() {
        return penetrationShort;
    }

    @Override
    public Integer getShortStrength() {
        return strengthShort;
    }

    @Override
    public final Boolean isFirearm() {
        return firearm;
    }

    public final void setDistancesImperialUnits(final RangedValue distances) {
        distancesInches = distances;
    }

    public final void setDistancesMetricSystem(final RangedValue distances) {
        distancesCM = distances;
    }

    public final void setFirearm(final Boolean firearm) {
        this.firearm = firearm;
    }

    public final void setLongPenetration(final Integer penetration) {
        penetrationLong = penetration;
    }

    public final void setLongStrength(final Integer strength) {
        strengthLong = strength;
    }

    public final void setMediumPenetration(final Integer penetration) {
        penetrationMedium = penetration;
    }

    public final void setMediumStrength(final Integer strength) {
        strengthMedium = strength;
    }

    public final void setShortPenetration(final Integer penetration) {
        penetrationShort = penetration;
    }

    public final void setShortStrength(final Integer strength) {
        strengthShort = strength;
    }

}
