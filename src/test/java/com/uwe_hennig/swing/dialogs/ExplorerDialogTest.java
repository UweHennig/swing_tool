/**
 * @(#)ExplorerDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.TreePath;

import com.uwe_hennig.swing.widgets.Browser;
import com.uwe_hennig.swing.widgets.DefaultDialog;
import com.uwe_hennig.swing.widgets.Explorer;
import com.uwe_hennig.swing.widgets.ExplorerBrowserFactory;
import com.uwe_hennig.swing.widgets.Tree;

/**
 * ExplorerDialogTest
 * @author Uwe Hennig
 */
public class ExplorerDialogTest {
    static public void main(String[] arg) {
        DefaultDialog dlg = new DefaultDialog();
        Explorer tr = new Explorer(new Tree(), new ExplorerBrowserFactory() {
            public Browser create(TreePath tp) {
                Browser br = new Browser();
                TableModel dataModel = new AbstractTableModel() {
                    private static final long   serialVersionUID    = 4177695510701966336L;

                    public int getColumnCount() {
                        return 5;
                    }

                    public int getRowCount() {
                        return 5;
                    }

                    public Object getValueAt(int row, int col) {
                        return new Integer(row * col);
                    }
                };
                br.getTable().setModel(dataModel);
                return br;
            }
        });
        dlg.setCenter(tr);
        // add some data
        dlg.pack();
        dlg.setVisible(true);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
