/**
 * @(#)KeyValueEditor.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * KeyValueEditor
 * 
 * @author Uwe Hennig
 */
public class KeyValueEditor extends Composite {
    private static final long serialVersionUID  = 8003720830747641660L;
    private KeyValueModel     defaultTableModel = new KeyValueModel("Key", "Value");
    private JTable            table             = new JTable(defaultTableModel);
    private JScrollPane       scrollPane;
    private JPopupMenu        popupMenu         = new JPopupMenu();

    public KeyValueEditor() {
        _init();
    }

    public KeyValueEditor(KeyValueModel model) {
        table.setModel(model);
        _init();
    }

    public KeyValueModel getModel() {
        return (KeyValueModel) table.getModel();
    }

    private void _init() {
        setLayout(new java.awt.BorderLayout());
        setBackground(java.awt.Color.white);
        // Scroller
        scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Table defaults
        table.sizeColumnsToFit(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setShowGrid(true);
        TableColumnModel tcm0 = table.getColumnModel();
        TableColumn tc0 = tcm0.getColumn(0);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setBackground(java.awt.Color.lightGray);
        tc0.setCellRenderer(tcr);
        // Add elements
        scrollPane.getViewport().add(table);
        add(scrollPane);
        initPopupMenu();
        table.add(popupMenu);
    }

    protected void initPopupMenu() {
        JMenuItem mi = new JMenuItem("");
        popupMenu.add(mi);
    }
}
