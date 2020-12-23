/**
 * @(#)KeyValueTypeEditor.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * @author Uwe Hennig
 */
public class KeyValueTypeEditor extends Composite {
	private static final long	serialVersionUID	= 8471694120970546992L;
	private KeyValueTypeModel	defaultTableModel	= new KeyValueTypeModel(
															"Key", "Value",	"Type");
	private JTable				table				= new JTable(defaultTableModel);
	private JScrollPane			scrollPane;
	private JPopupMenu			popupMenu			= new JPopupMenu();
	private String[]			typeList			= { "Stück", "Währung" };

	public KeyValueTypeEditor() {
		_init();
	}

	public KeyValueTypeEditor(KeyValueTypeModel model) {
		table.setModel(model);
		_init();
	}

	public KeyValueTypeModel getModel() {
		return (KeyValueTypeModel) table.getModel();
	}

	private void _init() {
		setLayout(new java.awt.BorderLayout());
		setBackground(java.awt.Color.white);
		// Scroller
		scrollPane = new JScrollPane(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// Table defaults
		table.sizeColumnsToFit(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setShowGrid(true);
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc0 = tcm.getColumn(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setBackground(java.awt.Color.lightGray);
		tc0.setCellRenderer(tcr);
		TableColumn tc2 = tcm.getColumn(2);
		DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setBackground(java.awt.Color.lightGray);
		tc2.setCellRenderer(tcr);
		TableColumn typeColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox();
		for (int i = 0; i < typeList.length; i++)
			comboBox.addItem(typeList[i]);
		typeColumn.setCellEditor(new DefaultCellEditor(comboBox));
		// Add elements
		scrollPane.getViewport().add(table);
		add(scrollPane);
		initPopupMenu();
		table.add(popupMenu);
	}

	protected void initPopupMenu() {
		JMenuItem mi = new JMenuItem("");
		popupMenu.add(mi);
	}
}
