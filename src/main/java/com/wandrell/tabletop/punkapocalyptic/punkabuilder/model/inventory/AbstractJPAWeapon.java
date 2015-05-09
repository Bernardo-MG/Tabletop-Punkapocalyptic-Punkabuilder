package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.inventory;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.ruleset.JPASpecialRule;

@Entity(name = "Weapon")
@Inheritance
@DiscriminatorColumn(name = "weapon_type")
@DiscriminatorOptions(force = true)
@Table(name = "weapons")
public abstract class AbstractJPAWeapon implements Weapon {

    private Integer                          cost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                          id    = -1;
    private String                           name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "weapon_rules", joinColumns = { @JoinColumn(
            name = "weapon_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "rule_id",
                    referencedColumnName = "id") })
    private final Collection<JPASpecialRule> rules = new LinkedList<>();
    @Column(name = "two_handed")
    private Boolean                          twoHanded;

    public AbstractJPAWeapon() {
        super();
    }

    public AbstractJPAWeapon(final AbstractJPAWeapon weapon) {
        super();

        this.name = weapon.name;
        this.cost = weapon.cost;
        this.twoHanded = weapon.twoHanded;
        this.rules.addAll(weapon.rules);
    }

    public final void addSpecialRule(final JPASpecialRule rule) {
        getSpecialRulesEditable().add(rule);
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesEditable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final Boolean isTwoHanded() {
        return twoHanded;
    }

    public final void removeSpecialRule(final JPASpecialRule rule) {
        getSpecialRulesEditable().remove(rule);
    }

    public final void setCost(final Integer cost) {
        this.cost = cost;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setSpecialRules(final Collection<JPASpecialRule> rules) {
        getSpecialRulesEditable().clear();
        getSpecialRulesEditable().addAll(rules);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("cost", cost).add("rules", rules).toString();
    }

    private final Collection<JPASpecialRule> getSpecialRulesEditable() {
        return rules;
    }

}
