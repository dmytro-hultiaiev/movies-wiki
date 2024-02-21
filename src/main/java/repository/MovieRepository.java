package repository;

import entity.Actor;
import entity.Genre;
import entity.Movie;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class MovieRepository {
    private final Session session;

    public MovieRepository(Session session) {
        this.session = session;
    }
    public List<Movie> findByName(String title){
        Query<Movie> query = session
                .createQuery("FROM Movie WHERE title = :title", Movie.class)
                .setParameter("title", title);
        return query.list();
    }
    public Optional<Movie> findById(int id){
        return Optional.ofNullable(session.get(Movie.class, id));
    }
    public List<Movie> getAll(){
        Query<Movie> query = session
                .createQuery("FROM Movie", Movie.class);
        return query.list();
    }
    public List<Movie> findAllWithActors() {
        Query<Movie> query = session
                .createQuery("SELECT m FROM Movie m LEFT JOIN fetch m.actors", Movie.class);
        return query.list();
    }
    public void insert(Movie movie){
        session.beginTransaction();
        session.persist(movie);
        session.getTransaction().commit();
    }
    public void delete(String name){
        List<Movie> movies = findByName(name);
        session.beginTransaction();

        for(Movie elem : movies){
            session.remove(elem);
        }

        session.getTransaction().commit();
    }
}
