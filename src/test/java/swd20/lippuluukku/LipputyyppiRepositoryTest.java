package swd20.lippuluukku;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import swd20.lippuluukku.domain.Lipputyyppi;
import swd20.lippuluukku.domain.LipputyyppiRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LipputyyppiRepositoryTest {
	
	@Autowired
	private LipputyyppiRepository lipputyyppirepository;
	
	@Test
	public void lipputyyppirepositoryToimii() {
		Lipputyyppi lipputyyppi1 = new Lipputyyppi("VIP");
		lipputyyppirepository.save(lipputyyppi1);
		
		assertThat(lipputyyppirepository).isNotNull();
	}
	
	@Test
	public void lipputyyppirepositoryLoytaaNimen() {
		Lipputyyppi lipputyyppi1 = new Lipputyyppi("VIP");
		lipputyyppirepository.save(lipputyyppi1);
		
		assertThat(lipputyyppirepository.findByLipputyyppiNimi("VIP")).contains(lipputyyppi1);
	}
	
}
