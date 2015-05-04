package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa;

import java.util.Collection;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;

public class JPARepository<V> implements FilteredRepository<V, QueryData> {

    @PersistenceContext
    private EntityManager   em;
    private final QueryData getAllQuery;

    public JPARepository(final QueryData allQuery) {
        super();

        getAllQuery = allQuery;
    }

    @Override
    public final void add(final V entity) {
        final PersistenceEntity persist;

        if (entity instanceof PersistenceEntity) {
            persist = (PersistenceEntity) entity;

            if ((persist.getId() == null) || (persist.getId() < 0)) {
                getEntityManager().persist(entity);
            } else {
                getEntityManager().merge(entity);
            }
        }
    }

    @Override
    public final Collection<V> getAll() {
        return getCollection(getAllValuesQuery());
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Collection<V> getCollection(final QueryData filter) {
        final Query query;

        query = getEntityManager().createQuery(filter.getQuery());

        for (final Entry<String, Object> entry : filter.getParameters()
                .entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final V getEntity(final QueryData filter) {
        final Query query;

        query = getEntityManager().createQuery(filter.getQuery());

        for (final Entry<String, Object> entry : filter.getParameters()
                .entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return (V) query.getSingleResult();
    }

    @Override
    public final void remove(final V entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public final void update(final V entity) {
        add(entity);
    }

    private final QueryData getAllValuesQuery() {
        return getAllQuery;
    }

    private final EntityManager getEntityManager() {
        return em;
    }

}
