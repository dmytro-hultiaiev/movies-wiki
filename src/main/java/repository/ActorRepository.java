package repository;

import entity.Actor;
import entity.Movie;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

public class ActorRepository {
    private final Session session;

    public ActorRepository(Session session) {
        this.session = session;
    }
    public Optional<Actor> findById(int id){
        return Optional.ofNullable(session.get(Actor.class, id));
    }
    public List<Actor> findByYear(int year){
        Query<Actor> query = session
                             .createQuery("select a from Actor a where a.yearOfBirth > :year", Actor.class)
                             .setParameter("year", year);
        return query.list();
    }
    public List<Actor> findByLastLetters(String word){
        Query<Actor> query = session
                .createQuery("select a from Actor a where a.lastname LIKE :lastName", Actor.class)
                .setParameter("lastName", word);
        return query.list();
    }
    public void insert(Actor actor){
        session.beginTransaction();
        session.persist(actor);
        session.getTransaction().commit();
    }
}
