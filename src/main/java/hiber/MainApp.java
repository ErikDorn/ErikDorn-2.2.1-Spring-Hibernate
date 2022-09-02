package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Valera","Valerkin","valerkin@mail.ru");
      User user2 = new User("Aleks","Shindler","shindler@mail.ru");
      User user3 = new User("Normalniy","Pacik","pacik@mail.ru");
      User user4 = new User("Viktor","Orban","orban@mail.ru");

      Car car1 = new Car("OKA",222);
      Car car2 = new Car("VAZ2101",4444);
      Car car3 = new Car("LadaSedanBaklajan",777);
      Car car4 = new Car("Zaporojec",987);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      System.out.println(userService.getUserByCar("OKA", 222));
      try {
         User notFoundUser = userService.getUserByCar("sdfs", 3423);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
