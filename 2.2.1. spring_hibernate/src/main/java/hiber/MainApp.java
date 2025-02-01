package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.service.CarService;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);
      carService.add(new Car("Test1", "Test1"));
      carService.add(new Car("Test2", "Test2"));
      carService.add(new Car("Test3", "Test3"));
      carService.add(new Car("Test4", "Test4"));
      List<Car> cars = carService.listCars();


      UserService userService = context.getBean(UserService.class);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru",cars.get(0)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",cars.get(1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",cars.get(2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",cars.get(3)));

      List<User> users = userService.listUsers();

      // метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
      System.out.println("\n \u001B[32m" + userService.getUserByCar("Test2", "Test2").getFirstName()  + "\n \u001B[0m");

      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      context.close();
   }





}
