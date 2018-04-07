package pl.sternik.mr.heroku.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pl.sternik.mr.heroku.entities.Movie;
import pl.sternik.mr.heroku.entities.Rodzaj;



@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements MoviesRepository {

    private Movie[] baza;

    public ProstaBazaDanych() {
        baza = new Movie[15];
        Movie m = new Movie();
        m.setNumerKatalogowy(0L);
        m.setOpis("Fajny film");
        m.setDataProdukcji(new Date());
        m.setCenaNabycia(new BigDecimal("1.2"));
        m.setStatus(Rodzaj.AKCJI);
        baza[0] = m;
        m = new Movie();
        m.setNumerKatalogowy(2L);
        m.setOpis("Ładna nowiutka dwu złotóweczka");
        m.setCenaNabycia(new BigDecimal("2.2"));
        baza[2] = m;

    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new Movie[rozmiarBazy];
    }

    @Override
    public Movie create(Movie movie) throws MovieAlreadyExistsException {
        if (movie.getNumerKatalogowy() != null && baza[movie.getNumerKatalogowy().intValue()] != null) {
            if (movie.getNumerKatalogowy().equals(baza[movie.getNumerKatalogowy().intValue()].getNumerKatalogowy())) {
                throw new MovieAlreadyExistsException("Już jest movie o takim numerze.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = movie;
                movie.setNumerKatalogowy((long) i);
                return movie;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteById(Long id) throws NoSuchMovieException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchMovieException("Nie poprawny numer katologowy");
        }
        // tu troche zle ;)
        baza[numerKatalogowy] = null;
    }

    @Override
    public Movie update(Movie movie) throws NoSuchMovieException {
        int numerKatalogowy = movie.getNumerKatalogowy().intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchMovieException("Nie poprawny numer katologowy");
        }

        Movie m = baza[movie.getNumerKatalogowy().intValue()];
        if (m == null) {
            throw new NoSuchMovieException("Brak takiej monety.");
        } else {
            baza[movie.getNumerKatalogowy().intValue()] = movie;
        }
        return movie;
    }

    @Override
    public Movie readById(Long numerKatalogowy) throws NoSuchMovieException {
        int id = numerKatalogowy.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(id) || czyWolne(id)) {
            throw new NoSuchMovieException();
        }
        return baza[id];
    }

    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }

    public void wyswietlBaze() {
        for (int i = 0; i < baza.length; i++) {
            System.out.println("" + i + ":" + baza[i]);
        }
    }

    private boolean sprawdzPoprawnoscNumeruKatalogowego(int numerKatalogowy) {
        if (numerKatalogowy < 0 || numerKatalogowy >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }

}
