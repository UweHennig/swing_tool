/**
 * @(#)Utils.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Uwe Hennig
 */
public class Utils {
    /** Hilfmethode, die ein Editfeld mit einem Label erzeugt. */
    public static Composite createLine(JComponent left, JComponent right) {
        return createLine(left, right, null);
    } // end createLine(JComponent left, JComponent right)

    public static Composite createLine(JComponent left, JComponent center, JComponent right) {
        Composite pl = new Composite();
        // Grössen Anpassung
        center.setMaximumSize(new Dimension(200, 25));
        pl.setLayout(new BoxLayout(pl, BoxLayout.X_AXIS));
        pl.add(Box.createRigidArea(new Dimension(15, 5)));
        pl.add(left);
        pl.add(Box.createRigidArea(new Dimension(5, 5)));
        pl.add(center);
        pl.add(Box.createRigidArea(new Dimension(5, 5)));
        if (right == null)
            right = new JLabel();
        pl.add(right);
        pl.add(Box.createRigidArea(new Dimension(5, 5)));
        JPanel rubber = new JPanel();
        rubber.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

        pl.add(rubber);
        return pl;
    }

    /** Hilfmethode, die Comboboxen füllt. */
    public static void fillCombo(JComboBox box, Vector glist, Object selected) {
        box.removeAllItems();
        if (glist == null || glist.size() <= 0)
            return;
        Enumeration el = glist.elements();
        while (el.hasMoreElements()) {
            box.addItem(el.nextElement());
        }
        if (selected != null)
            box.setSelectedItem(selected);
    }

    /** Hilfmethode, die selektieres Element einer Combobox ermittelt. */
    public static Object getComboSelect(JComboBox box) {
        Object[] objs = box.getSelectedObjects();
        if (objs.length >= 1)
            return objs[0];
        else
            return null;
    }

    /** Gridbag helper */
    public static void addGridBagConstraints(Composite gbComp, Component c, GridBagConstraints gbc, int x, int y, int w,
            int h) {
        gbc.gridx = x; // Horiz Position
        gbc.gridy = y; // Vertikal Position
        gbc.gridwidth = w; // Breite in Grideinheiten
        gbc.gridheight = h; // H�he in Grideinheiten
        gbComp.add(c, gbc); // Add Component with GB-Constraints to Component
                            // gbComp
    }

    public static String dateToString(Date value) {
        return DateFormat.getDateInstance(DateFormat.SHORT).format(value);
    }

    public static Date stringToDate(String value) {
        try {
            return DateFormat.getDateInstance(DateFormat.SHORT).parse(value);
        } catch (Exception e) {
            return null;
        }
    }
}
