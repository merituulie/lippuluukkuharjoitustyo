package swd20.lippuluukku.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Lipputyyppi {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long lipputyyppiId;
	
	private String lipputyyppiNimi;

	@OneToMany(mappedBy = "lippuTyyppi")
	private List<Lippu> liput;
	
	public Lipputyyppi() {
		super();
		lipputyyppiNimi = null;
	}

	public Lipputyyppi(String lipputyyppiNimi) {
		super();
		this.lipputyyppiNimi = lipputyyppiNimi;
	}

	public String getLipputyyppiNimi() {
		return lipputyyppiNimi;
	}

	public void setLipputyyppiNimi(String lipputyyppiNimi) {
		this.lipputyyppiNimi = lipputyyppiNimi;
	}

	public List<Lippu> getLiput() {
		return liput;
	}

	public void setLiput(List<Lippu> liput) {
		this.liput = liput;
	}
	
	@Override
	public String toString() {
		return lipputyyppiNimi;
	}
	
}
