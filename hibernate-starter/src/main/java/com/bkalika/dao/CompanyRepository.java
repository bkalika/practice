package com.bkalika.dao;

import com.bkalika.entity.Company;
import com.bkalika.entity.Payment;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CompanyRepository extends BaseRepository<Long, Company> {

    public CompanyRepository(EntityManager entityManager) {
        super(Company.class, entityManager);
    }

    public List<Payment>  findAllByReceiverId(Long receiverId) {
        return getEntityManager().createQuery("SELECT p FROM Payment p WHERE p.receiver.id = :receiverId", Payment.class)
                .setParameter("receiverId", receiverId)
                .getResultList();
    }
}
