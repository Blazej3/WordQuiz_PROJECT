package exercise.wordquiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import exercise.wordquiz.domain.WordSet;
import exercise.wordquiz.domain.WordSetRepository;
import exercise.wordquiz.domain.AppUser;
import exercise.wordquiz.domain.AppUserRepository;
import exercise.wordquiz.domain.Category;
import exercise.wordquiz.domain.CategoryRepository;


@SpringBootApplication
public class WordquizApplication {
    // Logger for logging application events
    private static final Logger log = LoggerFactory.getLogger(WordquizApplication.class);

    // Main method to run the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(WordquizApplication.class, args);
    }

    // Bean for initializing data on application startup
    @Bean
    public CommandLineRunner bookDemo(WordSetRepository w_repository, CategoryRepository c_repository, AppUserRepository u_repository) {
        return (args) -> {
            // Log information about saving entities
            log.info("save a couple of books");

            // Create instances of categories
            Category category1 = new Category("food");
            Category category2 = new Category("animals");
            Category category3 = new Category("bodypars");

            // Save categories to the repository
            c_repository.save(category1);
            c_repository.save(category2);
            c_repository.save(category3);

            // Create instances of WordSet and associate them with categories
            WordSet wordSet1 = new WordSet("apple", "omena", "This is an example sentence with an apple.", "easy", category1);
            WordSet wordSet2 = new WordSet("cat", "kissa", "This is an example sentence with a cat.", "medium", category2);
            WordSet wordSet3 = new WordSet("eye", "silmä", "This is an example sentence with an eye.", "hard", category3);
            WordSet wordSet4 = new WordSet("arm", "käsivarsi", "This is an example sentence with an arm.", "hard", category3);
            WordSet wordSet5 = new WordSet("stomach", "vatsa", "This is an example sentence with a stomach.", "hard", category3);

            // Save WordSet instances to the repository
            w_repository.save(wordSet1);
            w_repository.save(wordSet2);
            w_repository.save(wordSet3);
            w_repository.save(wordSet4);
            w_repository.save(wordSet5);


            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6","user1@gmail.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","user2@gmail.com", "ADMIN");
			u_repository.save(user1);
			u_repository.save(user2);
            

            log.info("fetch all wordsets");
			for (WordSet WordSet: w_repository.findAll()) {
				log.info(WordSet.toString());
			}
        };
    }
}
