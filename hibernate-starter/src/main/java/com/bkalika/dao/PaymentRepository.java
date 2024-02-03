package com.bkalika.dao;

import com.bkalika.entity.Payment;
import jakarta.persistence.EntityManager;
//import org.hibernate.SessionFactory;

public class PaymentRepository extends BaseRepository<Long, Payment> {

    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }
}
