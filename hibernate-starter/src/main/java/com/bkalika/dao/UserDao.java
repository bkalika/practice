package com.bkalika.dao;

//import com.bkalika.entity.PersonalInfo_;
import com.bkalika.entity.User;
//import com.bkalika.entity.User_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria).list();
    }

    public List<User> findAllByFirstName(Session session, String firstName) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

//        criteria.select(user).where(cb.equal(user.get("personalInfo").get("firstname"), firstName));
//        criteria.select(user).where(cb.equal(user.get(User_.personalInfo)
//                .get(PersonalInfo_.FIRSTNAME), firstName)
//        );

        return session.createQuery(criteria).list();
    }
}
