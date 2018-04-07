package pl.sternik.mr.heroku.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.sternik.mr.heroku.entities.Movie;
import pl.sternik.mr.heroku.entities.Rodzaj;
import pl.sternik.mr.heroku.repositories.MovieAlreadyExistsException;
import pl.sternik.mr.heroku.repositories.MoviesRepository;
import pl.sternik.mr.heroku.repositories.NoSuchMovieException;


@Service
@Primary
public class FilmotekaServiceJ8Impl implements FilmotekaService {

    @Autowired
    @Qualifier("lista")
    private MoviesRepository movies;

    @Override
    public List<Movie> findAll() {
        return movies.findAll();
    }

    @Override
    public List<Movie> findLatest3() {
        return movies.findAll().stream().sorted((a, b) -> b.getDataProdukcji().compareTo(a.getDataProdukcji())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        try {
            return Optional.of(movies.readById(id));
        } catch (NoSuchMovieException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Movie> create(Movie movie) {
        try {
            return Optional.of(movies.create(movie));
        } catch (MovieAlreadyExistsException e) {
            try {
                return Optional.of(movies.readById(movie.getNumerKatalogowy()));
            } catch (NoSuchMovieException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Movie> edit(Movie movie) {
        try {
            return Optional.of(movies.update(movie));
        } catch (NoSuchMovieException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            movies.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMovieException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Movie> findAllToSell() {
        return movies.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Rodzaj.KOMEDIOWY))
                .collect(Collectors.toList());
    }
}
