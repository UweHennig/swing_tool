/**
 * @(#)CompleteDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.BorderLayout;

import com.uwe_hennig.swing.widgets.Composite;
import com.uwe_hennig.swing.widgets.DefaultDialog;
import com.uwe_hennig.swing.widgets.SplitPane;
import com.uwe_hennig.swing.widgets.TabbedPane;
import com.uwe_hennig.swing.widgets.ToolBar;

public class CompleteDialogTest extends DefaultDialog {
	private static final long	serialVersionUID	= 5893607247826513032L;

	public CompleteDialogTest(String s) {
		super(s);
		_init();
	}

	private void _init() {
		ToolBar toolbar = new ToolBar();

		//toolbar.addIconButton("TEXT", null);
		toolbar.addTextButton("Push me!", null);
		getContentPane().add(toolbar, BorderLayout.NORTH);
	}

	protected Composite createCenter() {
		TabbedPane comp = new TabbedPane();

		comp.addTab("Person", new PersonComp());
		comp.addTab("Details", new DetailComp());
		comp.addTab("Adresse", new AdresseComp());
		comp.addTab("Telekommunikation", new TelekomComp());

		SplitPane sp = new SplitPane(new Composite(), new Composite());
		comp.addTab("Splitter", sp);
		comp.addTab("More", new MoreComp());

		return comp;
	}

	static public void main(String[] arg) {
		CompleteDialogTest dlg = new CompleteDialogTest("Personendaten");
		dlg.pack();
		dlg.setVisible(true);
	}
}
