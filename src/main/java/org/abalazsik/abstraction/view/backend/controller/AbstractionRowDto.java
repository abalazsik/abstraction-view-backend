package org.abalazsik.abstraction.view.backend.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;

/**
 *
 * @author ador
 */
public class AbstractionRowDto {

    private String title;
    private List<AbstractionRowDto> rows;
    private Integer value;

    public AbstractionRowDto() {
    
    }
    
    @JsonCreator
    public AbstractionRowDto(String title, List<AbstractionRowDto> rows, Integer value) {
        this.title = title;
        this.rows = rows;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public List<AbstractionRowDto> getRows() {
        return rows;
    }

    public Integer getValue() {
        return value;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRows(List<AbstractionRowDto> rows) {
        this.rows = rows;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
