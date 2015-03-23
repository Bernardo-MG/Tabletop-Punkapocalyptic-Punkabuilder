package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.command;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.Command;
import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.pattern.service.application.ApplicationInfoService;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.file.FileService;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.repository.WeaponRepositoryAware;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.ApplicationInfoServiceAware;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.LocalizationServiceAware;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.ModelServiceAware;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.RulesetServiceAware;

public final class DefaultContextCommandExecutor implements
        ContextCommandExecutor {

    private final CommandExecutor  executor;
    private ModelService           modelService;
    private ApplicationInfoService serviceAppInfo;
    private FileService            serviceFile;
    private LocalizationService    serviceLoc;
    private RulesetService         serviceRuleset;
    private Repository<Weapon>     weaponRepository;

    public DefaultContextCommandExecutor(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as base executor");

        this.executor = executor;
    }

    @Override
    public final void execute(final Command command) {
        checkNotNull(command, "Received a null pointer as command");

        setContext(command);

        getExecutor().execute(command);
    }

    @Override
    public final <V> V execute(final ReturnCommand<V> command) {
        checkNotNull(command, "Received a null pointer as command");

        setContext(command);

        return getExecutor().execute(command);
    }

    @Override
    public final void setApplicationInfoService(
            final ApplicationInfoService service) {
        checkNotNull(service,
                "Received a null pointer as application info service");

        serviceAppInfo = service;
    }

    @Override
    public final void setFileService(final FileService service) {
        checkNotNull(service, "Received a null pointer as file service");

        serviceFile = service;
    }

    @Override
    public final void setLocalizationService(final LocalizationService service) {
        checkNotNull(service, "Received a null pointer as localization service");

        serviceLoc = service;
    }

    @Override
    public final void setModelService(final ModelService service) {
        modelService = service;
    }

    @Override
    public final void setRulesetService(final RulesetService service) {
        checkNotNull(service, "Received a null pointer as ruleset service");

        serviceRuleset = service;
    }

    @Override
    public final void setWeaponRepository(final Repository<Weapon> repository) {
        weaponRepository = repository;
    }

    private final ApplicationInfoService getApplicationInfoService() {
        return serviceAppInfo;
    }

    private final CommandExecutor getExecutor() {
        return executor;
    }

    private final FileService getFileService() {
        return serviceFile;
    }

    private final LocalizationService getLocalizationService() {
        return serviceLoc;
    }

    private final ModelService getModelService() {
        return modelService;
    }

    private final RulesetService getRulesetService() {
        return serviceRuleset;
    }

    private final Repository<Weapon> getWeaponRepository() {
        return weaponRepository;
    }

    private final void setContext(final Object command) {
        if (command instanceof ApplicationInfoServiceAware) {
            ((ApplicationInfoServiceAware) command)
                    .setApplicationInfoService(getApplicationInfoService());
        }

        if (command instanceof LocalizationServiceAware) {
            ((LocalizationServiceAware) command)
                    .setLocalizationService(getLocalizationService());
        }

        if (command instanceof RulesetServiceAware) {
            ((RulesetServiceAware) command)
                    .setRulesetService(getRulesetService());
        }

        if (command instanceof FileServiceAware) {
            ((FileServiceAware) command).setFileService(getFileService());
        }

        if (command instanceof ModelServiceAware) {
            ((ModelServiceAware) command).setModelService(getModelService());
        }

        if (command instanceof WeaponRepositoryAware) {
            ((WeaponRepositoryAware) command)
                    .setWeaponRepository(getWeaponRepository());
        }
    }

}
