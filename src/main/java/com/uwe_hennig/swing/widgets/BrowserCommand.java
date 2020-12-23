/**
 * @(#)BrowserCommand.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionListener;

public class BrowserCommand {
	private String			cmdText;
	private ActionListener	listener	= null;
	private BrowserLine		browserLine	= null;

	public BrowserCommand(String cmdText, ActionListener listener) {
		this.cmdText = cmdText;
		addListener(listener);
	}

	public String getCmdText() {
		return cmdText;
	}

	public void addListener(ActionListener list) {
		listener = (ActionListener) AWTEventMulticaster.add(listener, list);
	}

	public ActionListener getListener() {
		return listener;
	}

	protected void setBrowserLine(BrowserLine browserLine) {
		this.browserLine = browserLine;
	}

	public BrowserLine getBrowserLine() {
		return browserLine;
	}
}
