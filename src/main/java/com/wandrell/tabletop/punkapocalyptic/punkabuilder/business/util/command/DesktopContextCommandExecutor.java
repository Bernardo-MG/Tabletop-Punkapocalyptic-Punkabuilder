package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.command;

import com.wandrell.pattern.command.Command;
import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.pattern.command.UndoCommand;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.file.FileService;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.view.ViewService;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.tag.service.ViewServiceAware;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.util.service.application.ApplicationInfoService;

public class DesktopContextCommandExecutor implements ContextCommandExecutor {

    private final DefaultContextCommandExecutor executor;
    private ViewService                         viewService;

    public DesktopContextCommandExecutor(final CommandExecutor executor) {
        super();

        this.executor = new DefaultContextCommandExecutor(executor);
    }

    @Override
    public final void execute(final Command command) {
        setContext(command);

        getBaseExecutor().execute(command);
    }

    @Override
    public final <V> V execute(final ResultCommand<V> command) {
        setContext(command);

        return getBaseExecutor().execute(command);
    }

    @Override
    public final void setApplicationInfoService(
            final ApplicationInfoService service) {
        getBaseExecutor().setApplicationInfoService(service);
    }

    @Override
    public final void setFileService(final FileService service) {
        getBaseExecutor().setFileService(service);
    }

    @Override
    public final void setLocalizationService(final LocalizationService service) {
        getBaseExecutor().setLocalizationService(service);
    }

    @Override
    public final void setModelService(final ModelService service) {
        getBaseExecutor().setModelService(service);
    }

    @Override
    public final void setRulesetService(final RulesetService service) {
        getBaseExecutor().setRulesetService(service);
    }

    public final void setViewService(final ViewService service) {
        viewService = service;
    }

    @Override
    public final void setWeaponRepository(final Repository<Weapon> repository) {
        getBaseExecutor().setWeaponRepository(repository);
    }

    @Override
    public final void undo(final UndoCommand command) {
        getBaseExecutor().undo(command);
    }

    private final ContextCommandExecutor getBaseExecutor() {
        return executor;
    }

    private final ViewService getViewService() {
        return viewService;
    }

    private final void setContext(final Object command) {
        if (command instanceof ViewServiceAware) {
            ((ViewServiceAware) command).setViewService(getViewService());
        }
    }

}
