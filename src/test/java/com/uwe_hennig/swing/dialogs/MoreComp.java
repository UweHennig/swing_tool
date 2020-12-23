/**
 * @(#)CompleteDialogTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import javax.swing.BoxLayout;

import com.uwe_hennig.swing.widgets.Composite;

public class MoreComp extends Composite {
	private static final long	serialVersionUID	= -8666459671786709959L;
	
	private PersonComp			personComp			= new PersonComp();
	private AdresseComp			adresseComp			= new AdresseComp();

	public MoreComp() {
		init();
	}

	protected void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(personComp);
		add(adresseComp);
	}
}