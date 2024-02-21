import entity.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.ActorRepository;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        try(SessionFactory sessionFactory = configuration.buildSessionFactory()){
            Session session = sessionFactory.openSession();

            ActorRepository actorRepository = new ActorRepository(session);

            Actor actor = Actor.builder()
                            .name("Cillian")
                            .lastname("Murphy")
                            .yearOfBirth(1976)
                            .movies(null)
                            .build();

            actorRepository.insert(actor);
        }
    }
}
