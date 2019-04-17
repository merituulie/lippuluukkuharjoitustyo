package swd20.lippuluukku;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import swd20.lippuluukku.domain.Kayttaja;
import swd20.lippuluukku.domain.KayttajaRepository;
import swd20.lippuluukku.domain.Lippu;
import swd20.lippuluukku.domain.LippuRepository;
import swd20.lippuluukku.domain.Lipputyyppi;
import swd20.lippuluukku.domain.LipputyyppiRepository;
import swd20.lippuluukku.domain.Tapahtuma;
import swd20.lippuluukku.domain.TapahtumaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LippuRepositoryTest {
	
	@Autowired
	private LippuRepository lippurepository;
	
	@Test
	public void lippurepositoryToimii() {
		Lipputyyppi lipputyyppi1 = new Lipputyyppi("VIP");
		Tapahtuma tapahtuma1 = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		lippurepository.save(new Lippu(lipputyyppi1, tapahtuma1, 120.00, "Vapaa"));
		
		assertThat(lippurepository).isNotNull();
	}
	
	@Test
	public void lippurepositoryLoytaaLipun() {
		Lipputyyppi lipputyyppi1 = new Lipputyyppi("VIP");
		Tapahtuma tapahtuma1 = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		Lippu lippu = new Lippu(lipputyyppi1, tapahtuma1, 120.00, "Vapaa");
		lippurepository.save(lippu);
		
		assertThat(lippurepository.findByHinta(120.00)).contains(lippu);
	}
	
	@Test
	public void lippurepositoryLoytaaLipunTapahtumanJaLipunYhteyden() {
		Lipputyyppi lipputyyppi1 = new Lipputyyppi("VIP");
		Tapahtuma tapahtuma1 = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		Lippu lippu = new Lippu(lipputyyppi1, tapahtuma1, 120.00, "Vapaa");
		lippurepository.save(lippu);
		 
		assertThat(lippurepository.findByTapahtuma(tapahtuma1)).isNotNull();
	}
}
