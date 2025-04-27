package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PersonDataTable extends AbstractTableModel {
    ArrayList<Scores> scoresArrayList;
    public PersonDataTable() {
        scoresArrayList = new ArrayList<>();
    }
    public void addScore(Scores scores) {
        scoresArrayList.add(scores);
        this.fireTableDataChanged();
    }

    public void removeScore(int index) {
        scoresArrayList.remove(index);
        this.fireTableDataChanged();
    }
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Raw Scores";
            case 1:
                return "Total Scores";
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return scoresArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Scores scores = scoresArrayList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return scores.getRawScore();
            case 1:
                return scores.getTotalScore();
            default:
                return null;
        }
    }
}
