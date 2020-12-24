/**
 * @(#)DefaultDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.uwe_hennig.swing.widgets.DefaultDialog;

/**
 * DefaultDialogTest
 * @author Uwe Hennig
 */
public class DefaultDialogTest {
    static public void main(String[] arg) {
        DefaultDialog dlg = new DefaultDialog();
        dlg.pack();
        dlg.setVisible(true);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
