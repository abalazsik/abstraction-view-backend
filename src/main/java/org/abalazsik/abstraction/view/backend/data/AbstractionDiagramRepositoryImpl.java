package org.abalazsik.abstraction.view.backend.data;

import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagram;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagramDetail;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagramRepository;
import org.abalazsik.abstraction.view.backend.domain.AbstractionRows;
import org.abalazsik.abstraction.view.backend.domain.CreateAbstractionDiagram;
import org.abalazsik.abstraction.view.backend.domain.UpdateAbstractionDiagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ador
 */
@Service
public class AbstractionDiagramRepositoryImpl implements AbstractionDiagramRepository {

    private AbstractionDiagramEntityRepository repository;

    @Autowired
    public AbstractionDiagramRepositoryImpl(AbstractionDiagramEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Long> createDiagram(CreateAbstractionDiagram.CreateAbstractionDiagramWithDate data) {
        return repository.save(new AbstractionDiagramEntity(
                data.getTitle(),
                new AbstractionRows(),
                data.getCreatedAt(),
                data.getCreatedAt()
        )).map(AbstractionDiagramEntity::getId);
    }

    @Override
    public Mono<AbstractionDiagram> updateDiagram(UpdateAbstractionDiagram.UpdateAbstractionDiagramWithDate data) {
        return repository.findById(data.getId()).flatMap(entity -> {
            entity.setTitle(data.getTitle());
            entity.setRows(data.getRows());
            entity.setUpdatedAt(data.getUpdatedAt());
            return repository.save(entity).map(updatedEntity -> new AbstractionDiagram(updatedEntity.getId(),
                    updatedEntity.getTitle(),
                    updatedEntity.getRows(),
                    updatedEntity.getCreatedAt(),
                    updatedEntity.getUpdatedAt()));
        });
    }

    @Override
    public Flux<AbstractionDiagramDetail> list() {
        return repository.findAll().map(entity
                -> new AbstractionDiagramDetail(entity.getId(),
                        entity.getTitle(), entity.getCreatedAt(), entity.getUpdatedAt()));
    }

    @Override
    public Mono<AbstractionDiagram> getById(Long id) {
        return repository.findById(id).map(
                entity -> new AbstractionDiagram(entity.getId(),
                        entity.getTitle(),
                        entity.getRows(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt()));

    }

}
