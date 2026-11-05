package org.abalazsik.abstraction.view.backend.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author ador
 */
public class UpdateAbstractionDiagram {

    private final Long id;
    private final String title;
    private final AbstractionRows rows;

    public UpdateAbstractionDiagram(Long id, String title, AbstractionRows rows) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title should be non empty!");
        }

        this.id = Objects.requireNonNull(id, "id should be not null");
        this.title = title;
        this.rows = rows;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AbstractionRows getRows() {
        return rows;
    }

    public UpdateAbstractionDiagramWithDate withDate(LocalDateTime updatedAt) {
        return new UpdateAbstractionDiagramWithDate(this, updatedAt);
    }

    public static class UpdateAbstractionDiagramWithDate extends UpdateAbstractionDiagram {

        private final LocalDateTime updatedAt;

        private UpdateAbstractionDiagramWithDate(UpdateAbstractionDiagram other, LocalDateTime updatedAt) {
            super(other.id, other.title, other.rows);
            this.updatedAt = updatedAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

    }

}
