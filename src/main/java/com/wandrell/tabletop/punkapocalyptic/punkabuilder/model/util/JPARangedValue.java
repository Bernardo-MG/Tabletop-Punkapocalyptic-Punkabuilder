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
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;

@Embeddable
public class JPARangedValue implements RangedValue {

    @Column(name = "long")
    private Integer distanceLong;
    @Column(name = "medium")
    private Integer distanceMedium;
    @Column(name = "short")
    private Integer distanceShort;

    public JPARangedValue() {
        super();
    }

    @Override
    public final Integer getLongValue() {
        return distanceLong;
    }

    @Override
    public final Integer getMediumValue() {
        return distanceMedium;
    }

    @Override
    public final Integer getShortValue() {
        return distanceShort;
    }

    public final void setLongValue(final Integer longVal) {
        distanceLong = longVal;
    }

    public final void setMediumValue(final Integer mediumVal) {
        distanceMedium = mediumVal;
    }

    public final void setShortValue(final Integer shortVal) {
        distanceShort = shortVal;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("short", distanceShort)
                .add("medium", distanceMedium).add("long", distanceLong)
                .toString();
    }

}
