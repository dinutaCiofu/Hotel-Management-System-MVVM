package org.example.view.observer;

import javax.swing.table.DefaultTableModel;

public interface TableModelObserver {
    void onTableModelChanged(DefaultTableModel model);
}
