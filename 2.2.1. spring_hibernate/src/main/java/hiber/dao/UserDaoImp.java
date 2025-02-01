package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public  User getUserByCar(String model, String series) {
      Transaction transaction = null;
      User user = null;

      try (Session session = sessionFactory.openSession()) {
         transaction = session.beginTransaction();

         String hql = "FROM User u WHERE u.car.series = :model AND u.car.series = :series";
         Query<User> query = session.createQuery(hql, User.class);
         query.setParameter("model", model);
         query.setParameter("series", series);

         List<User> users = query.getResultList();
         if (!users.isEmpty()) {
            user = users.get(0);
         }
         transaction.commit();
      } catch (Exception e) {
         if (transaction != null) {
            transaction.rollback();
         }
         e.printStackTrace();
      }
      return user;
   }

}
