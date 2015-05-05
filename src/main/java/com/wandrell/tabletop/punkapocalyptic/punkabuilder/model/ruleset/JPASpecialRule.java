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
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "SpecialRule")
@Table(name = "special_rules")
public final class JPASpecialRule implements SpecialRule, PersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = -1;
    @Column(name = "name")
    private String  nameToken;

    public JPASpecialRule() {
        super();
    }

    public JPASpecialRule(final JPASpecialRule rule) {
        super();

        checkNotNull(rule, "Received a null pointer as rule");

        nameToken = rule.nameToken;
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

        final JPASpecialRule other;

        other = (JPASpecialRule) obj;
        return Objects.equals(nameToken, other.nameToken);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getNameToken() {
        return nameToken;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(nameToken);
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setNameToken(final String name) {
        nameToken = name;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", nameToken)
                .toString();
    }

}
