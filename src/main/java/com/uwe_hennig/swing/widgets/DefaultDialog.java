/**
 * @(#)DefaultDialog.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * DefaultDialog is a standard dialog.
 * 
 * @see de.uh.guibase.Dialog Dialog
 * @author Uwe Hennig
 */
public class DefaultDialog extends Dialog {
	private static final long	serialVersionUID	= 5519089584748352662L;

	public DefaultDialog() {
		super();
		_init();
	}

	public DefaultDialog(String s) {
		super(s);
		_init();
	}

	private void onOk() {
		if (valid())
			ok();
	}

	protected boolean valid() {
		return false;
	}

	/**
	 * ok - Methode wird stets vom "ActionEvent" des OK-Buttons aufgerufen. Alternativ kann man mit "getButtonPanel" den
	 * DefaultButtonPanel bekommen. Hier kann man weitere Listener registrieren.
	 * 
	 * @see #getButtonPanel
	 * @see DefaultButtonPanel
	 * @see DefaultButtonPanel#addOkActionListener
	 */
	protected void ok() {}

	private void onCancel() {
		int result = JOptionPane.showConfirmDialog(this, "Wirklich abbrechen?",
				"Frage: ", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == 0) {
			cancel();
			dispose();
		}
	}

	/**
	 * cancel - Methode wird stets vom "ActionEvent" des Cancel-Buttons aufgerufen. Alternativ kann man mit
	 * "getButtonPanel" den DefaultButtonPanel bekommen. Hier kann man weitere Listener registrieren.
	 * 
	 * @see #getButtonPanel
	 * @see DefaultButtonPanel
	 * @see DefaultButtonPanel#addCancelActionListener
	 */
	protected void cancel() {}

	/**
	 * help - Methode wird stets vom "ActionEvent" des Help-Buttons aufgerufen. Alternativ kann man mit "getButtonPanel"
	 * den DefaultButtonPanel bekommen. Hier kann man weitere Listener registrieren.
	 * 
	 * @see #getButtonPanel
	 * @see DefaultButtonPanel
	 * @see DefaultButtonPanel#addHelpActionListener
	 */
	protected void help() {
		System.out.println("Help pressed!");
	}

	private void _init() {
		addWindowListener(new DefaultDialogWindowListener(this));
		DefaultButtonPanel bp = new DefaultButtonPanel();
		bp.addOkActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOk();
			}
		});
		bp.addCancelActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});
		bp.addHelpActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help();
			}
		});
		setButtonPanel(bp);
		Composite center = createCenter();
		center.setPreferredSize(new Dimension(center.getPreferredSize().width,
				center.getPreferredSize().height + 50));
		setCenter(center);
		setLocation(100, 100);
	}

	protected Composite createCenter() {
		Composite comp = new Composite();
		comp.add(new JLabel("Bitte Methode createCenter �berschreiben!"));
		return comp;
	}

	class DefaultDialogWindowListener implements WindowListener {
		DefaultDialog	dlg;

		DefaultDialogWindowListener(DefaultDialog dlg) {
			this.dlg = dlg;
		}

		// WindowListener-Methods
		public void windowActivated(java.awt.event.WindowEvent e) {}

		public void windowClosed(java.awt.event.WindowEvent e) {}

		public void windowDeactivated(java.awt.event.WindowEvent e) {}

		public void windowDeiconified(java.awt.event.WindowEvent e) {}

		public void windowIconified(java.awt.event.WindowEvent e) {}

		public void windowOpened(java.awt.event.WindowEvent e) {}

		public void windowClosing(java.awt.event.WindowEvent e) {
			dlg.cancel();
		}
	}
}
