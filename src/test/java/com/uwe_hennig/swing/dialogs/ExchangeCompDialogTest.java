/**
 * @(#)ExchangeCompDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.uwe_hennig.swing.widgets.DefaultDialog;
import com.uwe_hennig.swing.widgets.ExchangeComp;

/**
 * ExchangeCompDialogTest
 * 
 * @author Uwe Hennig
 */
public class ExchangeCompDialogTest {
    public static void main(String[] arg) {
        String[] str = { "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun" };
        String[] selStr = { "zwei", "vier", "acht" };
        DefaultDialog dlg = new DefaultDialog();
        ExchangeComp sp = new ExchangeComp("Test");
        sp.addData(str);
        sp.setSelection(selStr);
        dlg.setCenter(sp);
        dlg.pack();
        dlg.setVisible(true);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
