package org.abalazsik.abstraction.view.backend.domain;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * proxy class that lets you create custom methods on a list type
 *
 * @author ador
 */
public class AbstractionRows extends AbstractList<AbstractionRow> {

    private List<AbstractionRow> actualRows;

    public AbstractionRows() {
        actualRows = new ArrayList<>();
    }

    public AbstractionRows(List<AbstractionRow> actualRows) {
        this.actualRows = actualRows;
    }

    @Override
    public AbstractionRow get(int index) {
        return actualRows.get(index);
    }

    @Override
    public int size() {
        return actualRows.size();
    }

    public AbstractionRows copy() {
        return new AbstractionRows(List.copyOf(this.actualRows));
    }
    
    public int calculateValue() {
        int sum = 0;

        for (AbstractionRow row : actualRows) {
            sum += calculateValue(row);
        }

        return sum;
    }

    private int calculateValue(AbstractionRow row) {
        if (row.getRows().isEmpty()) {
            return row.getValue();
        }

        int sum = 0;

        for (AbstractionRow subRow : row.getRows()) {
            sum += calculateValue(subRow);
        }

        return sum;
    }

}
