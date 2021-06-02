package com.linmsen.jpa;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public class BaseRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    private EntityManager em;


    public BaseRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }
}
