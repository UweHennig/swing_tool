/**
 * @(#)BrowserColumn.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

/**
 * This class contains the list of column headers.
 * 
 * @author Uwe Hennig
 */
public class BrowserColumn {
	private String[]	headlines	= null;

	public BrowserColumn(String[] headlines) {
		this.headlines = headlines;
	}

	public String headLine(int col) {
		return headlines[col];
	}

	public int getColumnCount() {
		return headlines.length;
	}
}
