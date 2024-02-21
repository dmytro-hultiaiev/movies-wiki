package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies", schema = "public")
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "year_of_release")
    private int yearOfRelease;
    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors = new ArrayList<>();
    @ManyToOne
    private Genre genre;
}
