package org.abalazsik.abstraction.view.backend.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.abalazsik.abstraction.view.backend.domain.*;
import java.util.Objects;

/**
 *
 * @author ador
 */
public class UpdateAbstractionDiagramDto {

    private Long id;
    private String title;
    private List<AbstractionRowDto> rows;
    
    @JsonCreator
    public UpdateAbstractionDiagramDto(Long id, String title, List<AbstractionRowDto> rows) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title should be non empty!");
        }

        this.id = Objects.requireNonNull(id, "id should be not null");
        this.title = title;
        this.rows = Objects.requireNonNull(rows, "rows should be not null");
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<AbstractionRowDto> getRows() {
        return rows;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRows(List<AbstractionRowDto> rows) {
        this.rows = rows;
    }

}
