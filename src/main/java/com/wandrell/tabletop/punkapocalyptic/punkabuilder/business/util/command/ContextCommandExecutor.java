package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.command;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.pattern.service.application.ApplicationInfoService;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.file.FileService;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

public interface ContextCommandExecutor extends CommandExecutor {

    public void setApplicationInfoService(final ApplicationInfoService service);

    public void setFileService(final FileService service);

    public void setLocalizationService(final LocalizationService service);

    public void setModelService(final ModelService service);

    public void setRulesetService(final RulesetService service);

    public void setWeaponRepository(final Repository<Weapon> repository);

}
