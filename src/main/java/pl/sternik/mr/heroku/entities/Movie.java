package pl.sternik.mr.heroku.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class Movie {

  @NotNull
  private Long numerKatalogowy;
  @Size(min=2, max=30, message = "Opis should be in the range [{min}...{max}]")
  private String opis;
  @NotEmpty
  private String tytul;
  @NotNull
  private BigDecimal cenaNabycia;
  @NotNull
  private Date dataProdukcji;
  @NotEmpty
  private String rezyser;
  @NotNull
  private Rodzaj rodzaj;

	
	
	public static Movie produceMovie(Long numerKatalogowy, String rezyser, String opis,
									  Date dataProdukcji, BigDecimal cenaNabycia, Rodzaj rodzaj, String tytul) {
		Movie m = new Movie();
		m.numerKatalogowy = numerKatalogowy;
		m.rezyser = rezyser;
		m.opis = opis;
		m.cenaNabycia = cenaNabycia;
		m.rodzaj = rodzaj;
		m.dataProdukcji = dataProdukcji;
		m.tytul = tytul;
		return m;
	}

	public Long getNumerKatalogowy() {
		return numerKatalogowy;
	}

	public void setNumerKatalogowy(Long numerKatalogowy) {
		this.numerKatalogowy = numerKatalogowy;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public BigDecimal getCenaNabycia() {
		return cenaNabycia;
	}

	public void setCenaNabycia(BigDecimal cenaNabycia) {
		this.cenaNabycia = cenaNabycia;
	}

	public Date getDataProdukcji() {
		return dataProdukcji;
	}

	public void setDataProdukcji(Date dataProdukcji) {
		this.dataProdukcji = dataProdukcji;
	}

	public String getRezyser() {
		return rezyser;
	}

	public void setRezyser(String rezyser) {
		this.rezyser = rezyser;
	}

	public Rodzaj getRodzaj() {
		return rodzaj;
	}

	public void setRodzaj(Rodzaj rodzaj) {
		this.rodzaj = rodzaj;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Movie movie = (Movie) o;
		return Objects.equals(numerKatalogowy, movie.numerKatalogowy) &&
				Objects.equals(opis, movie.opis) &&
				Objects.equals(tytul, movie.tytul) &&
				Objects.equals(cenaNabycia, movie.cenaNabycia) &&
				Objects.equals(dataProdukcji, movie.dataProdukcji) &&
				Objects.equals(rezyser, movie.rezyser) &&
				rodzaj == movie.rodzaj;
	}

	@Override
	public int hashCode() {

		return Objects.hash(numerKatalogowy, opis, tytul, cenaNabycia, dataProdukcji, rezyser, rodzaj);
	}

	@Override
	public String toString() {
		return "Movie{" +
				"numerKatalogowy=" + numerKatalogowy +
				", opis='" + opis + '\'' +
				", tytul='" + tytul + '\'' +
				", cenaNabycia=" + cenaNabycia +
				", dataProdukcji=" + dataProdukcji +
				", rezyser='" + rezyser + '\'' +
				", rodzaj=" + rodzaj +
				'}';
	}
}
