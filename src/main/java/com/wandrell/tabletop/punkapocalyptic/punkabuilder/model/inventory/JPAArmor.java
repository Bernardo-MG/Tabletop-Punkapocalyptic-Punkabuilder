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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

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
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.ruleset.JPASpecialRule;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "Armor")
@Table(name = "armors")
public final class JPAArmor implements Armor, PersistenceEntity {

    @Column(name = "name")
    private String                           armorName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "armor_rules", joinColumns = { @JoinColumn(
            name = "armor_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "rule_id",
                    referencedColumnName = "id") })
    private final Collection<JPASpecialRule> armorRules = new LinkedHashSet<JPASpecialRule>();
    @Column(name = "armor")
    private Integer                          armorValue;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                          id         = -1;

    public JPAArmor() {
        super();
    }

    public JPAArmor(final JPAArmor armor) {
        super();

        checkNotNull(armor, "Received a null pointer as armor");

        this.armorValue = armor.armorValue;

        armorName = armor.armorName;

        armorRules.addAll(armor.armorRules);
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

        final JPAArmor other;

        other = (JPAArmor) obj;
        return Objects.equals(armorName, other.armorName);
    }

    @Override
    public final Integer getArmor() {
        return armorValue;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return armorName;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(armorName);
    }

    public final void setArmor(final Integer armor) {
        armorValue = armor;
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setName(final String name) {
        armorName = name;
    }

    public final void setSpecialRules(final Collection<JPASpecialRule> rules) {
        getSpecialRulesModifiable().clear();
        getSpecialRulesModifiable().addAll(rules);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", armorName)
                .toString();
    }

    private final Collection<JPASpecialRule> getSpecialRulesModifiable() {
        return armorRules;
    }

}
