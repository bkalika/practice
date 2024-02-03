package com.bkalika;

import com.bkalika.entity.*;
import com.bkalika.util.HibernateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

//@UtilityClass
public class MainTest {

//    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");
//
//    static {
//        postgres.start();
//    }

//    public static SessionFactory buildSessionFactory() {
//        Configuration configuration = HibernateUtil.buildConfiguration();
//        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
//        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
//        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
//        configuration.configure();
//
//        return configuration.buildSessionFactory();
//    }

    @Test
    public void checkH2() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                        .name("Google")
                                .build();

        session.persist(company);

        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var chat = session.get(Chat.class, 1L);
        var user = session.get(User.class, 2L);

//        var userChat = UserChat.builder()
//                .createdAt(Instant.now())
//                .createdBy("Bohdan")
//                .build();
        var userChat = new UserChat();
        userChat.setCreatedAt(Instant.now());
        userChat.setCreatedBy("Bohdan");

        userChat.setChat(chat);
        userChat.setUser(user);

        session.persist(userChat);

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

//        var user = User.builder()
//                .username("pilip_test02@mail.com")
//                .build();

        var profile = Profile.builder()
                .language("EN")
                .street("Pennsylvania")
                .build();

//        session.persist(user);
//        profile.setUser(user);
        session.persist(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkOrphalRemoval() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1L);
        company.getUsers().removeIf(user -> user.getId().equals(1L));

        session.getTransaction().commit();
    }

    @Test
    public void addUserAndCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder()
                .name("Amazon")
                .build();

//        var user = User.builder()
//                .username("pilip_test01@mail.com")
//                .company(company)
//                .build();

//        user.setCompany(company);
//        company.getUsers().add(user);

//        company.addUser(user);

        session.persist(company);

        session.getTransaction().commit();
    }

//    @Test
//    public void testHibernateApi() throws SQLException, IllegalAccessException {
//        var user = User.builder()
//                .username("ivan1@mail.com")
//                .firstname("Pilip")
//                .lastname("Orlik")
//                .birthDate(new Birthday(LocalDate.of(1500, 1, 3)))
//                .build();
//        var sql = """
//                insert into
//                %s
//                (%s)
//                values
//                (%s)
//                """;
//        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(table -> table.schema() + "." + table.name())
//                .orElse(user.getClass().getName());
//
//        Field[] fields = user.getClass().getDeclaredFields();
//
//        var columnNames = Arrays.stream(fields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName())
//                ).collect(Collectors.joining(", "));
//
//        var columnValues = Arrays.stream(fields)
//                .map(field -> "?")
//                .collect(Collectors.joining(", "));
//
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/barrelbase",
//                "barreluser", "barrelpassword");
//        PreparedStatement preparedStatement = connection
//                .prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//
//        for (int i = 0; i < fields.length; i++) {
//            fields[i].setAccessible(true);
//
//            if (fields[i].get(user).equals("birthDate")) {
//                // TODO: add Birthday logic
//            } else {
//                preparedStatement.setObject(i + 1, fields[i].get(user));
//            }
//        }
//        System.out.println(preparedStatement);
//        preparedStatement.executeUpdate();
//        preparedStatement.close();
//        connection.close();
//    }

    @Test
    public void checkInheritance() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                        .name("PrivatBank")
                                .build();

        session.persist(company);

//        var programmer = Programmer.builder()
//                .username("bohdan@gmail.com")
//                        .language(Language.JAVA)
//                                .company(company)
//                                        .build();

//        session.persist(programmer);
//
//        var manager = Manager.builder()
//                        .username("viktor@gmail.com")
//                                .project("Java Enterprise")
//                                        .company(company)
//                                                .build();

//        session.persist(manager);

        session.flush();
        session.clear();

//        var programmer1 = session.get(Programmer.class, 1L);
//        var manager1 = session.get(User.class, 2L);

        session.getTransaction().commit();
    }

    @Test
    public void checkHQL() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var name = "Bohdan";
        var company = "Google";

        var users = session.createNamedQuery("findUserByNameAndCompany", User.class)
                .setParameter("firstname", name)
                .setParameter("company", company)
                .list();

//        var users = session.createNativeQuery();


        // Update/Delete
        session.createQuery("UPDATE User u SET role = 'ADMIN'", User.class)
                .executeUpdate();

        System.out.println(users);

        session.getTransaction().commit();
    }
}
