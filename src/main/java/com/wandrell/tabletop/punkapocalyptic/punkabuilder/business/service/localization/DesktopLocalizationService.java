package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.localization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ResourceBundle;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.util.command.localization.BundleStringCommand;

public final class DesktopLocalizationService implements LocalizationService {

    private final ResourceBundle  bundleMessage;
    private final ResourceBundle  bundleReport;
    private final ResourceBundle  bundleView;
    private final CommandExecutor executor;

    public DesktopLocalizationService(final CommandExecutor executor,
            final ResourceBundle messageBundle,
            final ResourceBundle viewBundle, final ResourceBundle reportBundle) {
        super();

        checkNotNull(executor, "Received a null pointer as executor");
        checkNotNull(messageBundle, "Received a null pointer as message bundle");
        checkNotNull(viewBundle, "Received a null pointer as view bundle");
        checkNotNull(reportBundle,
                "Received a null pointer as the report bundle");

        this.bundleMessage = messageBundle;
        this.bundleReport = reportBundle;
        this.bundleView = viewBundle;

        this.executor = executor;
    }

    @Override
    public final String getMessageString(final String message) {
        return getExecutor().execute(
                new BundleStringCommand(message, getMessageBundle()));
    }

    @Override
    public String getReportString(String key) {
        return getExecutor().execute(
                new BundleStringCommand(key, getReportBundle()));
    }

    @Override
    public final String getViewString(final String key) {
        return getExecutor().execute(
                new BundleStringCommand(key, getViewBundle()));
    }

    private final CommandExecutor getExecutor() {
        return executor;
    }

    private final ResourceBundle getMessageBundle() {
        return bundleMessage;
    }

    private final ResourceBundle getReportBundle() {
        return bundleReport;
    }

    private final ResourceBundle getViewBundle() {
        return bundleView;
    }

}
