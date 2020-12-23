/**
 * @(#)BrowserLine.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.util.Vector;

/**
 * BrowserLine ist ein Adapter. Er stellt eine Objekt dar, das sich in einer Zeile des Browsers darstellt. Eine Zeile
 * besteht aus Spalten und jede Spalte hat einen Renderer. Jedes Objekt hat ein eigenes PopupMenu und entsprechende
 * Listener.
 * 
 * @author Uwe Hennig
 */
public abstract class BrowserLine {
	/**
	 * @link aggregation
	 * @associates <{BrowserCommand}>
	 * @directed
	 * @supplierCardinality 0..*
	 * @label objectCmomands
	 */
	private Vector<BrowserCommand>	browserCommands	= new Vector<BrowserCommand>();

	public abstract int getColumnCount();

	public abstract String getColumnString(int column);

	/**
	 * @param browserCommand
	 */
	public void addCommand(BrowserCommand browserCommand) {
		browserCommands.add(browserCommand);
	}

	/**
	 * @return all Commands
	 */
	public Object[] getCommands() {
		return browserCommands.toArray();
	}
}
