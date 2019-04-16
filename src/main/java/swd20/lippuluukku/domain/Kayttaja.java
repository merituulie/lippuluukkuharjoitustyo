package swd20.lippuluukku.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kayttaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kayttajaId", nullable = false, updatable = false)
	private Long kayttajaId;
	private String rooli;
	
	@OneToMany(mappedBy = "kayttaja")
	private List<Lippu> liput;
	
	@Column(name = "kayttajaNimi", nullable = false, unique = true)
	private String kayttajaNimi;
	
	@Column(name = "salasana", nullable = false)
	private String salasanaHash;
	
	@Column(name = "sposti", nullable = false)
	private String sposti;
	
	public Kayttaja() {
		super();
		kayttajaNimi = null;
		salasanaHash = null;
		sposti = null;
	}
	
	public Kayttaja(String kayttajaNimi, String salasanaHash, String sposti, String rooli) {
		super();
		this.kayttajaNimi = kayttajaNimi;
		this.salasanaHash = salasanaHash;
		this.sposti = sposti;
		this.rooli = rooli;
	}

	public Long getKayttajaId() {
		return kayttajaId;
	}

	public void setKayttajaId(Long userId) {
		this.kayttajaId = userId;
	}

	public String getRooli() {
		return rooli;
	}

	public void setRooli(String rooli) {
		this.rooli = rooli;
	}

	public String getKayttajaNimi() {
		return kayttajaNimi;
	}

	public void setKayttajaNimi(String kayttajaNimi) {
		this.kayttajaNimi = kayttajaNimi;
	}

	public String getSalasanaHash() {
		return salasanaHash;
	}

	public void setSalasanaHash(String salasanaHash) {
		this.salasanaHash = salasanaHash;
	}

	public String getSposti() {
		return sposti;
	}

	public void setSposti(String sposti) {
		this.sposti = sposti;
	}
	
	public List<Lippu> getLiput() {
		return liput;
	}

	public void setLiput(List<Lippu> liput) {
		this.liput = liput;
	}
	
	public void varaaLiput(Lippu lippu) {
		lippu.setTilaVarattu();
		liput.add(lippu);
	}

	@Override
	public String toString() {
		return "Käyttäjän rooli: " + rooli + ", nimi: " + kayttajaNimi;
	}
}
