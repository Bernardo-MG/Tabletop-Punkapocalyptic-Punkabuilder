package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.unit.stats;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.swing.event.EventListenerList;

import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.event.ValueChangeListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.AttributesListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.EditableAttributesHolder;
import com.wandrell.tabletop.valuebox.DefaultValueBox;
import com.wandrell.tabletop.valuebox.ValueBox;

@Embeddable
public final class JPAEditableAttributesHolder implements
        EditableAttributesHolder {

    private final ValueBox          actions;
    private final ValueBox          agility;
    private final ValueBox          combat;
    @Transient
    private final EventListenerList listeners = new EventListenerList();
    private final ValueBox          precision;
    private final ValueBox          strength;
    private final ValueBox          tech;
    private final ValueBox          toughness;

    public JPAEditableAttributesHolder() {
        super();

        actions = new DefaultValueBox();
        agility = new DefaultValueBox();
        combat = new DefaultValueBox();
        precision = new DefaultValueBox();
        strength = new DefaultValueBox();
        tech = new DefaultValueBox();
        toughness = new DefaultValueBox();

        setAttributesListeners();
    }

    public JPAEditableAttributesHolder(
            final JPAEditableAttributesHolder attributes) {
        super();

        actions = attributes.actions.createNewInstance();
        agility = attributes.agility.createNewInstance();
        combat = attributes.combat.createNewInstance();
        precision = attributes.precision.createNewInstance();
        strength = attributes.strength.createNewInstance();
        tech = attributes.tech.createNewInstance();
        toughness = attributes.toughness.createNewInstance();

        setAttributesListeners();
    }

    @Override
    public final void addAttributesListener(final AttributesListener listener) {
        getListeners().add(AttributesListener.class, listener);
    }

    @Override
    public final JPAEditableAttributesHolder createNewInstance() {
        return new JPAEditableAttributesHolder(this);
    }

    @Column(name = "actions")
    @Access(value = AccessType.PROPERTY)
    @Override
    public final Integer getActions() {
        return getActionsValueBox().getValue();
    }

    @Column(name = "agility")
    @Access(value = AccessType.PROPERTY)
    @Override
    public final Integer getAgility() {
        return getAgilityValueBox().getValue();
    }

    @Column(name = "combat")
    @Access(value = AccessType.PROPERTY)
    @Override
    public final Integer getCombat() {
        return getCombatValueBox().getValue();
    }

    @Column(name = "precision")
    @Access(value = AccessType.PROPERTY)
    @Override
    public final Integer getPrecision() {
        return getPrecisionValueBox().getValue();
    }

    @Column(name = "strength")
    @Access(value = AccessType.PROPERTY)
    @Override
    public final Integer getStrength() {
        return getStrengthValueBox().getValue();
    }

    @Column(name = "tech")
    @Access(value = AccessType.PROPERTY)
    @Override
    public final Integer getTech() {
        return getTechValueBox().getValue();
    }

    @Column(name = "toughness")
    @Access(value = AccessType.PROPERTY)
    @Override
    public final Integer getToughness() {
        return getToughnessValueBox().getValue();
    }

    @Override
    public final void
            removeAttributesListener(final AttributesListener listener) {
        getListeners().remove(AttributesListener.class, listener);
    }

    @Override
    public final void setActions(final Integer actions) {
        getActionsValueBox().setValue(actions);
    }

    @Override
    public final void setAgility(Integer agility) {
        getAgilityValueBox().setValue(agility);
    }

    @Override
    public final void setCombat(Integer combat) {
        getCombatValueBox().setValue(combat);
    }

    @Override
    public final void setPrecision(Integer precision) {
        getPrecisionValueBox().setValue(precision);
    }

    @Override
    public final void setStrength(final Integer strength) {
        getStrengthValueBox().setValue(strength);
    }

    @Override
    public final void setTech(final Integer tech) {
        getTechValueBox().setValue(tech);
    }

    @Override
    public final void setToughness(final Integer toughness) {
        getToughnessValueBox().setValue(toughness);
    }

    private final void fireActionsChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.actionsChanged(evt);
        }
    }

    private final void fireAgilityChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.agilityChanged(evt);
        }
    }

    private final void fireCombatChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.combatChanged(evt);
        }
    }

    private final void firePrecisionChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.precisionChanged(evt);
        }
    }

    private final void fireStrengthChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.strengthChanged(evt);
        }
    }

    private final void fireTechChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.techChanged(evt);
        }
    }

    private final void fireToughnessChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.toughnessChanged(evt);
        }
    }

    private final ValueBox getActionsValueBox() {
        return actions;
    }

    private final ValueBox getAgilityValueBox() {
        return agility;
    }

    private final ValueBox getCombatValueBox() {
        return combat;
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final ValueBox getPrecisionValueBox() {
        return precision;
    }

    private final ValueBox getStrengthValueBox() {
        return strength;
    }

    private final ValueBox getTechValueBox() {
        return tech;
    }

    private final ValueBox getToughnessValueBox() {
        return toughness;
    }

    private final void setAttributesListeners() {
        getActionsValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireActionsChangedEvent(event);
            }

        });
        getAgilityValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireAgilityChangedEvent(event);
            }

        });
        getCombatValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireCombatChangedEvent(event);
            }

        });
        getPrecisionValueBox().addValueChangeListener(
                new ValueChangeListener() {

                    @Override
                    public final void
                            valueChanged(final ValueChangeEvent event) {
                        firePrecisionChangedEvent(event);
                    }

                });
        getStrengthValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireStrengthChangedEvent(event);
            }

        });
        getTechValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireTechChangedEvent(event);
            }

        });
        getToughnessValueBox().addValueChangeListener(
                new ValueChangeListener() {

                    @Override
                    public final void
                            valueChanged(final ValueChangeEvent event) {
                        fireToughnessChangedEvent(event);
                    }

                });
    }

}
