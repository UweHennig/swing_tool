/**
 * @(#)Browser.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

/**
 * Browser ist ein JTable. Erweitere Funktionalität:
 * <li> 1) Uniformes Aussehen
 * <li> 2) Popup
 * <li> 3) Default - Model </dir>
 * 
 * @author Uwe Hennig
 */
public class Browser extends Composite {
	private static final long	serialVersionUID	= -7587972522910175259L;
	private JTable				table				= new JTable();
	protected JScrollPane		scrollPane;
	private BrowserListener		listener;

	/**
	 * @link aggregation
	 * @supplierCardinality 1
	 * @directed
	 */

	/* #BrowserModel lnkBrowserModel; */

	/** ctor */
	public Browser() {
		_init();
	}

	/** ctor with model */
	public Browser(BrowserModel model) {
		table.setModel(model);
		_init();
	}

	/**
	 * @param table
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return BrowserModel
	 */
	public BrowserModel getModel() {
		return (BrowserModel) table.getModel();
	}

	public void setModel(BrowserModel model) {
		table.setModel(model);
	}

	/**
	 * Interne Hilfunktion _init. Mit _init werden die Defaulteinstellung dieser Klasse vorgenommen.
	 */
	private void _init() {
		setLayout(new java.awt.BorderLayout());
		setBackground(java.awt.Color.white);

		// Scroller
		scrollPane = new JScrollPane(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// Table defaults
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.sizeColumnsToFit(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setShowGrid(true);

		// Add elements
		scrollPane.getViewport().add(table);
		add(scrollPane);
		listener = new BrowserListener(this);
		addMouseListener(listener);
		table.addMouseListener(listener);
	}

	/**
	 * createPopup erzeugt ein PopupMenu. Alle notwendigen Daten werden vom Model "getCommand-Methoden" geholt.
	 */
	private JPopupMenu createPopup() {
		int seperatorPattern = 0;
		JPopupMenu popup = new JPopupMenu();
		BrowserModel model = (BrowserModel) getModel();
		Object[] mc = model.getModelCommands();

		for (int i = 0; i < mc.length; i++) {
			BrowserCommand cmd = (BrowserCommand) mc[i];
			JMenuItem mi = new JMenuItem(cmd.getCmdText());
			popup.add(mi);
			mi.addActionListener(new InternalBrowserPopupActionListener(cmd));
		}

		int selectedObject = table.getSelectedRow();

		if (selectedObject >= 0) {
			Object[] goc = model.getGlobalObjCommands();
			Object[] oc = model.getObjectCommands(selectedObject);

			if (mc.length > 0) {
				seperatorPattern += 4;
			}

			if (goc.length > 0) {
				seperatorPattern += 2;
			}

			if (oc.length > 0) {
				seperatorPattern += 1;
			}

			if ((seperatorPattern == 6) || (seperatorPattern == 7)) {
				popup.addSeparator();
			}

			for (int k = 0; k < goc.length; k++) {
				BrowserCommand cmd = (BrowserCommand) goc[k];
				JMenuItem mi = new JMenuItem(cmd.getCmdText());
				popup.add(mi);
				mi
						.addActionListener(new InternalBrowserPopupActionListener(
								cmd));
			}

			if ((seperatorPattern == 3) || (seperatorPattern == 5)
					|| (seperatorPattern == 7)) {
				popup.addSeparator();
			}

			for (int j = 0; j < oc.length; j++) {
				BrowserCommand cmd = (BrowserCommand) oc[j];
				JMenuItem mi = new JMenuItem(cmd.getCmdText());
				popup.add(mi);
				mi
						.addActionListener(new InternalBrowserPopupActionListener(
								cmd));
			}
		}

		return popup;
	}

	/**
	 * @author Uwe Hennig
	 */
	final class BrowserListener implements MouseListener {
		private Browser	parent	= null;

		public BrowserListener(Browser parent) {
			this.parent = parent;
		}

		public void mouseClicked(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				JPopupMenu popup = parent.createPopup();
				popup.show(parent, e.getX(), e.getY());
			}
		}
	}

	final class InternalBrowserPopupActionListener implements ActionListener {
		BrowserCommand	browserCommand;

		public InternalBrowserPopupActionListener(BrowserCommand browserCommand) {
			this.browserCommand = browserCommand;
		}

		public void actionPerformed(ActionEvent event) {
			if (browserCommand != null) {
				browserCommand.setBrowserLine(((BrowserModel) getModel())
						.getSelectedLine());
				browserCommand.getListener().actionPerformed(
						new ActionEvent(browserCommand, 0, browserCommand
								.getCmdText()));
				browserCommand.setBrowserLine(null);
			}
		}
	}
}
