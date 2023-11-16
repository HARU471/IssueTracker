package geiffel.da4.issuetracker;

import geiffel.da4.issuetracker.commentaire.Commentaire;
import geiffel.da4.issuetracker.commentaire.CommentaireRepository;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueRepository;
import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IssueTrackerStudentsApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CommentaireRepository commentaireRepository;

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerStudentsApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUpBDD() {
        return args -> {


            List<User> users = new ArrayList<>();

            User user1 = new User(1L, "Machin", Fonction.USER);
            User user2 = new User(2L, "Chose", Fonction.USER);
            User user3 = new User(3L, "Truc", Fonction.DEVELOPPER);

            users.add(user1);
            users.add(user2);
            users.add(user3);

            userRepository.saveAll(users);

            List<Issue> issues = new ArrayList<>();

            Issue issue1 = new Issue(1L, "Titre1", "Content1", users.get(0), Timestamp.from(Instant.now()), null);
            Issue issue2 = new Issue(2L, "Titre2", "Content2", users.get(1), Timestamp.from(Instant.now()), null);

            issues.add(issue1);
            issues.add(issue2);

            issueRepository.saveAll(issues);

            List<Commentaire> commentaires = new ArrayList<>();
            Commentaire commentaire1 = new Commentaire(1L, users.get(0), issues.get(0), "String contenu");
            Commentaire commentaire2 = new Commentaire(2L, users.get(1), issues.get(0), "String contenu 2222222");

            commentaires.add(commentaire1);
            commentaires.add(commentaire2);

            commentaireRepository.saveAll(commentaires);
        };
    }

}
