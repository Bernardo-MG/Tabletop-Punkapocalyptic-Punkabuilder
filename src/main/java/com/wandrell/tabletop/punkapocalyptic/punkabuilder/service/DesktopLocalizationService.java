package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

@Service("localizationService")
public final class DesktopLocalizationService implements LocalizationService {

    private final ResourceBundle bundleMessage;
    private final ResourceBundle bundleReport;
    private final ResourceBundle bundleView;

    @Autowired
    public DesktopLocalizationService(final ResourceBundle messageBundle,
            final ResourceBundle viewBundle, final ResourceBundle reportBundle) {
        super();

        checkNotNull(messageBundle, "Received a null pointer as message bundle");
        checkNotNull(viewBundle, "Received a null pointer as view bundle");
        checkNotNull(reportBundle,
                "Received a null pointer as the report bundle");

        this.bundleMessage = messageBundle;
        this.bundleReport = reportBundle;
        this.bundleView = viewBundle;
    }

    @Override
    public final String getMessageString(final String key) {
        return getValue(key, getMessageBundle());
    }

    @Override
    public String getReportString(String key) {
        return getValue(key, getReportBundle());
    }

    @Override
    public final String getViewString(final String key) {
        return getValue(key, getViewBundle());
    }

    private final ResourceBundle getMessageBundle() {
        return bundleMessage;
    }

    private final ResourceBundle getReportBundle() {
        return bundleReport;
    }

    private final String
            getValue(final String key, final ResourceBundle bundle) {
        final String value;

        if (bundle.containsKey(key)) {
            value = bundle.getString(key);
        } else {
            value = key;
        }

        return value;
    }

    private final ResourceBundle getViewBundle() {
        return bundleView;
    }

}
