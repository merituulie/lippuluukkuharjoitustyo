package swd20.lippuluukku.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lippu {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long lippuId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="lipputyyppiId")
	public Lipputyyppi lippuTyyppi;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="tapahtumaId")
	private Tapahtuma tapahtuma;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="kayttajaId")
	private Kayttaja kayttaja;
	
	@Column(name = "hinta")
	public	 double hinta;
	
	@Column(name= "montakoLippua")
	private int montakoLippua;
	
	@Column(name = "tila")
	public String tila;
	
	public Lippu() {
		lippuTyyppi = null;
		tapahtuma = null;
		kayttaja = null;
		hinta = 0.0;
		montakoLippua = 0;
	}
	
	public Lippu(Tapahtuma tapahtuma) {
		super();
		this.tapahtuma = tapahtuma;
	}
	
	public Lippu(Lipputyyppi lippuTyyppi, Tapahtuma tapahtuma, double hinta) {
		super();
		this.lippuTyyppi = lippuTyyppi;
		this.tapahtuma = tapahtuma;
		this.hinta = hinta;
	}
	
	public Lippu(Lipputyyppi lippuTyyppi, Tapahtuma tapahtuma, double hinta, String tila) {
		super();
		this.lippuTyyppi = lippuTyyppi;
		this.tapahtuma = tapahtuma;
		this.hinta = hinta;
		this.tila = tila;
	}
	
	public Lippu(Lipputyyppi lippuTyyppi, Tapahtuma tapahtuma, double hinta, Kayttaja kayttaja) {
		super();
		this.lippuTyyppi = lippuTyyppi;
		this.tapahtuma = tapahtuma;
		this.hinta = hinta;
		this.kayttaja = kayttaja;
	}

	public Lippu(Lipputyyppi lippuTyyppi, Tapahtuma tapahtuma, double hinta, int montakoLippua) {
		super();
		this.lippuTyyppi = lippuTyyppi;
		this.tapahtuma = tapahtuma;
		this.hinta = hinta;
		this.montakoLippua = montakoLippua;
	}
	
	public Long getLippuId() {
		return lippuId;
	}

	public void setLippuId(Long lippuId) {
		this.lippuId = lippuId;
	}

	public Lipputyyppi getlippuTyyppi() {
		return lippuTyyppi;
	}

	public void setlippuTyyppi(Lipputyyppi lippuTyyppi) {
		this.lippuTyyppi = lippuTyyppi;
	}

	public Tapahtuma getTapahtuma() {
		return tapahtuma;
	}

	public void setTapahtuma(Tapahtuma tapahtuma) {
		this.tapahtuma = tapahtuma;
	}

	public double gethinta() {
		return hinta;
	}

	public void sethinta(double hinta) {
		this.hinta = hinta;
	}

	public int getMontakoLippua() {
		return montakoLippua;
	}

	public void setMontakoLippua(int montakoLippua) {
		this.montakoLippua = montakoLippua;
	}

	public String getTila() {
		return tila;
	}

	public void setTila(String tila) {
		this.tila = tila;
	}
	
	public void setTilaVapaa() {
		this.tila = "Vapaa";
	}
	
	public void setTilaVarattu() {
		this.tila = "Varattu";
	}

	public Kayttaja getKayttaja() {
		return kayttaja;
	}

	public void setKayttaja(Kayttaja kayttaja) {
		this.kayttaja = kayttaja;
	}

	@Override
	public String toString() {
		return "hinta: " + hinta;
	}

}
