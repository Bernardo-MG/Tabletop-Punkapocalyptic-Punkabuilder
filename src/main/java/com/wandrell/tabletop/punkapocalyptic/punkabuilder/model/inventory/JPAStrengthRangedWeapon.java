/**
 * Copyright 2015 the original author or authors
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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitDependantWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

@Entity
@DiscriminatorValue("ranged_strength")
public final class JPAStrengthRangedWeapon extends AbstractJPARangedWeapon
        implements UnitDependantWeapon {

    @Transient
    private Unit unit;

    public JPAStrengthRangedWeapon() {
        super();
    }

    public JPAStrengthRangedWeapon(final JPAStrengthRangedWeapon weapon) {
        super();

        checkNotNull(weapon, "Received a null pointer as weapon");

        unit = weapon.unit;
    }

    @Override
    public final Integer getLongStrength() {
        return getUnit().getAttributes().getStrength()
                + super.getLongStrength();
    }

    @Override
    public final Integer getMediumStrength() {
        return getUnit().getAttributes().getStrength()
                + super.getMediumStrength();
    }

    @Override
    public final Integer getShortStrength() {
        return getUnit().getAttributes().getStrength()
                + super.getShortStrength();
    }

    @Override
    public final void setUnit(final Unit unit) {
        this.unit = unit;
    }

    private final Unit getUnit() {
        return unit;
    }

}
