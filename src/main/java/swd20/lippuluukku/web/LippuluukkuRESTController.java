package swd20.lippuluukku.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import swd20.lippuluukku.domain.Lippu;
import swd20.lippuluukku.domain.LippuRepository;
import swd20.lippuluukku.domain.Lipputyyppi;
import swd20.lippuluukku.domain.LipputyyppiRepository;
import swd20.lippuluukku.domain.Tapahtuma;
import swd20.lippuluukku.domain.TapahtumaRepository;

@RestController // REST metodeille oma luokka, application.propertiesiin on määriteltynä urli /api/(metodin url)
public class LippuluukkuRESTController {
	@Autowired
	private TapahtumaRepository tapahtumarepository;
	
	@Autowired
	private LippuRepository lippurepository;
	
	@Autowired
	private LipputyyppiRepository lipputyyppirepository;
	
	@GetMapping(value="/liput") // Rest-metodi, jolla saadaan JSONina kaikki liput
		public @ResponseBody List<Lippu> lippuListaRest() {
		return (List<Lippu>) lippurepository.findAll();
	}
	
	@GetMapping(value="/tapahtumatt") // Kaikki tapahtumat - REST metodina
		public @ResponseBody List<Tapahtuma> tapahtumaListaRest() {
		return (List<Tapahtuma>) tapahtumarepository.findAll();
	}
	
	@GetMapping(value="/lipputyypit") // Kaikki lipputyypit - REST metodina
		public @ResponseBody List<Lipputyyppi> lipputyyppiListaRest() {
		return (List<Lipputyyppi>) lipputyyppirepository.findAll();
	}
	
	@GetMapping(value="/liput/{id}") // REST metodi, jolla saadaa haettuna yksi lippu id:n perusteella
		public @ResponseBody Optional<Lippu> findLippuRest(@PathVariable("id") Long lippuId) {
		return lippurepository.findById(lippuId);
	}
	
	@GetMapping(value="/tapahtumatt/{id}") // Yksi tapahtuma id:llä REST
		public @ResponseBody Optional<Tapahtuma> findTapahtumaRest(@PathVariable("id") Long tapahtumaId) {
		return tapahtumarepository.findById(tapahtumaId);
	}
	
	@GetMapping(value="/lipputyypit/{id}") // Yksi lipputyyppi id:llä REST
		public @ResponseBody Optional<Lipputyyppi> findLipputyyppiRest(@PathVariable("id") Long lipputyyppiId) {
		return lipputyyppirepository.findById(lipputyyppiId);
	}
	
}
