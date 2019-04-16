package swd20.lippuluukku.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LipputyyppiRepository extends CrudRepository<Lipputyyppi, Long> {
	List<Lipputyyppi> findByLipputyyppiNimi(String lipputyyppiNimi);
}
