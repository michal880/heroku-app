package pl.sternik.mr.heroku.repositories;

import java.util.List;

import pl.sternik.mr.heroku.entities.Movie;


public interface MoviesRepository {
    Movie create(Movie movie) throws MovieAlreadyExistsException;
    Movie readById(Long id) throws NoSuchMovieException;
    Movie update(Movie movie) throws NoSuchMovieException;
    void deleteById(Long id) throws NoSuchMovieException;
    List<Movie> findAll();
}