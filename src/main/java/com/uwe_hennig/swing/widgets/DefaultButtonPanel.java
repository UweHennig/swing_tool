/**
 * @(#)DefaultButtonPanel.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.AWTEventMulticaster;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;

/**
 * DefaultButtonPanel bildet eine Buttonzeile. Diese Buttonleiste wird im Dialog verwendet und besteht aus den Buttons:
 * OK, Abbruch und Hilfe
 * 
 * @see de.uh.guibase.ButtonPanel ButtonPanel
 * @see de.uh.guibase.Dialog Dialog
 * @see de.uh.guibase.DefaultDialog DefaultDialog
 * @author Uwe Hennig
 */
public class DefaultButtonPanel extends ButtonPanel {
	private static final long	serialVersionUID		= 2764333432404792490L;
	private JButton				okButton				= new JButton("Ok");
	private JButton				cancelButton			= new JButton("Abbruch");
	private JButton				helpButton				= new JButton("Hilfe");
	private ActionListener		helpActionListener		= null;
	private ActionListener		okActionListener		= null;
	private ActionListener		cancelActionListener	= null;
	private ButtonPanel			internalPanel			= new ButtonPanel();

	public DefaultButtonPanel() {
		super();
		_init();
	}

	public void addButton(JButton aButton) {
		internalPanel.addButton(aButton);
		countButtons++;
		resizePanel();
		invalidate();
	}

	protected void resizePanel() {
		int thisWidth = (bDim.width + maxArea.width) * 3 + maxArea.width;
		Dimension iDim = internalPanel.getPreferredSize();
		setPreferredSize(new Dimension(iDim.width + thisWidth, bDim.height + 2
				* maxArea.height));
		invalidate();
	}

	/** Klasse kann Default-Buttons ActionListener aufnehmen. */
	public synchronized void addCancelActionListener(ActionListener al) {
		cancelActionListener = AWTEventMulticaster
				.add(cancelActionListener, al);
	}

	/** Klasse kann Default-Buttons ActionListener l�schen. */
	public synchronized void removeCancelActionListener(ActionListener al) {
		cancelActionListener = AWTEventMulticaster.remove(cancelActionListener,
				al);
	}

	/** Klasse kann Default-Buttons ActionListener aufnehmen. */
	public synchronized void addOkActionListener(ActionListener al) {
		okActionListener = AWTEventMulticaster.add(okActionListener, al);
	}

	/** Klasse kann Default-Buttons ActionListener l�schen. */
	public synchronized void removeOkActionListener(ActionListener al) {
		okActionListener = AWTEventMulticaster.remove(okActionListener, al);
	}

	/** Klasse kann Default-Buttons ActionListener aufnehmen. */
	public synchronized void addHelpActionListener(ActionListener al) {
		helpActionListener = AWTEventMulticaster.add(helpActionListener, al);
	}

	/** Klasse kann Default-Buttons ActionListener l�schen. */
	public synchronized void removeHelpActionListener(ActionListener al) {
		helpActionListener = AWTEventMulticaster.remove(helpActionListener, al);
	}

	/** Event-Dispatcher f�r OK-Button */
	protected void ok() {
		if (okActionListener != null)
			okActionListener.actionPerformed(new ActionEvent(okButton, 0,
					"help"));
	}

	/** Event-Dispatcher f�r Cancel-Button */
	protected void cancel() {
		if (cancelActionListener != null)
			cancelActionListener.actionPerformed(new ActionEvent(cancelButton,
					0, "cancel"));
	}

	/** Event-Dispatcher f�r Help-Button */
	protected void help() {
		if (helpActionListener != null)
			helpActionListener.actionPerformed(new ActionEvent(helpButton, 0,
					"help"));
	}

	/** initialisiere divere Einstellungen */
	private void _init() {
		setBorder(BorderFactory.createEtchedBorder());
		add(internalPanel);
		add(Box.createRigidArea(new Dimension(defaultRigidAreaWidth,
				defaultRigidAreaHeight)));
		super.addButton(okButton);
		super.addButton(cancelButton);
		super.addButton(helpButton);
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		};
		okButton.addActionListener(al);
		al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		};
		cancelButton.addActionListener(al);
		al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help();
			}
		};
		helpButton.addActionListener(al);
	}
}
