package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;

@Component("mutationRepo")
public final class DesktopMutationRepository extends JPARepository<Mutation>
        implements MutationRepository {

    public DesktopMutationRepository() {
        super(new DefaultQueryData("SELECT mutation FROM Mutation mutation"));
    }

    @Override
    public final Collection<Mutation> getByNamesList(
            final Collection<String> names) {
        final Map<String, Object> params;
        final StringBuilder namesResult;

        namesResult = new StringBuilder();
        for (final String name : names) {
            if (namesResult.length() > 0) {
                namesResult.append(", ");
            }
            namesResult.append(name);
        }

        params = new LinkedHashMap<>();
        params.put("mutations", namesResult.toString());

        return getCollection(new DefaultQueryData(
                "SELECT mutation FROM Mutation mutation WHERE mutation.name = :mutations",
                params));
    }

}
