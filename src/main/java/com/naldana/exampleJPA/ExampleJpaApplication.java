package com.naldana.exampleJPA;

import java.sql.SQLException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.naldana.exampleJPA.models.Gender;
import com.naldana.exampleJPA.models.User;
import com.naldana.exampleJPA.models.UserProfile;
import com.naldana.exampleJPA.repositories.UserProfileRepository;
import com.naldana.exampleJPA.repositories.UserRepository;

@SpringBootApplication
public class ExampleJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(ExampleJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExampleJpaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner example(UserRepository userRepository) {
		return (args) -> {
			log.info("Iniciando Aplicación");
			crearUsuario(userRepository);
		};
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
