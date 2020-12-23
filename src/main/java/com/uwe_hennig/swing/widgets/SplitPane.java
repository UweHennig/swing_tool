/**
 * @(#)SplitPane.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import javax.swing.JSplitPane;

/**
 * SplitPane ist ein standard SplitPane. Die rechte und linke Seite besteht aus einem Composite.
 * 
 * @see de.uh.guibase.Comosite
 * @author Uwe Hennig
 */
public class SplitPane extends Composite {
	private static final long	serialVersionUID	= -3445130625363328640L;
	private JSplitPane			splitPane			= null;
	private Composite			left				= new Composite();
	private Composite			right				= new Composite();

	/** No default ctor */
	private SplitPane() {}

	public SplitPane(Composite l, Composite r) {
		left = l;
		right = r;
		_init();
	}

	public JSplitPane getSplitPane() {
		return this.splitPane;
	}

	public void setLeftComponent(Composite left) {
		this.left = left;
		this.splitPane.setLeftComponent(left);
	}

	public Composite getLeftComponent() {
		return this.left;
	}

	public void setRightComponent(Composite right) {
		int pos = splitPane.getDividerLocation();
		System.out.println("divLoc=" + pos);
		this.right = right;
		this.splitPane.setRightComponent(right);
		splitPane.setDividerLocation(pos);
		invalidate();
	}

	public Composite getRightComponent() {
		return this.right;
	}

	/** Interne Hilfunktion _init. Mit _init werden die Defaulteinstellung dieser Klasse vorgenommen. */
	private void _init() {
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		splitPane.setDividerLocation(150);
		setLayout(new java.awt.BorderLayout());
		setBackground(java.awt.Color.white);
		add(splitPane);
	}
}
