/**
 * @(#)Dialog.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Dialog stellt einen Standarddialog dar.
 * 
 * @author Uwe Hennig
 */
public class Dialog extends JFrame {
    private static final long serialVersionUID = 5023225261408450327L;
    private Composite         center           = null;
    private ButtonPanel       buttonPanel      = new ButtonPanel();

    public Dialog() {
        _init();
    }

    public Dialog(String s) {
        super(s);
        _init();
    }

    /** setzt den Dialoginhalt. */
    public void setCenter(Composite comp) {
        getContentPane().remove(comp);
        getContentPane().add(comp, BorderLayout.CENTER);
        invalidate();
    }

    public Composite getCenter() {
        return center;
    }

    /** setzen der unteren Buttonleiste */
    public void setButtonPanel(ButtonPanel buttonPanel) {
        // alten Panel l�schen und neuen nach SOUTH einf�gen.
        getContentPane().remove(this.buttonPanel);
        this.buttonPanel = buttonPanel;
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * Interne Hilfunktion _init. Mit _init werden die Defaulteinstellung dieser
     * Klasse vorgenommen.
     */
    private void _init() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

}
