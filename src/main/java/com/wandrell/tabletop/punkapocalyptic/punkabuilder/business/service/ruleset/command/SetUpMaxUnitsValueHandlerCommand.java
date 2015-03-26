package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.ruleset.command;

import com.wandrell.pattern.command.Command;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.util.tag.GangAware;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class SetUpMaxUnitsValueHandlerCommand implements Command {

    private final Gang     gang;
    private final ValueBox value;

    public SetUpMaxUnitsValueHandlerCommand(final ValueBox value,
            final Gang gang) {
        super();

        this.value = value;
        this.gang = gang;
    }

    @Override
    public void execute() throws Exception {
        if ((value instanceof ValueBox) && (value instanceof GangAware)) {
            ((GangAware) value).setGang(gang);
        }
    }

}
