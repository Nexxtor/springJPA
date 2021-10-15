package com.naldana.exampleJPA;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.naldana.exampleJPA.models.Cart;
import com.naldana.exampleJPA.models.Course;
import com.naldana.exampleJPA.models.Gender;
import com.naldana.exampleJPA.models.Items;
import com.naldana.exampleJPA.models.Student;
import com.naldana.exampleJPA.models.User;
import com.naldana.exampleJPA.models.UserProfile;
import com.naldana.exampleJPA.repositories.CartRepository;
import com.naldana.exampleJPA.repositories.ItemsRepository;
import com.naldana.exampleJPA.repositories.UserProfileRepository;
import com.naldana.exampleJPA.repositories.UserRepository;
import com.naldana.exampleJPA.services.StudentCourseDataService;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@SpringBootApplication
public class ExampleJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(ExampleJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExampleJpaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner example(UserRepository userRepository, CartRepository cartRepository, ItemsRepository itemsRepository) {
		return (args) -> {
			log.info("Iniciando Aplicación");
			crearUsuario(userRepository);
			probarOneToMany(cartRepository,itemsRepository);
		};
	}
	
	
	@Bean 
	CommandLineRunner example2(StudentCourseDataService dataService) {
		return (args) -> {
			Student s = new Student("Nestor");
			Course c = new Course("Spring");
			dataService.saveCourse(c);
			dataService.saveStudent(s);
			dataService.conectStudentAndCourse(s, c);
		};
	}
	
	private static void probarOneToMany(CartRepository cartRepository, ItemsRepository itemsRepository) {
		
		log.info("Creando una Cart");
		Cart cart = new Cart();
		cartRepository.save(cart);
		
		log.info("Añadimos 5 items a la cart");
		Set<Items> items = new HashSet<Items>();
		for (int i = 0 ; i <5 ; i++) {
			Items item = new Items();
			item.setCart(cart);
			items.add(item);
		}
		
		itemsRepository.saveAll(items);
		
		log.info("Todas las carts");
		List<Cart> carts = cartRepository.findAll();
		carts.forEach((Cart cartElement) -> {
			log.info(cartElement.getCartId().toString());
			Set<Items> itemsCart = cartElement.getItems();
			itemsCart.forEach( i -> {
				log.info("\t" + i.getId().toString());
			});
		});
	}

	public static void crearUsuario(UserRepository userRepository) {
		log.info("Guardado un usurio");
		
		User nestor = new User("Nestor", "Aldana","nestor1.aldana1@gmail.com","password");
		
		
		Calendar dob = Calendar.getInstance();
		dob.set(1994,01,11);
		// Creando perfil
		
		UserProfile profile = new UserProfile("XXXXX", Gender.MALE, dob.getTime(), "a", "b", "c", "d", "e", "asd");
		
		nestor.setUserProfile(profile);
		profile.setUser(nestor);
		
		try {
			userRepository.save(nestor);
		} catch(Exception e) {
			log.warn("El usuario no puedo ser creado por: " + e.getMessage());
			
		}
		
		log.info("Todos los usuarios");
		log.info("-------------------------------");
		for ( User user: userRepository.findAll()) {
			log.info(user.toString());
			log.info(user.getUserProfile().getCity());
		}
		log.info("-------------------------------");
	}
}
