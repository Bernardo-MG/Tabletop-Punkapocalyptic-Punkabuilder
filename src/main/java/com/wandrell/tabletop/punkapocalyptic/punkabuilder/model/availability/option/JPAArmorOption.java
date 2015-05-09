package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.availability.option;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.inventory.JPAArmor;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "ArmorOption")
@Table(name = "armor_options")
public final class JPAArmorOption implements ArmorOption, PersistenceEntity {

    @OneToOne
    @JoinColumn(name = "armor", referencedColumnName = "id")
    private JPAArmor armor;
    private Integer  cost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id = -1;

    public JPAArmorOption() {
        super();
    }

    @Override
    public final Armor getArmor() {
        return armor;
    }

    public final void getArmor(final JPAArmor armor) {
        this.armor = armor;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    public final void setCost(final Integer cost) {
        this.cost = cost;
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

}
