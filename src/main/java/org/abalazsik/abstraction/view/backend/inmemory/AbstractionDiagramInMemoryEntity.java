package org.abalazsik.abstraction.view.backend.inmemory;

import java.time.LocalDateTime;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagram;
import org.abalazsik.abstraction.view.backend.domain.AbstractionRows;

/**
 * Just expose the setters LMAO
 * @author ador
 */
public class AbstractionDiagramInMemoryEntity extends AbstractionDiagram {

    public AbstractionDiagramInMemoryEntity(Long id, String title, AbstractionRows rows, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, title, rows, createdAt, updatedAt);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRows(AbstractionRows rows) {
        this.rows = rows;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
