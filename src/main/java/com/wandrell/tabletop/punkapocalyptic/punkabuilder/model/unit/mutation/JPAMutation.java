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
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.unit.mutation;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.AttributeBonusMutation;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "Mutation")
@Table(name = "mutations")
public final class JPAMutation implements Mutation, AttributeBonusMutation,
        PersistenceEntity {

    @Column(name = "actions")
    private Integer actionsBonus;
    @Column(name = "agility")
    private Integer agilityBonus;
    @Column(name = "combat")
    private Integer combatBonus;
    @Column(name = "cost")
    private Integer cost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = -1;
    @Column(name = "name")
    private String  name;
    @Column(name = "precision")
    private Integer precisionBonus;
    @Column(name = "strength")
    private Integer strengthBonus;
    @Column(name = "tech")
    private Integer techBonus;
    @Column(name = "toughness")
    private Integer toughnessBonus;

    public JPAMutation() {
        super();
    }

    public JPAMutation(final JPAMutation mutation) {
        super();

        checkNotNull(mutation, "Received a null pointer as mutation");

        this.name = mutation.name;
        this.cost = mutation.cost;

        actionsBonus = mutation.actionsBonus;
        agilityBonus = mutation.agilityBonus;
        combatBonus = mutation.combatBonus;
        precisionBonus = mutation.precisionBonus;
        strengthBonus = mutation.strengthBonus;
        techBonus = mutation.techBonus;
        toughnessBonus = mutation.toughnessBonus;
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

        JPAMutation other;

        other = (JPAMutation) obj;
        return Objects.equal(name, other.name);
    }

    @Override
    public final Integer getActionsBonus() {
        return actionsBonus;
    }

    @Override
    public final Integer getAgilityBonus() {
        return agilityBonus;
    }

    @Override
    public final Integer getCombatBonus() {
        return combatBonus;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Integer getPrecisionBonus() {
        return precisionBonus;
    }

    @Override
    public final Integer getStrengthBonus() {
        return strengthBonus;
    }

    @Override
    public final Integer getTechBonus() {
        return techBonus;
    }

    public final void getTechBonus(final Integer tech) {
        techBonus = tech;
    }

    @Override
    public final Integer getToughnessBonus() {
        return toughnessBonus;
    }

    public final void getToughnessBonus(final Integer toughness) {
        toughnessBonus = toughness;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    public final void setActionsBonus(final Integer actions) {
        actionsBonus = actions;
    }

    public final void setAgilityBonus(final Integer agility) {
        agilityBonus = agility;
    }

    public final void setCombatBonus(final Integer combat) {
        combatBonus = combat;
    }

    public final void setCost(final Integer cost) {
        this.cost = cost;
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setPrecisionBonus(final Integer precision) {
        precisionBonus = precision;
    }

    public final void setStrengthBonus(final Integer strength) {
        strengthBonus = strength;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

}
