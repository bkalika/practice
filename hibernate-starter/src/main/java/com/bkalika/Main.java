package com.bkalika;

import com.bkalika.dao.CompanyRepository;
import com.bkalika.dao.PaymentRepository;
import com.bkalika.dao.UserRepository;
import com.bkalika.dto.UserCreateDto;
import com.bkalika.entity.*;
import com.bkalika.mapper.CompanyReadMapper;
import com.bkalika.mapper.UserCreateMapper;
import com.bkalika.mapper.UserReadMapper;
import com.bkalika.service.UserService;
import com.bkalika.util.HibernateUtil;
import jakarta.persistence.LockModeType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.time.LocalDate;

@Slf4j
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class); // used annotation instead of this

    public static void main(String[] args) {
//        Company company = Company.builder()
//                .name("Google")
//                .build();

        // TRANSIENT
//        var user = User.builder()
//                .username("pilip@mail.com")
//                .personalInfo(PersonalInfo.builder()
//                        .firstname("Pilip")
//                        .lastname("Orlik")
//                        .birthDate(new Birthday(LocalDate.of(1560, 1, 3)))
//                        .build())
//                .role(Role.ADMIN)
//                .company(company)
//                .build();
//        log.info("User object in transient state: {}", user);
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        configuration.addAttributeConverter(BirthdayConverter.class, true);
//        configuration.addAnnotatedClass(User.class);
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
//            try (Session session = sessionFactory.openSession()) {
//                session.beginTransaction();

//                user.setFirstname("PilipEdited");
//                log.warn("User firstname is changed: {}", user);
//                // PERSISTENT to session and TRANSIENT to session2
//                session.merge(company);
//                session.merge(user);
//                log.debug("User: {}, session: {}", user, session);
//                session.getTransaction().commit();
//            }

//            try (Session session2 = sessionFactory.openSession()) {
//                session2.beginTransaction();
//
//                // First GET than DELETE PERSISTENT to session2 DETACHED to session
//                session2.remove(user);
//
//                // REMOVED to session2
//                session2.getTransaction().commit();
//            }
//        } catch (Exception exception) {
//            log.error("Exception occurred: ", exception);
//            throw exception;
//        }

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    ((proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1)));
            session.beginTransaction();

            var paymentRepository = new PaymentRepository(session);
            paymentRepository.findById(0L).ifPresent(System.out::println);

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);

            var userRepository = new UserRepository(session);
            var companyRepository = new CompanyRepository(session);
            var userCreateMapper = new UserCreateMapper(companyRepository);
            var userService = new UserService(userRepository, userReadMapper, userCreateMapper);

            userService.findUserById(0L).ifPresent(System.out::println);

            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstname("Anna")
                            .lastname("Milkus")
                            .birthDate(new Birthday(LocalDate.now()))
                            .build(),
                    "anna3@gmail.com",
                    Role.USER,
                    0L
            );
            System.out.println(userService.create(userCreateDto));

            session.getTransaction().commit();
        }
//        Session session = sessionFactory.getCurrentSession()) {
//            TestDataImporter.importData(sessionFactory);

//            var payment = session.find(Payment.class, 0L, LockModeType.OPTIMISTIC);
//            if (payment != null) {
//                payment.setAmount(payment.getAmount() + 10);
//            }
//        }
    }
}
