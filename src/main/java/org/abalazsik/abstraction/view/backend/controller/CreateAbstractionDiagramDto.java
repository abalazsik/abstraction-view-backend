package org.abalazsik.abstraction.view.backend.controller;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 *
 * @author ador
 */
public class CreateAbstractionDiagramDto {

    private String title;

    public CreateAbstractionDiagramDto() {
    }
    
    @JsonCreator
    public CreateAbstractionDiagramDto(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title should be non empty!");
        }

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
