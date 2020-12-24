/**
 * @(#)DefaultButtonPanelDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.uwe_hennig.swing.widgets.DefaultButtonPanel;

/**
 * DefaultButtonPanelDialogTest
 * @author Uwe Hennig
 */
public class DefaultButtonPanelDialogTest {
    public static void main(String[] arg) {
        JFrame frm = new JFrame("DefaultButtonPanel");
        DefaultButtonPanel tm = new DefaultButtonPanel();
        tm.addButton(new JButton("no default"));
        JButton[] buttons = tm.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            System.out.println("button[" + i + "]='" + buttons[i].getText()
                    + "'");
        }
        tm.addOkActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("worked");
            }
        });
        frm.getContentPane().add(tm);
        frm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frm.pack();
        frm.setVisible(true);
    }

}
