package swd20.lippuluukku;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import swd20.lippuluukku.domain.Lippu;
import swd20.lippuluukku.domain.LippuRepository;
import swd20.lippuluukku.domain.Lipputyyppi;
import swd20.lippuluukku.domain.LipputyyppiRepository;
import swd20.lippuluukku.domain.Tapahtuma;
import swd20.lippuluukku.domain.TapahtumaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LippuluukkuKonstruktoriTest {
	
	@Autowired
	private TapahtumaRepository tapahtumarepository;
	
	@Autowired
	private LippuRepository lippurepository;
	
	@Autowired
	private LipputyyppiRepository lipputyyppirepository;
	
	@Test
	public void tapahtumanLuontiToimii() {
		Tapahtuma tapahtuma1 = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		tapahtumarepository.save(tapahtuma1);
		
		assertThat(tapahtuma1.getTapahtumaId()).isNotNull();
		assertThat(tapahtuma1.getTapahtumaNimi()).contains("Ruisrock");
		assertThat(tapahtuma1.getTapahtumaPvm()).contains("30.6.2019");
		assertThat(tapahtuma1.getTapahtumaAika()).contains("18:00");
	}
	
	@Test
	public void tapahtumanPeruminen() {
		Tapahtuma tapahtuma = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		tapahtuma.setStatusPeruttu();
		tapahtumarepository.save(tapahtuma);

		assertThat(tapahtuma.getStatus()).contains("Peruttu");
	}
	
	@Test
	public void tapahtumanJulkaisu() {
		Tapahtuma tapahtuma = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		tapahtuma.setStatusJulkaistu();
		tapahtumarepository.save(tapahtuma);

		assertThat(tapahtuma.getStatus()).contains("Julkaistu");
	}
	
	@Test
	public void lipputyypinLuontiToimii() {
		Lipputyyppi lipputyyppi1 = new Lipputyyppi("VIP");
		lipputyyppirepository.save(lipputyyppi1);
		
		assertThat(lipputyyppi1.getLipputyyppiNimi()).isNotNull();
		assertThat(lipputyyppi1.getLipputyyppiNimi()).contains("VIP");
	}
	
	@Test
	public void lipunluontiToimii() {
		Lipputyyppi lipputyyppi1 = new Lipputyyppi("VIP");
		Tapahtuma tapahtuma1 = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		Lippu lippu = new Lippu(lipputyyppi1, tapahtuma1, 120.00, "Vapaa");
		lippurepository.save(lippu);
		
		assertThat(lippu.getLippuId()).isNotNull();
		assertThat(lippu.gethinta()).isEqualTo( 120.00);
		assertThat(lippu.getlippuTyyppi()).isEqualTo(lipputyyppi1);
		assertThat(lippu.getTapahtuma()).isEqualTo(tapahtuma1);
	}
	
	@Test
	public void lipunTilaVapaa() {
		Lipputyyppi lipputyyppi = new Lipputyyppi("VIP");
		Tapahtuma tapahtuma = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		Lippu lippu = new Lippu(lipputyyppi, tapahtuma, 120.00);
		lippu.setTilaVapaa();
		lippurepository.save(lippu);
		
		assertThat(lippu.getTila()).contains("Vapaa");
	}
	
	@Test
	public void lipunTilaVarattu() {
		Lipputyyppi lipputyyppi = new Lipputyyppi("VIP");
		Tapahtuma tapahtuma = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		Lippu lippu = new Lippu(lipputyyppi, tapahtuma, 120.00, "Vapaa");
		lippu.setTilaVarattu();
		lippurepository.save(lippu);
		
		assertThat(lippu.getTila()).contains("Varattu");
	}
	
}
