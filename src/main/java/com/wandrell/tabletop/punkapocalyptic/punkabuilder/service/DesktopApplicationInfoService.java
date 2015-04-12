package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wandrell.util.service.application.ApplicationInfoService;
import com.wandrell.util.service.application.AuthorInfo;
import com.wandrell.util.service.application.HelperInfo;
import com.wandrell.util.service.application.MapApplicationInfoService;

@Service("applicationInfoService")
public final class DesktopApplicationInfoService implements
        ApplicationInfoService {

    private final ApplicationInfoService baseService;

    @SuppressWarnings("unchecked")
    @Autowired
    public DesktopApplicationInfoService(
            @Qualifier("applicationInfoMap") final Map<String, Object> applicationInfoMap) {
        super();

        final Map<String, Object> map;

        // TODO: Why is this happening?
        map = (Map<String, Object>) applicationInfoMap
                .get("applicationInfoMap");

        baseService = new MapApplicationInfoService(map);
    }

    @Override
    public final String getApplicationName() {
        return getBaseService().getApplicationName();
    }

    @Override
    public final Collection<AuthorInfo> getAuthors() {
        return getBaseService().getAuthors();
    }

    @Override
    public final Collection<HelperInfo> getHelpers() {
        return getBaseService().getHelpers();
    }

    @Override
    public final URI getProjectURI() {
        return getBaseService().getProjectURI();
    }

    @Override
    public final String getVersion() {
        return getBaseService().getVersion();
    }

    private final ApplicationInfoService getBaseService() {
        return baseService;
    }

}
