package com.wandrell.tabletop.punkapocalyptic.report.reportbuilder;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

public final class OptionalListReportBuilder extends JasperReportBuilder {

    private static final long serialVersionUID = 1366316153929393655L;

    public OptionalListReportBuilder(final DRField<?> field, final String title) {
        super();

        detail(Components.horizontalList(Components.horizontalGap(10),
                Components.verticalList(Components.text(field))));
        title(Components.text(title));
        setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);
    }

}
