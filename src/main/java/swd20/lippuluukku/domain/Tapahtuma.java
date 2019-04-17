package swd20.lippuluukku.domain;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class Tapahtuma {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long tapahtumaId;
	
	@OneToMany(cascade = CascadeType.ALL, 	mappedBy="tapahtuma")
	private List<Lippu> liput;
	
	@Size(min = 2, message = "Nimen on oltava vähintään 2 merkkiä pitkä.")
	@Size(max = 50, message = "Nimi voi olla enintään 50 merkkiä pitkä.")
	@Column(name = "tapahtumaNimi")
	private String tapahtumaNimi;
	
	@Column(name = "tapahtumaPvm")
	private String tapahtumaPvm;
	
	@Column(name = "tapahtumaAika")
	private String tapahtumaAika;
	
	@Column(name = "status")
	public String status = "Julkaistu";
	
	public Tapahtuma() {
		super();
		tapahtumaNimi = null;
		tapahtumaPvm = null;
		tapahtumaAika = null;
	}

	public Tapahtuma(String tapahtumaNimi, String tapahtumaPvm, String tapahtumaAika) {
		super();
		this.tapahtumaPvm = tapahtumaPvm;
		this.tapahtumaNimi = tapahtumaNimi;
		this.tapahtumaAika = tapahtumaAika;
	}
	
	public Tapahtuma(String tapahtumaNimi, String tapahtumaPvm, String tapahtumaAika, String status) {
		super();
		this.tapahtumaPvm = tapahtumaPvm;
		this.tapahtumaNimi = tapahtumaNimi;
		this.tapahtumaAika = tapahtumaAika;
		this.status = status;
	}

	public Long getTapahtumaId() {
		return tapahtumaId;
	}

	public void setTapahtumaId(Long tapahtumaId) {
		this.tapahtumaId = tapahtumaId;
	}

	public List<Lippu> getLiput() {
		return liput;
	}

	public void setLiput(List<Lippu> liput) {
		this.liput = liput;
	}

	public String getTapahtumaNimi() {
		return tapahtumaNimi;
	}

	public void setTapahtumaNimi(String tapahtumaNimi) {
		this.tapahtumaNimi = tapahtumaNimi;
	}

	public String getTapahtumaPvm() {
		return tapahtumaPvm;
	}

	public void setTapahtumaPvm(String tapahtumaPvm) {
		this.tapahtumaPvm = tapahtumaPvm;
	}

	public String getTapahtumaAika() {
		return tapahtumaAika;
	}

	public void setTapahtumaAika(String tapahtumaAika) {
		this.tapahtumaAika = tapahtumaAika;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setStatusJulkaistu() {
		this.status = "Julkaistu";
	}
	
	public void setStatusPeruttu() {
		this.status = "Peruttu";
	}

	@Override
	public String toString() {
		return "Tapahtuma id: "+ tapahtumaId +", nimi: "+ tapahtumaNimi + ", päivämäärä: " + tapahtumaPvm + ", aika: " + tapahtumaAika;
	}
	
}
