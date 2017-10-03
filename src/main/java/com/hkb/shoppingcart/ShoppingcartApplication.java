package com.hkb.shoppingcart;

import java.util.Arrays;

import com.hkb.shoppingcart.repo.UserRepository;
import com.hkb.shoppingcart.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShoppingcartApplication {

	@Autowired
	private UserRepository repo;

	private static final Logger logger = LoggerFactory.getLogger(ShoppingcartApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ShoppingcartApplication.class, args);
		logger.info("---Shopping cart application started---");
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
			return args -> {

					System.out.println("Let's inspect the beans provided by Spring Boot:");

					String[] beanNames = ctx.getBeanDefinitionNames();
					Arrays.sort(beanNames);
					for (String beanName : beanNames) {
							System.out.println(beanName);
					}

					repo.deleteAll();

					// save a couple of users
					repo.save(new User("Alice", "Smith", "alice@gmail.com"));
					repo.save(new User("Bob", "Smith", "bob@gmail.com"));

					// fetch all users
					System.out.println("Users found with findAll():");
					System.out.println("-------------------------------");
					for (User user : repo.findAll()) {
						System.out.println(user);
					}
					System.out.println();

					// fetch an individual user
					System.out.println("User found with findByFirstName('Alice'):");
					System.out.println("--------------------------------");
					System.out.println(repo.findByFirstName("Alice"));

					System.out.println("Users found with findByLastName('Smith'):");
					System.out.println("--------------------------------");
					for (User user : repo.findByLastName("Smith")) {
						System.out.println(user);
					}

			};
	}

}
