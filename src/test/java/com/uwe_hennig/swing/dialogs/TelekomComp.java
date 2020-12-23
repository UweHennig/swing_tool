/**
 * @(#)TelekomComp.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.uwe_hennig.swing.widgets.Composite;
import com.uwe_hennig.swing.widgets.DataViewLayout;
import com.uwe_hennig.swing.widgets.DataViewList;
import com.uwe_hennig.swing.widgets.Utils;

public class TelekomComp extends Composite {
	private static final long	serialVersionUID	= 8187000311203240208L;
	// Tel1
	// Tel2
	// Mobil
	// Fax
	// Email
	// X.400
	// Webpage
	private JTextField			Tel1				= new JTextField();
	private JTextField			Tel2				= new JTextField();
	private JTextField			Mobil				= new JTextField();
	private JTextField			Fax					= new JTextField();
	private JTextField			Email1				= new JTextField();
	private JTextField			Email2				= new JTextField();
	private JTextField			X400				= new JTextField();
	private JTextField			Webpage				= new JTextField();

	private DataViewLayout		dvl					= new DataViewLayout();
	private DataViewList		links				= new DataViewList();
	private DataViewList		rechts				= new DataViewList();
	private JPanel				unten				= new JPanel();

	public TelekomComp() {
		init();
	}

	protected void init() {
		initLinks();
		initRechts();
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH; // In horizontaler Ebenen ausdehnbar
		gbc.weightx = 1;
		gbc.weighty = 1;
		Utils.addGridBagConstraints(this, links, gbc, 0, 0, 1, 1);
		gbc.fill = GridBagConstraints.BOTH; // In horizontaler Ebenen ausdehnbar
		gbc.weightx = 1;
		gbc.weighty = 1;
		Utils.addGridBagConstraints(this, rechts, gbc, 1, 0, 1, 1);
		gbc.fill = GridBagConstraints.BOTH; // In beiden Ebenen ausdehnbar
		gbc.weightx = 1;
		gbc.weighty = 10;
		Utils.addGridBagConstraints(this, unten, gbc, 0, 1, 2, 1);
	}

	private void initLinks() {
		links.setDataViewLayout(dvl);
		links.addLine("Tel. 1", Tel1);
		links.addLine("Tel. 2", Tel2);
		links.addLine("Mobil", Mobil);
		links.addLine("Fax ", Fax);
		// links.setBackground(java.awt.Color.red);
	}

	private void initRechts() {
		rechts.setDataViewLayout(dvl);
		rechts.addLine("eMail 1", Email1);
		rechts.addLine("eMail 2", Email2);
		rechts.addLine("X.400", X400);
		rechts.addLine("Webpage", Webpage);
		// rechts.setBackground(java.awt.Color.blue);

	}

}
