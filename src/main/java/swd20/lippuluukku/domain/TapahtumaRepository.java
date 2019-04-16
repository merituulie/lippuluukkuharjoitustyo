package swd20.lippuluukku.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TapahtumaRepository extends CrudRepository<Tapahtuma, Long> {
	List<Tapahtuma> findByTapahtumaId(Long tapahtumaId);

	List<Tapahtuma> findByTapahtumaNimi(String tapahtumaNimi);
	
}
