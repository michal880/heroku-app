package pl.sternik.mr.heroku.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.mr.heroku.entities.Movie;
import pl.sternik.mr.heroku.repositories.MovieAlreadyExistsException;
import pl.sternik.mr.heroku.repositories.MoviesRepository;
import pl.sternik.mr.heroku.repositories.NoSuchMovieException;


@Service
@Qualifier("tablica")
public class FilmotekaServiceImpl implements FilmotekaService {

    @Autowired
    @Qualifier("tablica")
    private MoviesRepository bazaDanych;

    @Override
    public List<Movie> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Movie> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchMovieException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Movie> create(Movie movie) {
        try {
            return Optional.of(bazaDanych.create(movie));
        } catch (MovieAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Movie> edit(Movie movie) {
        try {
            return Optional.of(bazaDanych.update(movie));
        } catch (NoSuchMovieException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMovieException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Movie> findLatest3() {
        //TODO: 
        return Collections.emptyList();
    }

}
