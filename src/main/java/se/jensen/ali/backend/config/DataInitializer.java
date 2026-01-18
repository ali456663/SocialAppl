package se.jensen.ali.backend.config;



import se.jensen.ali.backend.model.User;
import se.jensen.ali.backend.model.Post;
import se.jensen.ali.backend.repository.UserRepository;
import se.jensen.ali.backend.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository,
                                      PostRepository postRepository) {
        return args -> {
            System.out.println("🚀 Startar att skapa testdata...");

            // 1. Rensa gamla data först
            System.out.println("🧹 Rensar gamla data...");
            postRepository.deleteAll();
            userRepository.deleteAll();

            // 2. Skapa två testanvändare med SETTERS
            System.out.println("👤 Skapar testanvändare...");

            User user1 = new User();  // Använd tom konstruktor
            user1.setUsername("ali");
            user1.setPassword("password123");

            User user2 = new User();  // Använd tom konstruktor
            user2.setUsername("bob");
            user2.setPassword("password123");

            // 3. Spara användarna
            User savedUser1 = userRepository.save(user1);
            User savedUser2 = userRepository.save(user2);
            System.out.println("✅ Användare sparade: ali och bob");

            // 4. Skapa tre testposts
            System.out.println("📝 Skapar testposts...");

            Post post1 = new Post();
            post1.setContent("Hej, detta är min första post!");
            post1.setUser(savedUser1);

            Post post2 = new Post();
            post2.setContent("Vad trevligt att se er här!");
            post2.setUser(savedUser2);

            Post post3 = new Post();
            post3.setContent("En till post från Ali");
            post3.setUser(savedUser1);

            // 5. Spara posts
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
            System.out.println("✅ 3 posts sparade");

            // 6. Skriv ut sammanfattning
            System.out.println("🎉 Testdata skapad klart!");
            System.out.println("   - 2 användare: ali (id: " + savedUser1.getId() + "), bob (id: " + savedUser2.getId() + ")");
            System.out.println("   - 3 posts totalt");
            System.out.println("🌐 API är redo på http://localhost:9090");
        };
    }
}