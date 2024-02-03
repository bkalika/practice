package com.bkalika.util;

import com.bkalika.converter.BirthdayConverter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter());
        return configuration.buildSessionFactory();
    }
}
