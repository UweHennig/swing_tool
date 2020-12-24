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
	private static final String QUESTION = "Question: ";
	private static final String CANCEL_QUESTION = "Cancel?";

	public DefaultDialog() {
		super();
		_init();
	}

	public DefaultDialog(String s) {
		super(s);
		_init();
	}

	private void onOk() {
		if (valid()) {
			ok();
		}
	}

	protected boolean valid() {
		return true;
	}

	protected void ok() {
	    dispose();
	}

	private void onCancel() {
		int result = JOptionPane.showConfirmDialog(this, CANCEL_QUESTION,QUESTION, 
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == 0) {
			cancel();
			dispose();
		}
	}

	protected void cancel() {}

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
		comp.add(new JLabel("Bitte Methode createCenter überschreiben!"));
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
