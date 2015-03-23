package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;

public final class FactionViewTransactionParser implements
        Parser<Element, Map<String, Object>> {

    public FactionViewTransactionParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final Element input) {
        final Map<String, Object> transaction;
        final String faction;
        final String image;

        faction = input.getChildText("faction");
        image = input.getChildText("image");

        transaction = new LinkedHashMap<>();
        transaction.put("faction", faction);
        transaction.put("image", image);

        return transaction;
    }

}
