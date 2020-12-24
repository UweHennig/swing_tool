/**
 * @(#)KeyValueEditorDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.uwe_hennig.swing.widgets.DefaultDialog;
import com.uwe_hennig.swing.widgets.KeyValueEditor;
import com.uwe_hennig.swing.widgets.KeyValueModel;

/**
 * KeyValueEditorDialogTest
 * @author Uwe Hennig
 */
public class KeyValueEditorDialogTest {
    public static void main(String[] arg) {
        DefaultDialog dlg = new DefaultDialog("KeyValueEditor");
        KeyValueModel model = new KeyValueModel("Key", "Value");
        model.add("eins", new Integer(1));
        model.add("zwei", new Integer(2));
        model.add("drei", new Integer(3));
        model.add("w/f", new Boolean(true));
        model.add("str", "Test");
        model.remove("drei");
        model.add("vier", new Integer(4));
        model.remove("eins");
        KeyValueEditor kve = new KeyValueEditor(model);
        dlg.setCenter(kve);
        dlg.pack();
        dlg.setVisible(true);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
    }

}
