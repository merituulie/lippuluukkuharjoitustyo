package swd20.lippuluukku;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import swd20.lippuluukku.domain.Kayttaja;
import swd20.lippuluukku.domain.KayttajaRepository;
import swd20.lippuluukku.domain.Lippu;
import swd20.lippuluukku.domain.LippuRepository;
import swd20.lippuluukku.domain.Lipputyyppi;
import swd20.lippuluukku.domain.LipputyyppiRepository;
import swd20.lippuluukku.domain.Tapahtuma;
import swd20.lippuluukku.domain.TapahtumaRepository;

@SpringBootApplication
public class LippuluukkuApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LippuluukkuApplication.class, args);
	}
	
	@Bean // demodataa järjestelmään
	public CommandLineRunner lippuluukkuDemo(LippuRepository lippurepository, LipputyyppiRepository lipputyyppirepository, TapahtumaRepository tapahtumarepository, KayttajaRepository kayttajarepository) {
		return (args) -> {
			
			Lipputyyppi lipputyyppi1 = new Lipputyyppi("VIP");
			Lipputyyppi lipputyyppi2 = new Lipputyyppi("Premium");
			Lipputyyppi lipputyyppi3 = new Lipputyyppi("Basic");
			lipputyyppirepository.save(lipputyyppi1);
			lipputyyppirepository.save(lipputyyppi2);
			lipputyyppirepository.save(lipputyyppi3);
			
			Tapahtuma tapahtuma1 = new Tapahtuma("Ruisrock", "30.6.2019", "18:00");
			Tapahtuma tapahtuma2 = new Tapahtuma("Inna", "15.7.2019", "18:30");
			Tapahtuma tapahtuma3 = new Tapahtuma("Weekend Festival", "1.8.2019", "19:00");
			Tapahtuma tapahtuma4 = new Tapahtuma("The Circus Presents: Björk", "30.6.2019", "18:00");
			Tapahtuma tapahtuma5 = new Tapahtuma("AllIn: Urban Music Festival", "20.9.2019", "17:30");
			Tapahtuma tapahtuma6 = new Tapahtuma("Loop Presents: Twerk SM", "20.5.2019", "21:00");
			
			tapahtumarepository.save(tapahtuma1);
			tapahtumarepository.save(tapahtuma2);
			tapahtumarepository.save(tapahtuma3);
			tapahtumarepository.save(tapahtuma4);
			tapahtumarepository.save(tapahtuma5);
			tapahtumarepository.save(tapahtuma6);
			
			lippurepository.save(new Lippu(lipputyyppi1, tapahtuma1, 120.00, "Vapaa"));
			lippurepository.save(new Lippu(lipputyyppi2, tapahtuma2, 90.00, "Vapaa"));
			lippurepository.save(new Lippu(lipputyyppi2, tapahtuma2, 55.00, "Vapaa"));
			lippurepository.save(new Lippu(lipputyyppi1, tapahtuma2, 80.00, "Vapaa"));
			lippurepository.save(new Lippu(lipputyyppi3, tapahtuma2, 100.00, "Vapaa"));
			lippurepository.save(new Lippu(lipputyyppi3, tapahtuma2, 65.00, "Vapaa"));
			
			Kayttaja asiakas = new Kayttaja("asiakas", "$2a$10$IISyvTaYVoYM6GVkJCo8buCetGtrsCKh/nXytKeR93u6BLiGOpCqG", "user2@email.com", "ASIAKAS");
			Kayttaja tuottaja = new Kayttaja("tuottaja", "$2a$10$IISyvTaYVoYM6GVkJCo8buCetGtrsCKh/nXytKeR93u6BLiGOpCqG", "user2@email.com", "TUOTTAJA");
			Kayttaja admin = new Kayttaja("admin", "$2a$10$IISyvTaYVoYM6GVkJCo8buCetGtrsCKh/nXytKeR93u6BLiGOpCqG", "user3@gmail.com", "ADMIN");
			kayttajarepository.save(asiakas); // järjestelmässä toistaiseksi toissijainen käyttäjä, asiakas, joka voisi varata itselleen lippuja tapahtumista
			kayttajarepository.save(tuottaja); // järjestelmän pääkäyttäjä, tapahtumien julkaisija ja tietojen ylläpitäjä.
			kayttajarepository.save(admin);
			
			
		};
	}

}
