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
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.availability;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.procedure.ConstraintData;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.faction.JPAFaction;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.ruleset.JPAConstraintData;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.unit.JPAUnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "FactionUnitAvailability")
@Table(name = "faction_units")
public final class JPAFactionUnitAvailability implements
        FactionUnitAvailability, PersistenceEntity {

    @OneToOne
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private JPAUnitTemplate                     availUnit;
    @OneToOne
    @JoinColumn(name = "faction_id", referencedColumnName = "id")
    private JPAFaction                          faction;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                             id          = -1;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "faction_unit_constraints", joinColumns = { @JoinColumn(
            name = "faction_unit_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "constraint_id",
                    referencedColumnName = "id") })
    private final Collection<JPAConstraintData> unitConstrn = new LinkedList<>();

    public JPAFactionUnitAvailability() {
        super();
        // TODO: Use a composite key
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        JPAFactionUnitAvailability other;

        other = (JPAFactionUnitAvailability) obj;
        return Objects.equals(availUnit, other.availUnit)
                && Objects.equals(unitConstrn, other.unitConstrn);
    }

    @Override
    public final Collection<ConstraintData> getConstraints() {
        return Collections.unmodifiableCollection(getConstraintsModifiable());
    }

    @Override
    public final Faction getFaction() {
        return faction;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final UnitTemplate getUnit() {
        return availUnit;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(unitConstrn, availUnit);
    }

    public final void setConstraints(
            final Collection<JPAConstraintData> constraints) {
        getConstraintsModifiable().clear();
        getConstraintsModifiable().addAll(constraints);
    }

    public final void setFaction(final JPAFaction faction) {
        this.faction = faction;
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setUnit(final JPAUnitTemplate template) {
        availUnit = template;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("unit", availUnit)
                .add("constraints", unitConstrn).toString();
    }

    private final Collection<JPAConstraintData> getConstraintsModifiable() {
        return unitConstrn;
    }

}
