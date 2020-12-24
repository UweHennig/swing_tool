/**
 * @(#)SampleDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.UUID;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.uwe_hennig.swing.widgets.Browser;
import com.uwe_hennig.swing.widgets.BrowserColumn;
import com.uwe_hennig.swing.widgets.DefaultDialog;
import com.uwe_hennig.swing.widgets.TabbedPane;

/**
 * SampleDialogTest
 * 
 * @author Uwe Hennig
 */
public class SampleDialogTest {
    private static Random rand = new Random(System.currentTimeMillis());
    
    public static void main(String... args) {
        DefaultDialog dlg = new DefaultDialog();
        TabbedPane tp = new TabbedPane();
        dlg.setCenter(tp);
        // add some data
        Browser br = new Browser();
        final String [] columnNames = {"Machine Ids", "Amount data", "CPU usage"};
        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() {
                return 3;
            }
            
            public int getRowCount() {
                return 10;
            }
            
            public String getColumnName(int column) {
                return columnNames[column];
            }

            public Object getValueAt(int row, int col) {
                if (col == 0) {
                    return UUID.randomUUID().toString();
                } if (col == 1) {
                    return rand.nextInt(10000);
                }
                else {
                    return String.format("%1.1f%%", rand.nextDouble()*100.0);
                }
                
            }
        };
        br.getTable().setModel(dataModel);
        tp.addTab("Machines", br);
        
        dlg.pack();
        dlg.setVisible(true);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
