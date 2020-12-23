/**
 * @(#)AdresseComp.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.uwe_hennig.swing.widgets.Composite;
import com.uwe_hennig.swing.widgets.DataViewLayout;
import com.uwe_hennig.swing.widgets.DataViewList;

public class AdresseComp extends Composite {
	private static final long	serialVersionUID	= 1187652284197677359L;
	// PLZ / Postfach
	// ORT
	// Strasse HNr
	// Land
	private JTextField			Plz					= new JTextField();
	private JTextField			Ort					= new JTextField();
	private JTextField			Strasse				= new JTextField();
	private JComboBox			Land				= new JComboBox();

	private DataViewList		dvl					= new DataViewList();

	public AdresseComp() {
		init();
		Land.addItem("Deutschland");
		Land.addItem("Frankreich");
		Land.addItem("England");
	}

	protected void init() {
		dvl.addLine("PLZ / Postfach", Plz);
		dvl.addLine("Ort", Ort);
		dvl.addLine("Strasse / Hausnummer", Strasse);
		dvl.addLine("Land", Land);
		add(dvl);
	}

	public void setDataViewLayout(DataViewLayout dvll) {
		dvl.setDataViewLayout(dvll);
	}

}