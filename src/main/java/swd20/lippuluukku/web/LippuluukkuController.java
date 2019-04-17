package swd20.lippuluukku.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;

import swd20.lippuluukku.domain.Kayttaja;
import swd20.lippuluukku.domain.KayttajaRepository;
import swd20.lippuluukku.domain.Lippu;
import swd20.lippuluukku.domain.LippuRepository;
import swd20.lippuluukku.domain.LipputyyppiRepository;
import swd20.lippuluukku.domain.Tapahtuma;
import swd20.lippuluukku.domain.TapahtumaRepository;

@Controller
public class LippuluukkuController {
	// Principal principal -> tietää käyttäjän
	@Autowired
	private TapahtumaRepository tapahtumarepository;
	
	@Autowired
	private LippuRepository lippurepository;
	
	@Autowired
	private LipputyyppiRepository lipputyyppirepository;
	
	@Autowired
	private KayttajaRepository kayttajarepository;
	 
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	 
	 @RequestMapping(value="/tapahtumalista") // kaikkien tapahtumien listan palauttava metodi
	 public String tapahtumaLista(Model model) {
		 model.addAttribute("tapahtumat", tapahtumarepository.findAll());
		 return "tapahtumalista";
	 }
	 
	 @GetMapping(value = "/lisaatapahtuma") // luo tyhjän tapahtuma-olion ja model siirtää sen sivulle täytettäväksi
	 @PreAuthorize("hasAuthority('TUOTTAJA')")
	    public String lisaaTapahtuma(Model model){
	    	model.addAttribute("tapahtuma", new Tapahtuma());
	    	return "lisaatapahtuma";
	    }
	 
	 @PostMapping(value = "/tallennatapahtuma") // validointia regexillä sisältävä tallennusmetodi, jonka kautta uusi tapahtuma tallennetaan tietokantaan
	    public String tallennaTapahtuma(@Valid Tapahtuma tapahtuma, BindingResult bindingResult, Model model){
		 int pvmerror = 0;
		 int aikaerror = 0;
		 if (!tapahtuma.getTapahtumaPvm().matches("\\d{2}[.]\\d{2}[.]\\d{4}")) {
			 pvmerror = 1;
			 model.addAttribute("pvmerror", pvmerror);
			 return "lisaatapahtuma";
		 } else if (!tapahtuma.getTapahtumaAika().matches("(\\d{2})[:](\\d{2})")) {
			 aikaerror = 1;
			 model.addAttribute("aikaerror", aikaerror);
			 return "lisaatapahtuma";
		 } else {
			 pvmerror = 0;
			 aikaerror = 0;
			 model.addAttribute("pvmerror", pvmerror);
			 model.addAttribute("aikaerror", aikaerror);
		 }
		 if (bindingResult.hasErrors()) {
	         return "lisaatapahtuma";
	     }
		 	tapahtumarepository.save(tapahtuma); // osaa tehdä niin insertin kuin updaten
	        return "redirect:tapahtumalista";
		    }
	 
	 @RequestMapping(value = "/muokkaatapahtumaa/{tapahtumaId}") // vanhan tapahtuman hakeminen tietokannasta ja tietojen tuominen sivulle muuutettavaksi.
	 @PreAuthorize("hasAuthority('TUOTTAJA')")
	    public String muokkaaTapahtumaa(@PathVariable("tapahtumaId") Long tapahtumaId, Model model) {
	    	model.addAttribute("tapahtuma1", tapahtumarepository.findById(tapahtumaId));
	        return "muokkaatapahtumaa";
	    }
	 
	 @PostMapping(value = "/tallennavanhatapahtuma") // tallennusmetodi muokatulle, ennestään tietokannasta löytyneelle tapahtumalle.
	 	public String tallennaVanhaTapahtuma(Tapahtuma tapahtuma) {
		 tapahtumarepository.save(tapahtuma);
		 return "redirect:/tapahtumalista";
	 }
	 
	    @RequestMapping(value = "/julkaisetapahtuma/{id}") // mikäli tapahtuma on ollut peruttuna, julkaistaan tapahtuma uudestaan Tapahtuma-luokkaan luodulla metodilla.
	    @PreAuthorize("hasAuthority('TUOTTAJA')")
	    public String julkaiseTapahtuma(@PathVariable("id") Long tapahtumaId, String status, Model model) {
	    	Tapahtuma tapahtuma = tapahtumarepository.findById(tapahtumaId).orElse(null);
	    	tapahtuma.setStatusJulkaistu();
	    	tapahtumarepository.save(tapahtuma);
	    	return "redirect:../muokkaatapahtumaa/{id}";
	    }
	    
	    @RequestMapping(value = "/perutapahtuma/{id}") // Mikäli tapahtuma halutaan perua (ei anneta tuottajan poistaa tapahtumia) haetaan tapahtuman id sekä status, tallennetaan ja palataan takaisin tapahtumalistalle.
	    @PreAuthorize("hasAuthority('TUOTTAJA')")
	    public String peruTapahtuma(@PathVariable("id") Long tapahtumaId, String status, Model model) {
	    	Tapahtuma tapahtuma = tapahtumarepository.findById(tapahtumaId).orElse(null);
	    	tapahtuma.setStatusPeruttu();
	    	tapahtumarepository.save(tapahtuma);
	    	return "redirect:../tapahtumalista";
	    }
	 
		@GetMapping(value = "/poistatapahtuma/{tapahtumaId}") // tapahtumaidllä päästään kiinni olioon, joka halutaan poistaa kannasta. VAIN Adminilla valtuudet.
		@PreAuthorize("hasAuthority('ADMIN')")
		public String poistaTapahtuma(@PathVariable("tapahtumaId") Long id, Model model) {
			tapahtumarepository.deleteById(id);
	        return "redirect:../tapahtumalista";
	    }
	 
	 @RequestMapping(value = "/lisaalippuja/{tapahtumaId}") // haetaan tapahtuma, luodaan tyhjä Lippu, sekä lipputyypit.
	 @PreAuthorize("hasAuthority('TUOTTAJA')")
	    public String lisaaLippuja(@PathVariable("tapahtumaId") Long tapahtumaId, Model model){
		 	Tapahtuma tapahtuma = tapahtumarepository.findById(tapahtumaId).orElse(null);
		 	Lippu lippu = new Lippu();
		 	lippu.setTapahtuma(tapahtuma);
		 	model.addAttribute("tapahtuma", tapahtuma);
		 	model.addAttribute("lippu", lippu); // annetaan yksi tyhjä olio
	        model.addAttribute("lipputyypit", lipputyyppirepository.findAll());
	    	return "lisaalippuja";
	    }
	 
	 @RequestMapping(value = "/poistalippuja/{tapahtumaId}") // Ei poisteta lippuja kannasta, vaan muutetaan niiden status peruutetuiksi.
	 @PreAuthorize("hasAuthority('TUOTTAJA')")
	 	public String poistaLippuja(@PathVariable("tapahtumaId") Long tapahtumaId) {
		 	Tapahtuma tapahtuma = tapahtumarepository.findById(tapahtumaId).orElse(null);
		 	List<Lippu> liput = lippurepository.findByTapahtuma(tapahtuma);
		 	for (Lippu lippu : liput) {
		 		lippu.setTila("Peruttu");
		 		lippurepository.save(lippu);
		 	}
		return "redirect:../tapahtumalista";
	 }
	 
	 @PostMapping(value = "/tallennalippuja") // saadaan lisaalippuja lomakkeelta, kuinka monta tällaista lippua halutaan tehdä ja tuotetaan niin monta tietokantaan.
	    public String tallennaLippuja(Lippu lippu){ // template syöttää name-attribuutin arvoista oliolle tiedot
		 int montako = lippu.getMontakoLippua();
		 lippu.setTilaVapaa();
		 	for (int i=0;i<=(montako-1);i++) {
		 		lippurepository.save(new Lippu(lippu.getlippuTyyppi(), lippu.getTapahtuma(), lippu.gethinta(), lippu.getTila())); // osaa tehdä niin insertin kuin updaten
					}
			return "redirect:tapahtumalista";
		}
	 
	 @RequestMapping(value= "/varaalippuja/{tapahtumaId}") // Käyttäjä näkee haetun tapahtuman vapaat liput ja voi varata itselleen niitä yhden kerrallaan.
		 public String varaaLippuja(@PathVariable("tapahtumaId") Long id, Model model, @AuthenticationPrincipal UserDetails currentUser) {
			 Kayttaja kayttaja = kayttajarepository.findByKayttajaNimi(currentUser.getUsername());
			 Tapahtuma tapahtuma = tapahtumarepository.findById(id).orElse(null);
			 model.addAttribute("kayttajaNimi", kayttaja.getKayttajaNimi());
			 model.addAttribute("liput", lippurepository.findByTapahtuma(tapahtuma));
			 model.addAttribute("tapahtuma", tapahtuma);
			 return "varaalippuja";
	 }
	 
	 @RequestMapping(value= "/varaaliput/{kayttajaNimi}/{lippuId}") // Submit-metodi käyttäjän lippujen varaamiseen
	    public String varaaLiput(@PathVariable("kayttajaNimi") String kayttajaNimi, @PathVariable("lippuId") Long lippuId){ // template syöttää name-attribuutin arvoista oliolle tiedot	
		 	Kayttaja kayttaja = kayttajarepository.findByKayttajaNimi(kayttajaNimi);
		 	Lippu lippu = lippurepository.findById(lippuId).orElse(null);
		 	kayttaja.varaaLiput(lippu);
		 	lippurepository.save(lippu);
		 	kayttajarepository.save(kayttaja);
			return "redirect:../../tapahtumalista";
	 }
	 
	@GetMapping(value="/omatliput") // ei täysin toimiva, omaksi ilokseni koittelen että minkälainen metodi pitäisi olla, että saisin luotua vielä sivut jolle käyttäjän varaamat liput näkyisi.
	 public String tapahtumaLista(@AuthenticationPrincipal UserDetails currentUser, Model model) {
		 Kayttaja kayttaja = kayttajarepository.findByKayttajaNimi(currentUser.getUsername());
		 List <Lippu> liput = kayttaja.getLiput();
		 model.addAttribute("liput", liput);
		 return "omatliput";
	 }
}
