/**
 * @(#)TabbedPane.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.util.Hashtable;

import javax.swing.JTabbedPane;

/**
 * TabbedPane ist ein standard TabbedPane
 * 
 * @author Uwe Hennig
 */
public class TabbedPane extends Composite {
	private static final long				serialVersionUID	= 2392145154247602614L;
	private JTabbedPane						tabbedPane			= new JTabbedPane();
	private Hashtable<String, Composite>	member				= new Hashtable<String, Composite>();

	public TabbedPane() {
		_init();
	}

	public void setTabbedPane(JTabbedPane pane) {
		this.tabbedPane = pane;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void addTab(String name, Composite comp) {
		member.put(name, comp);
		tabbedPane.addTab(name, comp);
	}

	public Composite getTab(String name) {
		return (Composite) member.get(name);
	}

	/** Interne Hilfunktion _init. Mit _init werden die Defaulteinstellung dieser Klasse vorgenommen. */
	private void _init() {
		// setLayout(new java.awt.BorderLayout());
		setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
		add(tabbedPane);
	}

}
