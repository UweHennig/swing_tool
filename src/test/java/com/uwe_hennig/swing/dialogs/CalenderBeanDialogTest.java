/**
 * @(#)CalenderBeanDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import com.uwe_hennig.swing.widgets.CalendarBean;

/**
 * CalenderBeanDialogTest
 * @author Uwe Hennig
 */
public class CalenderBeanDialogTest {
    public static void main(String[] arg) {
        javax.swing.JDialog dlg = new javax.swing.JDialog();
        dlg.getContentPane().add(new CalendarBean(Calendar.getInstance()));
        // dlg.setCenter();
        dlg.pack();
        dlg.setVisible(true);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
