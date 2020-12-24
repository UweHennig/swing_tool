/**
 * @(#)TreeDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import com.uwe_hennig.swing.widgets.DefaultDialog;
import com.uwe_hennig.swing.widgets.Tree;

/**
 * TreeDialogTest
 * @author Uwe Hennig
 */
public class TreeDialogTest {
    static public void main(String[] arg) {
        DefaultDialog dlg = new DefaultDialog();
        Tree tr = new Tree();
        dlg.setCenter(tr);
        // add some data
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
