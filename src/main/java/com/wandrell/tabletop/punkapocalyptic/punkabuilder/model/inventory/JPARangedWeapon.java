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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ranged")
public final class JPARangedWeapon extends AbstractJPARangedWeapon {

    public JPARangedWeapon() {
        super();
    }

    public JPARangedWeapon(final JPARangedWeapon weapon) {
        super(weapon);
    }

    @Override
    public final Integer getLongStrength() {
        return super.getLongStrength();
    }

    @Override
    public final Integer getMediumStrength() {
        return super.getMediumStrength();
    }

    @Override
    public final Integer getShortStrength() {
        return super.getShortStrength();
    }

}
