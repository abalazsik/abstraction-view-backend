package org.abalazsik.abstraction.view.backend.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author ador
 */
public class AbstractionRow {

    private String title;
    private List<AbstractionRow> rows;
    private Integer value;

    public AbstractionRow() {
    }
    
    @JsonCreator
    public AbstractionRow(
            @JsonProperty("title") String title, 
            @JsonProperty("rows") List<AbstractionRow> rows, 
            @JsonProperty("value") Integer value) {
        this.title = title;
        this.rows = rows;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public List<AbstractionRow> getRows() {
        return rows;
    }

    public Integer getValue() {
        return value;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRows(List<AbstractionRow> rows) {
        this.rows = rows;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
