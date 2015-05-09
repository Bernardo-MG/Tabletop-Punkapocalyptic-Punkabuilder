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
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.unit.JPAUnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.unit.mutation.JPAMutation;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "UnitMutationAvailability")
@Table(name = "unit_mutations")
public final class JPAUnitMutationAvailability implements
        UnitMutationAvailability, PersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                       id              = -1;
    @Column(name = "max")
    private Integer                       maxMutations;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_mutations_mutations", joinColumns = { @JoinColumn(
            name = "unit_mutation_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "mutation_id",
                    referencedColumnName = "id") })
    private final Collection<JPAMutation> mutationOptions = new LinkedHashSet<JPAMutation>();
    @OneToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private JPAUnitTemplate               unit;

    public JPAUnitMutationAvailability() {
        super();
    }

    public final void addMutationOption(final JPAMutation mutation) {
        getMutationOptionsModifiable().add(mutation);
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

        JPAUnitMutationAvailability other;

        other = (JPAUnitMutationAvailability) obj;
        return Objects.equal(unit, other.unit);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Integer getMaxMutations() {
        return maxMutations;
    }

    @Override
    public final Collection<Mutation> getMutationOptions() {
        return Collections
                .unmodifiableCollection(getMutationOptionsModifiable());
    }

    @Override
    public final UnitTemplate getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(unit);
    }

    public final void removeMutationOption(final JPAMutation mutation) {
        getMutationOptionsModifiable().remove(mutation);
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setMaxMutations(final Integer max) {
        maxMutations = max;
    }

    public final void
            setMutationOptions(final Collection<JPAMutation> mutations) {
        getMutationOptionsModifiable().clear();
        getMutationOptionsModifiable().addAll(mutations);
    }

    public final void setUnit(final JPAUnitTemplate unit) {
        this.unit = unit;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("unit", unit.getNameToken()).add("max", maxMutations)
                .add("mutations", mutationOptions).toString();
    }

    private final Collection<JPAMutation> getMutationOptionsModifiable() {
        return mutationOptions;
    }

}
