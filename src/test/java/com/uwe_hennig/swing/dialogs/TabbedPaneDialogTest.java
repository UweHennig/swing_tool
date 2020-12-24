/**
 * @(#)TabbedPaneDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.uwe_hennig.swing.widgets.Browser;
import com.uwe_hennig.swing.widgets.Composite;
import com.uwe_hennig.swing.widgets.DefaultDialog;
import com.uwe_hennig.swing.widgets.TabbedPane;

/**
 * TabbedPaneDialogTest
 * @author Uwe Hennig
 */
public class TabbedPaneDialogTest {
    static public void main(String[] arg) {
        DefaultDialog dlg = new DefaultDialog();
        TabbedPane tp = new TabbedPane();
        dlg.setCenter(tp);
        // add some data
        Browser br = new Browser();
        TableModel dataModel = new AbstractTableModel() {
            private static final long   serialVersionUID    = -7850649585956736958L;

            public int getColumnCount() {
                return 10;
            }

            public int getRowCount() {
                return 10;
            }

            public Object getValueAt(int row, int col) {
                return new Integer(row * col);
            }
        };
        br.getTable().setModel(dataModel);
        tp.addTab("eins", br);
        tp.addTab("zwei", new Composite());
        // some buttons
        dlg.getButtonPanel().addButton(new JButton("Test"));
        dlg.pack();
        dlg.setVisible(true);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
