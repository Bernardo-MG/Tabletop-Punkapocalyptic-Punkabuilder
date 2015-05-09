package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.ArmorOptionRepository;

@Component("armorOptionRepo")
public final class DesktopArmorOptionRepository extends
        JPARepository<ArmorOption> implements ArmorOptionRepository {

    public DesktopArmorOptionRepository() {
        super(new DefaultQueryData("SELECT option FROM ArmorOption option"));
    }

}
