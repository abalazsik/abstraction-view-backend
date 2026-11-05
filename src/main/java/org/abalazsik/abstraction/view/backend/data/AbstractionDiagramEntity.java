package org.abalazsik.abstraction.view.backend.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.abalazsik.abstraction.view.backend.domain.AbstractionRows;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author ador
 */
@Table("abstraction_diagram")
public class AbstractionDiagramEntity implements Serializable {

    @Id
    @Column("id")
    private Long id;

    @Column("title")
    private String title;

    @Column("rows")
    private AbstractionRows rows;

    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("updated_at")
    private LocalDateTime updatedAt;

    public AbstractionDiagramEntity() {
    }

    public AbstractionDiagramEntity(String title, AbstractionRows rows, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.rows = rows;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AbstractionRows getRows() {
        return rows;
    }

    public void setRows(AbstractionRows rows) {
        this.rows = rows;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
