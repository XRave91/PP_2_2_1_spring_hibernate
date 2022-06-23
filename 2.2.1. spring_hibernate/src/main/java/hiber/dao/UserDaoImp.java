package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@EnableTransactionManagement
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired

    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override

    public User getUserByCarParams(String model, int series) {
        User result = sessionFactory.getCurrentSession()
                .createQuery("SELECT o.user from Car o WHERE o.model=:model and o.series=:series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList().get(0);
        return result;


    }

}
