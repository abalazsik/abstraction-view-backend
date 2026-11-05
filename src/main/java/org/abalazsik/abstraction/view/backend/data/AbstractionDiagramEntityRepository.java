package org.abalazsik.abstraction.view.backend.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ador
 */
@Repository
public interface AbstractionDiagramEntityRepository extends ReactiveCrudRepository<AbstractionDiagramEntity, Long> {

}
