package swd20.lippuluukku.domain;

import java.util.List;
import java.util.function.IntPredicate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LippuRepository extends CrudRepository<Lippu, Long> {
	List<Lippu> findByLippuTyyppi(String lippuTyyppi);

	List<Lippu> findByTapahtuma(Tapahtuma tapahtuma);
	
	List<Lippu> findByTila(@Param("tila") String tila); // Rest käytäntö, jolla voidaan kutsua lippuja endpointilla

	List<Lippu> findByHinta(double hinta);
}