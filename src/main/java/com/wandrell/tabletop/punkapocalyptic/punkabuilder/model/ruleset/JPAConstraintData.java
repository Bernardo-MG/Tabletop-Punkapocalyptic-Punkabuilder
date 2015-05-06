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
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.ruleset;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.procedure.ConstraintData;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

/**
 * Default implementation of {@link ConstraintData};
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "ConstraintData")
@Table(name = "constraints_data")
public final class JPAConstraintData implements ConstraintData,
        PersistenceEntity {

    /**
     * Context for the constraint.
     */
    @ElementCollection
    @Column(name = "context")
    private final Collection<String> context = new LinkedList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                  id      = -1;
    /**
     * Name token identifying the constraint.
     */
    @Column(name = "name")
    private String                   name;

    public JPAConstraintData() {
        super();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JPAConstraintData other = (JPAConstraintData) obj;
        return Objects.equal(name, other.name)
                && Objects.equal(context, other.context);
    }

    @Override
    public final Collection<String> getContext() {
        return Collections.unmodifiableCollection(getContextModifiable());
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getNameToken() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name, context);
    }

    public final void setContext(final Collection<String> context) {
        getContextModifiable().clear();
        getContextModifiable().addAll(context);
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setNameToken(final String name) {
        this.name = name;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("context", context).toString();
    }

    /**
     * Returns a modifiable instance of the context.
     * 
     * @return context
     */
    private final Collection<String> getContextModifiable() {
        return context;
    }

}
