package org.abalazsik.abstraction.view.backend.controller;

import java.util.stream.Collectors;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagram;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagramDetail;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagramService;
import org.abalazsik.abstraction.view.backend.domain.AbstractionRow;
import org.abalazsik.abstraction.view.backend.domain.AbstractionRows;
import org.abalazsik.abstraction.view.backend.domain.CreateAbstractionDiagram;
import org.abalazsik.abstraction.view.backend.domain.UpdateAbstractionDiagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ador
 */
@RestController
@RequestMapping("/abstraction-diagram")
public class AbstractionDiagramController {

    private AbstractionDiagramService abstractionDiagramService;

    @Autowired
    public AbstractionDiagramController(AbstractionDiagramService abstractionDiagramService) {
        this.abstractionDiagramService = abstractionDiagramService;
    }

    @PostMapping("/")
    public Mono<Long> createDiagram(@RequestBody CreateAbstractionDiagramDto dto) {
        return abstractionDiagramService.createDiagram(new CreateAbstractionDiagram(dto.getTitle()));
    }

    @PutMapping("/")
    public Mono<AbstractionDiagram> updateDiagram(@RequestBody UpdateAbstractionDiagramDto dto) {
        return abstractionDiagramService.updateDiagram(
                new UpdateAbstractionDiagram(
                        dto.getId(),
                        dto.getTitle(),
                        new AbstractionRows(
                                dto.getRows().stream().map(this::mapRows).collect(Collectors.toList()))
                )
        );

    }

    @GetMapping("/list")
    public Flux<AbstractionDiagramDetail> list() {
        return abstractionDiagramService.list();
    }

    @GetMapping("/byId")
    public Mono<AbstractionDiagram> getById(Long id) {
        return abstractionDiagramService.getById(id);
    }

    private AbstractionRow mapRows(AbstractionRowDto dto) {
        return new AbstractionRow(dto.getTitle(), 
                dto.getRows().stream().map(this::mapRows).collect(Collectors.toList()), 
                dto.getValue());
    }

}
