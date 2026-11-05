package org.abalazsik.abstraction.view.backend.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ador
 */
public interface AbstractionDiagramRepository {

    public Mono<Long> createDiagram(CreateAbstractionDiagram.CreateAbstractionDiagramWithDate data);

    public Mono<AbstractionDiagram> updateDiagram(UpdateAbstractionDiagram.UpdateAbstractionDiagramWithDate data);

    public Flux<AbstractionDiagramDetail> list();

    public Mono<AbstractionDiagram> getById(Long id);

}
