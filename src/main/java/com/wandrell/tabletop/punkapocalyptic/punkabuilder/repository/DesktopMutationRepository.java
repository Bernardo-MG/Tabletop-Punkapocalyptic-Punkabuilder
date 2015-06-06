package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import org.springframework.stereotype.Component;

import com.wandrell.jpa.JPARepository;
import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;

@Component("mutationRepo")
public final class DesktopMutationRepository extends JPARepository<Mutation>
        implements MutationRepository {

    public DesktopMutationRepository() {
        super(new DefaultQueryData("SELECT mutation FROM Mutation mutation"));
    }

}
