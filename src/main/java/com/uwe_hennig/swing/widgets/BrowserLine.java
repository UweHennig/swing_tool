/**
 * @(#)BrowserLine.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.util.Vector;

/**
 * BrowserLine is an adapter class.
 * Each line consist of columns to be rendered and has its own popup menu.
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
