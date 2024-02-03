package com.bkalika.dao;

import com.bkalika.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E extends BaseEntity<K>>
        implements Repository<K, E> {

    private final Class<E> clazz;

    @Getter
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
//        var session = sessionFactory.getCurrentSession();
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
//        var session = sessionFactory.getCurrentSession();
        entityManager.remove(entityManager.find(clazz, id));
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
//        var session = sessionFactory.getCurrentSession();
        entityManager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
//        var session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
//        var session = sessionFactory.getCurrentSession();
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria).getResultList();
    }
}
