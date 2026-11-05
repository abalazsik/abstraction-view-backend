package org.abalazsik.abstraction.view.backend.domain;

import java.time.LocalDateTime;

/**
 *
 * @author ador
 */
public class AbstractionDiagram {

    protected Long id;
    protected String title;
    protected AbstractionRows rows;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public AbstractionDiagram(Long id, String title, AbstractionRows rows, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.rows = rows;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
