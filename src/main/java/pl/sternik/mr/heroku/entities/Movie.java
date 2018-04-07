package pl.sternik.mr.heroku.entities;

import java.math.BigDecimal;
import java.util.Date;

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

	public String getTytul() {
		return tytul;
	}

	public String getOpis() {
		return opis;
	}


	public BigDecimal getCenaNabycia() {
		return cenaNabycia;
	}

	public Date getDataProdukcji(){ return dataProdukcji;}



	public Rodzaj getStatus() { return rodzaj; }

	public void setNumerKatalogowy(Long numerKatalogowy) {
		this.numerKatalogowy = numerKatalogowy;
	}
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public void setCenaNabycia(BigDecimal cenaNabycia) {
		this.cenaNabycia = cenaNabycia;
	}

	public void setStatus(Rodzaj rodzaj) {
		this.rodzaj = rodzaj;
	}
	public void setDataProdukcji(Date data) {
		this.dataProdukcji = dataProdukcji;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((cenaNabycia == null) ? 0 : cenaNabycia.hashCode());
		result = prime * result + ((numerKatalogowy == null) ? 0 : numerKatalogowy.hashCode());
		result = prime * result + ((rodzaj == null) ? 0 : rodzaj.hashCode());
		return result;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Movie other = (Movie) obj;
//		if (opis == null) {
//			if (other.opis != null)
//				return false;
//		} else if (!opis.equals(other.opis))
//			return false;
//		if (cenaNabycia == null) {
//			if (other.cenaNabycia != null)
//				return false;
//		} else if (!cenaNabycia.equals(other.cenaNabycia))
//			return false;
//		if (dataNabycia == null) {
//			if (other.dataNabycia != null)
//				return false;
//		} else if (!dataNabycia.equals(other.dataNabycia))
//			return false;
//		if (krajPochodzenia == null) {
//			if (other.krajPochodzenia != null)
//				return false;
//		} else if (!krajPochodzenia.equals(other.krajPochodzenia))
//			return false;
//		if (nominal == null) {
//			if (other.nominal != null)
//				return false;
//		} else if (!nominal.equals(other.nominal))
//			return false;
//		if (numerKatalogowy == null) {
//			if (other.numerKatalogowy != null)
//				return false;
//		} else if (!numerKatalogowy.equals(other.numerKatalogowy))
//			return false;
//		if (status == null) {
//			if (other.status != null)
//				return false;
//		} else if (!status.equals(other.status))
//			return false;
//		if (waluta == null) {
//			if (other.waluta != null)
//				return false;
//		} else if (!waluta.equals(other.waluta))
//			return false;
//		return true;
//	}

	@Override
	public String toString() {
		return "Movie [numerKatalogowy=" + numerKatalogowy + ", tytul=" + tytul + ", Opis=" + opis + ", rezyser="
				+ rezyser + ", cenaNabycia=" + cenaNabycia + ", rodzaj=" + rodzaj + "]";
	}

}
