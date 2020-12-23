/**
 * @(#)PersonComp.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.uwe_hennig.swing.widgets.Composite;
import com.uwe_hennig.swing.widgets.DataViewLayout;
import com.uwe_hennig.swing.widgets.DataViewList;

public class PersonComp extends Composite {
	private static final long	serialVersionUID	= -8681859272448282012L;
	// Anrede
	// Name
	// Vorname
	// Kurze Notiz
	private JTextField			Anrede				= new JTextField();
	private JTextField			Name				= new JTextField();
	private JTextField			Vorname				= new JTextField();
	private JTextField			Notiz				= new JTextField();
	private DataViewList		dvl					= new DataViewList();

	public PersonComp() {
		init();
	}

	protected void init() {
		// setLayout(new javax.swing.BoxLayout(this,BoxLayout.Y_AXIS));
		dvl.addLine("Anrede", Anrede, new JButton("..."));
		dvl.addLine("Vorname", Vorname);
		dvl.addLine("Name", Name);
		dvl.addLine("Notiz", Notiz);
		add(dvl);
	}

	public void setDataViewLayout(DataViewLayout dvll) {
		dvl.setDataViewLayout(dvll);
	}
}