package org.abalazsik.abstraction.view.backend.domain;

import java.time.LocalDateTime;

/**
 *
 * @author ador
 */
public class CreateAbstractionDiagram {

    private final String title;

    public CreateAbstractionDiagram(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title should be non empty!");
        }

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public CreateAbstractionDiagramWithDate withDate(LocalDateTime updatedAt) {
        return new CreateAbstractionDiagramWithDate(this, updatedAt);
    }

    public static class CreateAbstractionDiagramWithDate extends CreateAbstractionDiagram {

        private final LocalDateTime createdAt;

        private CreateAbstractionDiagramWithDate(CreateAbstractionDiagram other, LocalDateTime createdAt) {
            super(other.title);
            this.createdAt = createdAt;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
        
    }

}
