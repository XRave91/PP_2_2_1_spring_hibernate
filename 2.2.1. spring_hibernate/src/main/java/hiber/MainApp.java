package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Polo", 2013);
        User usr1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
        car1.setUser(usr1);
        userService.add(usr1);

        car1 = new Car("Polo", 2013);
        usr1 = new User("User2", "Lastname2", "user2@mail.ru", car1);
        car1.setUser(usr1);
        userService.add(usr1);

        car1 = new Car("Ferrari", 2023);
        usr1 = new User("User3", "Lastname3", "user3@mail.ru", car1);
        car1.setUser(usr1);
        userService.add(usr1);

        car1 = new Car("Ford", 123);
        usr1 = new User("User4", "Lastname4", "user4@mail.ru", car1);
        car1.setUser(usr1);
        userService.add(usr1);


        List<User> users = userService.listUsers();
        for (User user : users) {
            printUser(user);
        }
        printUser(userService.getUserByCarParams("Polo", 2013));

        context.close();
    }

    public static void printUser(User user) {
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
        System.out.println();
    }
}
