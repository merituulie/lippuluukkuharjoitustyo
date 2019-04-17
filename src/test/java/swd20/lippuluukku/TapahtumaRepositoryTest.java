package swd20.lippuluukku;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import swd20.lippuluukku.domain.Tapahtuma;
import swd20.lippuluukku.domain.TapahtumaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TapahtumaRepositoryTest {
	
	@Autowired
	private TapahtumaRepository tapahtumarepository;
	
	@Test
	public void tapahtumarepositoryToimii() {
		Tapahtuma tapahtuma1 = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		tapahtumarepository.save(tapahtuma1);
		
		assertThat(tapahtumarepository).isNotNull();
	}
	
	@Test
	public void tapahtumarepositoryLoytaaTapahtuman() {
		Tapahtuma tapahtuma1 = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
		tapahtumarepository.save(tapahtuma1);
		
		assertThat(tapahtumarepository.findByTapahtumaNimi("Ruisrock").contains(tapahtuma1));
	}
	
}
