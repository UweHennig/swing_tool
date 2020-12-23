/**
 * @(#)DataViewLayout.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.Dimension;

/**
 * Layoutklasse für DataViewList. Verschiede Objekte vom DataViewList werden mit
 * einem Layout versehen.
 * 
 * @see de.uh.guibase.DataViewList
 * @author Uwe Hennig
 */
public class DataViewLayout extends java.util.Observable {
    private Dimension maxLabelDim  = new Dimension(50, 5);
    private Dimension maxWidgetDim = new Dimension(150, 5);
    private Dimension maxExtraDim  = new Dimension(10, 5);

    public DataViewLayout() {
    }

    public Dimension getMaxLabelDim() {
        return maxLabelDim;
    }

    public void setMaxLabelDim(Dimension dim) {
        maxLabelDim = dim;
        setChanged();
        notifyObservers();
    }

    public Dimension getMaxWidgetDim() {
        return maxWidgetDim;
    }

    public void setMaxWidgetDim(Dimension dim) {
        maxWidgetDim = dim;
        setChanged();
        notifyObservers();
    }

    public void setMaxExtraDim(Dimension dim) {
        maxExtraDim = dim;
        setChanged();
        notifyObservers();
    }

    public Dimension getMaxExtraDim() {
        return maxExtraDim;
    }

    /**
     * Update - Methode. Alle Labels und Controlls haben die gleiche Gr��e in
     * DataViewList
     */
    public void update(Dimension labelDim, Dimension widgetDim) {
        update(labelDim, widgetDim, null);
    }

    public void update(Dimension labelDim, Dimension widgetADim, Dimension widgetBDim) {
        updateMaxLabelDim(labelDim);
        updateMaxWidgetDim(widgetADim);
        if (widgetBDim != null)
            updateMaxExtraDim(widgetBDim);
        notifyObservers();
    }

    private void updateMaxLabelDim(Dimension dim) {
        if (dim.width > maxLabelDim.width) {
            maxLabelDim.width = dim.width;
            setChanged();
        }
        if (dim.height > maxLabelDim.height) {
            maxLabelDim.height = dim.height;
            setChanged();
        }
    }

    private void updateMaxWidgetDim(Dimension dim) {
        if (dim.width > maxWidgetDim.width) {
            maxWidgetDim.width = dim.width;
            setChanged();
        }
        if (dim.height > maxWidgetDim.height) {
            maxWidgetDim.height = dim.height;
            setChanged();
        }
    }

    private void updateMaxExtraDim(Dimension dim) {
        if (dim.width > maxExtraDim.width) {
            maxExtraDim.width = dim.width;
            setChanged();
        }
        if (dim.height > maxExtraDim.height) {
            maxExtraDim.height = dim.height;
            setChanged();
        }
    }
}
