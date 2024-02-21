package repository;

import entity.Genre;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class GenreRepository {
    private final Session session;

    public GenreRepository(Session session) {
        this.session = session;
    }
    public List<Genre> findByName(String name){
        Query<Genre> query = session
                            .createQuery("FROM Genre WHERE name = :name", Genre.class)
                            .setParameter("name", name);
        return query.list();
    }
    public Optional<Genre> findById(int id){
        return Optional.ofNullable(session.get(Genre.class, id));
    }
    public List<Genre> getAll(){
        Query<Genre> query = session
                             .createQuery("FROM Genre", Genre.class);
        return query.list();
    }
    public void insert(Genre genre){
        session.beginTransaction();
        session.persist(genre);
        session.getTransaction().commit();
    }
    public void delete(String name){
        List<Genre> genres = findByName(name);
        session.beginTransaction();

        for(Genre elem : genres){
            session.remove(elem);
        }

        session.getTransaction().commit();
    }
}
