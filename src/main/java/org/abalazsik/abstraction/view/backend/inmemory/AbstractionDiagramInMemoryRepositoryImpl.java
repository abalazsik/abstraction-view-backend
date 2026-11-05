package org.abalazsik.abstraction.view.backend.inmemory;

import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagram;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagramDetail;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagramRepository;
import org.abalazsik.abstraction.view.backend.domain.AbstractionRows;
import org.abalazsik.abstraction.view.backend.domain.CreateAbstractionDiagram;
import org.abalazsik.abstraction.view.backend.domain.UpdateAbstractionDiagram;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ador
 */
@Service
public class AbstractionDiagramInMemoryRepositoryImpl implements AbstractionDiagramRepository {

    private AtomicLong idSeq = new AtomicLong(0);
    private ConcurrentHashMap<Long, AbstractionDiagramInMemoryEntity> storage = new ConcurrentHashMap<>();

    @Override
    public Mono<Long> createDiagram(CreateAbstractionDiagram.CreateAbstractionDiagramWithDate data) {
        AbstractionDiagramInMemoryEntity abstractionDiagram = new AbstractionDiagramInMemoryEntity(idSeq.incrementAndGet(),
                data.getTitle(), new AbstractionRows(), data.getCreatedAt(), data.getCreatedAt());

        storage.put(abstractionDiagram.getId(), abstractionDiagram);

        return Mono.just(abstractionDiagram.getId());
    }

    @Override
    public Mono<AbstractionDiagram> updateDiagram(UpdateAbstractionDiagram.UpdateAbstractionDiagramWithDate data) {
        return Mono.justOrEmpty(storage.get(data.getId())).map(diagram -> {
            diagram.setRows(data.getRows().copy());
            diagram.setTitle(data.getTitle());
            diagram.setUpdatedAt(data.getUpdatedAt());
            
            return diagram;
        });
    }

    @Override
    public Flux<AbstractionDiagramDetail> list() {
        return Flux.fromStream(storage.values().stream().sorted(Comparator.comparing(AbstractionDiagramInMemoryEntity::getUpdatedAt)))
                .map((diagram -> new AbstractionDiagramDetail(
                diagram.getId(), diagram.getTitle(),
                diagram.getCreatedAt(), diagram.getUpdatedAt()
        )));
    }

    @Override
    public Mono<AbstractionDiagram> getById(Long id) {
        return Mono.justOrEmpty(storage.get(id));
    }

}
