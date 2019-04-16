package swd20.lippuluukku;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import swd20.lippuluukku.web.LippuluukkuController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LippuluukkuApplicationTests {

	@Autowired
	private LippuluukkuController lippuluukkucontroller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(lippuluukkucontroller).isNotNull();
	}

}
