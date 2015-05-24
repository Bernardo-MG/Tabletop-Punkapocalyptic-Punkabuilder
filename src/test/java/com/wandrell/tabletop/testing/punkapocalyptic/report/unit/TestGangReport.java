package com.wandrell.tabletop.testing.punkapocalyptic.report.unit;

import java.util.Collection;
import java.util.LinkedList;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.report.reportbuilder.GangReportBuilder;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

public final class TestGangReport {

    public TestGangReport() {
        super();
    }

    private final JasperReportBuilder getGangReportBuilder() {
        final LocalizationService serviceLocalization;
        final RulesetService serviceRuleset;

        serviceLocalization = Mockito.mock(LocalizationService.class);
        serviceRuleset = Mockito.mock(RulesetService.class);

        Mockito.when(serviceLocalization.getReportString(Matchers.anyString()))
                .thenReturn("text");

        Mockito.when(serviceRuleset.getMaxAllowedUnits(Matchers.anyInt()))
                .thenReturn(5);

        return new GangReportBuilder("AppName", "1.2.3",
                "http://www.somewhere.com", "images/punkapocalyptic.jpg",
                serviceLocalization, serviceRuleset);
    }

    private final Gang getGang() {
        final Gang gang;

        gang = Mockito.mock(Gang.class);
        Mockito.when(gang.getBullets()).thenReturn(5);
        Mockito.when(gang.getValoration()).thenReturn(50);

        return gang;
    }

    @Test
    public final void testReport_NoUnits() {
        final JasperReportBuilder report;
        final Gang gang;
        final Collection<Unit> units;
        final Collection<Gang> gangs;

        gang = getGang();

        units = new LinkedList<>();

        Mockito.when(gang.getUnits()).thenReturn(units);

        report = getGangReportBuilder();

        gangs = new LinkedList<>();
        gangs.add(gang);
        report.setDataSource(gangs);
    }

}
