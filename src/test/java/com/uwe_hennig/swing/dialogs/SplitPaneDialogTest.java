/**
 * @(#)SplitPaneDialogTest.java
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
import com.uwe_hennig.swing.widgets.DefaultDialog;
import com.uwe_hennig.swing.widgets.SplitPane;
import com.uwe_hennig.swing.widgets.Tree;

/**
 * SplitPaneDialogTest
 * @author Uwe Hennig
 */
public class SplitPaneDialogTest {
    static public void main(String[] arg) {
        DefaultDialog dlg = new DefaultDialog();
        Browser br = new Browser();
        SplitPane sp = new SplitPane(new Tree(), br);
        dlg.setCenter(sp);
        TableModel dataModel = new AbstractTableModel() {
            private static final long   serialVersionUID    = 4031323861709211672L;

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
        sp.setRightComponent(br);
        // some buttons
        dlg.getButtonPanel().addButton(new JButton("eins"));
        dlg.getButtonPanel().addButton(new JButton("zwei"));
        dlg.getButtonPanel().addButton(new JButton("drei"));
        dlg.pack();
        dlg.setVisible(true);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
