package org.abalazsik.abstraction.view.backend.domain;

import java.time.LocalDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ador
 */
public class AbstractionDiagramService {

    private AbstractionDiagramRepository repository;

    public AbstractionDiagramService(AbstractionDiagramRepository repository) {
        this.repository = repository;
    }

    public Mono<Long> createDiagram(CreateAbstractionDiagram data) {
        return repository.createDiagram(data.withDate(LocalDateTime.now()));
    }

    public Mono<AbstractionDiagram> updateDiagram(UpdateAbstractionDiagram data) {
        return repository.updateDiagram(data.withDate(LocalDateTime.now()));
    }

    public Flux<AbstractionDiagramDetail> list() {
        return repository.list();
    }

    public Mono<AbstractionDiagram> getById(Long id) {
        return repository.getById(id);
    }
}
