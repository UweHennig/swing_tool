/**
 * @(#)Composite.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

/**
 * Composite ist die Basisklasse vieler Komponenten.
 * 
 * @author Uwe Hennig
 */
public class Composite extends JPanel implements Serializable {
	private static final long	serialVersionUID	= -450648807943160754L;

	/** Liefert eine Liste aller Client-Komponenten. Analoge Methode zu getComponent aus javax.swing.JComponent */
	public Composite[] getComposites() {
		java.awt.Component[] comps = getComponents();
		java.util.Vector<java.awt.Component> vec = new java.util.Vector<java.awt.Component>();
		for (int i = 0; i < comps.length; i++) {
			if (comps[i] instanceof Composite)
				vec.add(comps[i]);
		}
		Composite[] ret = new Composite[vec.size()];
		vec.copyInto(ret);
		return ret;
	}

	/**
	 * Analoge Methode zu getComposite allerdings mit allen rekursiven Komponenten
	 * 
	 * @see Composite.getComposites
	 */
	public Vector<Composite> getRecursiveComposites(Composite comp) {
		Vector<Composite> vec = new Vector<Composite>();
		if (comp == null)
			return getRecursiveComposites(this);
		else {
			vec.add(comp);
			Composite[] comps = comp.getComposites();
			for (int i = 0; i < comps.length; i++) {
				if (comps[i] instanceof Composite)
					vec.addAll(getRecursiveComposites(comps[i]));
			}
		}
		return vec;
	}
}
