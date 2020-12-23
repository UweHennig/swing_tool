/**
 * @(#)DataViewLayout.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.Dimension;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Returns a uniform structure for input fields with label.
 * 
 * @see de.uh.guibase.DataViewLayout
 * @author Uwe Hennig
 */
public class DataViewList extends Composite implements Observer {
    private static final long       serialVersionUID = 3968154566418468734L;
    private Hashtable<String, Data> hashList         = new Hashtable<String, Data>();
    private DataViewLayout          dvl              = new DataViewLayout();
    private boolean                 twoColumns       = true;

    public DataViewList() {
        _init();
    }

    private class Data {
        public JComponent label;
        public JComponent widgetA;
        public JComponent widgetB;

        public Data(JComponent l, JComponent cA, JComponent cB) {
            label = l;
            widgetA = cA;
            widgetB = cB;
        }
    }

    public void setDataViewLayout(DataViewLayout dvl) {
        this.dvl.deleteObserver(this);
        this.dvl = dvl;
        this.dvl.addObserver(this);
    }

    /** Es wird eine Zeile bestehend aus Label und Componente erzeugt. */
    public Composite addLine(String strLabel, JComponent widget) {
        return addLine(strLabel, widget, null);
    }

    public Composite addLine(String strLabel, JComponent widgetA, JComponent widgetB) {
        JLabel label = new JLabel(strLabel);
        label.setLabelFor(widgetA);
        if (widgetB == null)
            dvl.update(label.getPreferredSize(), widgetA.getPreferredSize());
        else
            dvl.update(label.getPreferredSize(), widgetA.getPreferredSize(), widgetB.getPreferredSize());
        Composite comp = Utils.createLine(label, widgetA, widgetB);
        add(comp);
        add(Box.createRigidArea(new Dimension(5, 5)));
        hashList.put(strLabel, new Data(label, widgetA, widgetB));
        update();
        return comp;
    }

    public JComponent getComponent(String s) {
        Data comp = (Data) hashList.get(s);
        return comp.widgetA;
    }

    protected void _init() {
        dvl.addObserver(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(5, 15)));
    }

    /**
     * update - Methode aus Observer. delegiert an die private Methode update.
     * 
     * @see DataViewList#update update
     */
    public void update(Observable observable, Object obj) {
        update();
    }

    /** aktualisiert das Layout der Liste. */
    public void update() {
        int maxLabelDimWidth = dvl.getMaxLabelDim().width;
        int maxWidgetDimWidth = dvl.getMaxWidgetDim().width;
        int maxExtraDimWidth = dvl.getMaxExtraDim().width;
        int width = maxLabelDimWidth + maxWidgetDimWidth + maxExtraDimWidth + 25;
        int height = dvl.getMaxLabelDim().height + 5;
        Data data;

        for (Enumeration<Data> e = hashList.elements(); e.hasMoreElements();) {
            data = (Data) e.nextElement();
            data.label.setPreferredSize(dvl.getMaxLabelDim());
            data.label.setMinimumSize(dvl.getMaxLabelDim());
            if (twoColumns) {
                int tmp = maxWidgetDimWidth + (data.widgetB == null ? maxExtraDimWidth : -5);
                Dimension dim = new Dimension(tmp, height);
                data.widgetA.setPreferredSize(dim);
                data.widgetA.setMinimumSize(dim);
            } else {
                data.widgetA.setPreferredSize(dvl.getMaxWidgetDim());
                data.widgetA.setMinimumSize(dvl.getMaxWidgetDim());
            }
            if (data.widgetB != null)
                data.widgetB.setPreferredSize(dvl.getMaxExtraDim());
        }
        int anz = hashList.size();
        setPreferredSize(new Dimension(width, anz * (height + 15)));
    }
}
