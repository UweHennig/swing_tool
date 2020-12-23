// =========================================================================
/**
 * @(#)DetailComp.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import com.uwe_hennig.swing.widgets.Composite;
import com.uwe_hennig.swing.widgets.DataViewList;
import com.uwe_hennig.swing.widgets.ExchangeComp;

public class DetailComp extends Composite {
	private static final long	serialVersionUID	= 909587548683103534L;

	public DetailComp() {
		init();
	}

	protected void init() {
		String[] strList = { "Abitur", "Studium", "F�hrerschein Kl.3" };
		ExchangeComp ec = new ExchangeComp("Qualifikationen");
		ec.addData(strList);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(5, 5)));
		DataViewList lv = new DataViewList();
		lv.addLine("Ausweisnummer", new JTextField());
		add(lv);
		add(Box.createRigidArea(new Dimension(5, 5)));
		add(ec);
		doLayout();
	}
}
