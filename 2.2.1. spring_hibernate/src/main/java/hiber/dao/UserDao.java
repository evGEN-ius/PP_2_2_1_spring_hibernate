package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   User getUserByCar(String model, String series);
   List<User> listUsers();
}
