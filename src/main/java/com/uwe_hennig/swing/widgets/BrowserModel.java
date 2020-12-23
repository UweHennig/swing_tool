/**
 * @(#)BrowserModel.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import java.awt.event.ActionListener;

/*-
 * Model der Klasse Browser.
 * Das Model besteht aus einem Vektor von BrowserLines.<br>
 * Hier werden die Events f�r das PopupMenu spezifiziert:<br>
 * Das Menü besteht aus "BrowserComands" wie "New", aus "GlobalObjCommands" wie
 * "Show", "Edit" und "Delete" und aus "ObjectCommands" wie z.B. "Recalulate".<br>
 * <br>
 * Beispiel:<br>
 * <br>
 * --<br>
 * | New<br>
 * | -----------------<br>
 * | Show<br>
 * | Edit<br>
 * | Delete<br>
 * | -----------------<br>
 * | Recalculate<br>
 * --<br>
 * @see Browser
 * @author Uwe Hennig
 */

public class BrowserModel extends AbstractTableModel {
	private static final long		serialVersionUID	= 8224965657818807748L;

	/** @supplierCardinality 1 */
	private BrowserColumn			browserColumns;

	/**
	 * @link aggregation
	 * @associates <{BrowserLine}>
	 * @supplierCardinality 0..*
	 * @directed
	 */
	private Vector<BrowserLine>		browserLines		= new Vector<BrowserLine>();

	/**
	 * @link aggregation
	 * @associates <{BrowserCommand}>
	 * @directed
	 * @supplierCardinality 0..*
	 * @label browserCommands
	 */
	private Vector<BrowserCommand>	browserCommands		= new Vector<BrowserCommand>();

	/**
	 * @link aggregation
	 * @associates <{BrowserCommand}>
	 * @directed
	 * @supplierCardinality 0..*
	 * @label globalObjectCommands
	 */
	private Vector<BrowserCommand>	globalObjCommands	= new Vector<BrowserCommand>();

	public BrowserModel(BrowserColumn browserColumns) {
		this.browserColumns = browserColumns;
	}

	public void addLine(BrowserLine line) {
		browserLines.add(line);
	}

	public BrowserLine getSelectedLine() {
		return null;
	}

	public Object[] getModelCommands() {
		return browserCommands.toArray();
	}

	public Object[] getGlobalObjCommands() {
		return globalObjCommands.toArray();
	}

	public Object[] getObjectCommands(int row) {
		BrowserLine line = (BrowserLine) browserLines.get(row);
		return line.getCommands();
	}

	/** from AbstractTableModel. */
	public int getRowCount() {
		return browserLines.size();
	}

	/** from AbstractTableModel. */
	public int getColumnCount() {
		return browserColumns.getColumnCount();
	}

	/** PopupMenu-Command pro Model */
	public void addCommand(String txt, ActionListener listener) {
		browserCommands.add(new BrowserCommand(txt, listener));
	}

	/** PopupMenu-Command pro Object. */
	public void addGlobalObjCommand(String txt, ActionListener listener) {
		globalObjCommands.add(new BrowserCommand(txt, listener));
	}

	/** from AbstractTableModel. */
	public Object getValueAt(int row, int column) {
		return ((BrowserLine) browserLines.elementAt(row))
				.getColumnString(column);
	}

	/** from AbstractTableModel. */
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/** from AbstractTableModel. */
	public String getColumnName(int column) {
		return browserColumns.headLine(column);
	}
}
