package swd20.lippuluukku.domain;

import org.springframework.data.repository.CrudRepository;

public interface KayttajaRepository extends CrudRepository<Kayttaja, Long> {
	Kayttaja findByKayttajaNimi(String kayttajaNimi);
}
