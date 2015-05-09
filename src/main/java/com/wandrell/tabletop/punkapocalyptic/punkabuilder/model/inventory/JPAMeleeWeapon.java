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

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;

@Entity
@DiscriminatorValue("melee")
public final class JPAMeleeWeapon extends AbstractJPAWeapon implements
        MeleeWeapon {

    @Column(name = "combat")
    private Integer weaponCombat = 0;
    @Column(name = "penetration")
    private Integer weaponPenet  = 0;
    @Column(name = "strength")
    private Integer weaponStr    = 0;

    public JPAMeleeWeapon() {
        super();
    }

    public JPAMeleeWeapon(final JPAMeleeWeapon weapon) {
        super(weapon);

        checkNotNull(weapon, "Received a null pointer as weapon");

        weaponCombat = weapon.weaponCombat;
        weaponPenet = weapon.weaponPenet;
        weaponStr = weapon.weaponStr;
    }

    @Override
    public final Integer getCombat() {
        return weaponCombat;
    }

    @Override
    public final Integer getPenetration() {
        return weaponPenet;
    }

    @Override
    public final Integer getStrength() {
        return weaponStr;
    }

    public final void setCombat(final Integer combat) {
        weaponCombat = combat;
    }

    public final void setPenetration(final Integer penetration) {
        weaponPenet = penetration;
    }

    public final void setStrength(final Integer strength) {
        weaponStr = strength;
    }

}
