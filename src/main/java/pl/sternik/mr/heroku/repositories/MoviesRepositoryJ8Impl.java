package pl.sternik.mr.heroku.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.mr.heroku.entities.Movie;
import pl.sternik.mr.heroku.entities.Rodzaj;



@Service
//@Qualifier("lista")
public class MoviesRepositoryJ8Impl implements MoviesRepository {

    private List<Movie> movies = new ArrayList<Movie>() {
        private static final long serialVersionUID = 1L;
        {
            add(Movie.produceMovie(1L, "Polska", "test", new Date(), new BigDecimal("1.2"), Rodzaj.AKCJI, "tytul"));
            add(Movie.produceMovie(2L, "Polska", "test", new Date(),  new BigDecimal("1.2"), Rodzaj.DRAMAT, "tytul3"));
            add(Movie.produceMovie(3L, "Polska", "test", new Date(), new BigDecimal("1.2"), Rodzaj.DRAMAT, "tytul4"));
            add(Movie.produceMovie(4L, "Polska", "test", new Date(),  new BigDecimal("1.2"),Rodzaj.PRZYGODOWY, "tytul5"));
            add(Movie.produceMovie(5L, "Polska", "test", new Date(), new BigDecimal("1.2"), Rodzaj.DRAMAT, "tytul6"));
            add(Movie.produceMovie(6L, "Polska", "test", new Date(), new BigDecimal("1.2"), Rodzaj.DRAMAT, "tytul8"));
        }
    };

    @Override
    public List<Movie> findAll() {
        return this.movies;
    }

    @Override
    public Movie readById(Long id) throws NoSuchMovieException {
        return this.movies.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchMovieException::new);
    }

    @Override
    public Movie create(Movie movie) {
        if (!movies.isEmpty()) {
            movie.setNumerKatalogowy(
                    this.movies.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            movie.setNumerKatalogowy(1L);
        }
        this.movies.add(movie);
        return movie;
    }

    @Override
    public Movie update(Movie movie) throws NoSuchMovieException {
        for (int i = 0; i < this.movies.size(); i++) {
            if (Objects.equals(this.movies.get(i).getNumerKatalogowy(), movie.getNumerKatalogowy())) {
                this.movies.set(i, movie);
                return movie;
            }
        }
        throw new NoSuchMovieException("Nie ma takiego filmu: " + movie.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchMovieException {
        for (int i = 0; i < this.movies.size(); i++) {
            if (Objects.equals(this.movies.get(i).getNumerKatalogowy(), id)) {
                this.movies.remove(i);
            }
        }
        throw new NoSuchMovieException("Nie ma takiego filmu: " + id);
    }

}
