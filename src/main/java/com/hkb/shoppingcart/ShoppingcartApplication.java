package com.hkb.shoppingcart;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.hkb.shoppingcart.model.CartUser;
import com.hkb.shoppingcart.model.Product;
import com.hkb.shoppingcart.repo.CartUserRepository;
import com.hkb.shoppingcart.repo.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ShoppingcartApplication {

	@Autowired
	private CartUserRepository cartUserRepository;

	@Autowired
	private ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(ShoppingcartApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ShoppingcartApplication.class, args);
		logger.info("---Shopping cart application started---");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
			return args -> {

					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					System.out.println("Let's inspect the beans provided by Spring Boot:");

					String[] beanNames = ctx.getBeanDefinitionNames();
					Arrays.sort(beanNames);
					for (String beanName : beanNames) {
							System.out.println(beanName);
					}

					cartUserRepository.deleteAll();
					productRepository.deleteAll();

					// save a couple of users
					cartUserRepository.save(new CartUser("Alice", "Smith", "alice", bCryptPasswordEncoder.encode("toto"), "alice@gmail.com"));
					cartUserRepository.save(new CartUser("Bob", "Smith", "bob", bCryptPasswordEncoder.encode("toto"), "bob@gmail.com"));
					cartUserRepository.save(new CartUser("toto", "toto", "toto", bCryptPasswordEncoder.encode("toto"), "toto@gmail.com"));

					//Initialize the default products
					SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


					productRepository.save(
							new Product(
									"Leaf Rake",
									"GDN-0011",
									19.95f,
									ft.parse("2017-03-19T15:15:55.570Z"),
									"Leaf rake with 48-inch wooden handle.",
									3.2f,
									"http://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png",
									400
							));
					productRepository.save(
							new Product(
									"Garden Cart",
									"GDN-0023",
									32.95f,
									ft.parse("2017-03-18T08:15:55.570Z"),
									"15 gallon capacity rolling garden cart",
									4.2f,
									"http://openclipart.org/image/300px/svg_to_png/58471/garden_cart.png",
									200
							));
					productRepository.save(
							new Product(
									"Hammer",
									"TBX-0048",
									8.9f,
									ft.parse("2017-05-21T12:15:55.570Z"),
									"Curved claw steel hammer",
									4.8f,
									"http://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png",
									400
							));
					productRepository.save(
							new Product(
									"Saw",
									"TBX-0022",
									11.55f,
									ft.parse("2017-05-15T12:15:55.570Z"),
									"15-inch steel blade hand saw",
									3.7f,
									"http://openclipart.org/image/300px/svg_to_png/27070/egore911_saw.png",
									1200
							));
					productRepository.save(
							new Product(
									"Video Game Controller",
									"GMG-0042",
									35.95f,
									ft.parse("2017-10-15T14:15:55.570Z"),
									"15-inch steel blade hand saw",
									4.6f,
									"http://openclipart.org/image/300px/svg_to_png/120337/xbox-controller_01.png",
									300
							));
					// fetch all users
					System.out.println("Users found with findAll():");
					System.out.println("-------------------------------");
					for (CartUser cartUser : cartUserRepository.findAll()) {
						System.out.println(cartUser);
					}
					System.out.println();

					// fetch an individual user
					System.out.println("CartUser found with findByFirstName('Alice'):");
					System.out.println("--------------------------------");
					System.out.println(cartUserRepository.findByFirstName("Alice"));

					System.out.println("Users found with findByLastName('Smith'):");
					System.out.println("--------------------------------");
					for (CartUser cartUser : cartUserRepository.findByLastName("Smith")) {
						System.out.println(cartUser);
					}

			};
	}

}
