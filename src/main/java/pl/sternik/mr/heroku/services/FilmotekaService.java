package pl.sternik.mr.heroku.services;

import java.util.List;
import java.util.Optional;

import pl.sternik.mr.heroku.entities.Movie;


public interface FilmotekaService {
    List<Movie> findAll();

    List<Movie> findAllToSell();

    Optional<Movie> findById(Long id);

    Optional<Movie> create(Movie movie);

    Optional<Movie> edit(Movie movie);

    Optional<Boolean> deleteById(Long id);

    List<Movie> findLatest3();
}