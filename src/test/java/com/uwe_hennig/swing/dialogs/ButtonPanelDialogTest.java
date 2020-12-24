/**
 * @(#)ButtonPanelDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.uwe_hennig.swing.widgets.ButtonPanel;

/**
 * ButtonPanelDialogTest
 * 
 * @author Uwe Hennig
 */
public class ButtonPanelDialogTest {
    public static void main(String[] arg) {
        JFrame frm = new JFrame("ButtonPanel");
        ButtonPanel bp = new ButtonPanel();
        frm.getContentPane().add(bp);
        frm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        bp.addButton(new JButton("eins"));
        bp.addButton(new JButton("zwei"));
        bp.addButton(new JButton("drei"));
        bp.addButton(new JButton("etwas laenger"));
        bp.setRigidArea(new Dimension(2, 1));
        bp.addButton(new JButton("1"));
        bp.addButton(new JButton("2"));
        bp.addButton(new JButton("3"));
        // bp.resizeButtons(new Dimension(200,20));
        frm.pack();
        frm.setVisible(true);
        System.out.println("done");
    }

}
