/**
 * @(#)ExchangeComp.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

/**
 * ExchangeComp
 * 
 * @author Uwe Hennig
 */
public class ExchangeComp extends Composite {
	private static final long	serialVersionUID	= 2880447060229330015L;
	final private JButton		RightAllButton		= new JButton(">>  >>");
	final private JButton		RightButton			= new JButton("  >>  ");
	final private JButton		LeftButton			= new JButton("  <<  ");
	final private JButton		LeftAllButton		= new JButton("<<  <<");
	final private int			buttonWidth			= RightAllButton
															.getPreferredSize().width;
	final private int			buttonHeight		= RightAllButton
															.getPreferredSize().height;
	private String				borderTitle			= new String("");

	class ExchangeTableModel extends DefaultTableModel {
		private static final long	serialVersionUID	= 7407174075906779401L;
		static final public int		LEFT_MODE			= 1;
		static final public int		RIGHT_MODE			= 2;
		private int					theMode				= -1;

		public ExchangeTableModel() {}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public ExchangeTableModel(int Mode) {
			theMode = Mode;
		}

		public int getColumnCount() {
			return 1;
		}

		public String getColumnName(int column) {
			if (theMode == LEFT_MODE)
				return "Auswahlmenge";
			else
				return "Zugeordnete";
		}
	}

	private JTable				leftTable;
	private JTable				rightTable;
	private ExchangeTableModel	leftTableModel	= new ExchangeTableModel();
	private ExchangeTableModel	rightTableModel	= new ExchangeTableModel();

	public ExchangeComp() {
		_init();
	}

	public ExchangeComp(String borderTitle) {
		setBorderTitle(borderTitle);
		_init();
	}

	public void setSelection(String[] stringVector) {
		onLeftAll();
		int anz = leftTable.getRowCount();
		for (int i = anz - 1; i >= 0; i--) {
			String s = (String) leftTable.getValueAt(i, 0);
			for (int j = 0; j < stringVector.length; j++) {
				if (stringVector[j].compareTo(s) == 0) {
					moveRight(i);
					break;
				}
			}
		}
	}

	public String[] getSelection() {
		int anz = rightTable.getRowCount();
		String[] strList = new String[anz];
		for (int i = 0; i < anz; i++) {
			strList[i] = (String) rightTableModel.getValueAt(i, 0);
		}
		return strList;
	}

	public void moveLeft(int pos) {
		int rRowCount = rightTable.getRowCount();
		if (rRowCount > 0 && rRowCount > pos && pos >= 0) {
			String[] sv = new String[1];
			sv[0] = (String) rightTableModel.getValueAt(pos, 0);
			rightTableModel.removeRow(pos);
			leftTableModel.addRow(sv);
		}
	}

	public void moveRight(int pos) {
		int lRowCount = leftTable.getRowCount();
		if (lRowCount > 0 && lRowCount > pos && pos >= 0) {
			String[] sv = new String[1];
			sv[0] = (String) leftTableModel.getValueAt(pos, 0);
			leftTableModel.removeRow(pos);
			rightTableModel.addRow(sv);
		}
	}

	public void addData(String[] stringVector) {
		for (int i = 0; i < stringVector.length; i++) {
			String[] tmp = new String[1];
			tmp[0] = stringVector[i];
			leftTableModel.addRow(tmp);
		}
	}

	public void setBorderTitle(String s) {
		borderTitle = s;
		if (borderTitle != "") {
			Border etched = BorderFactory.createEtchedBorder();
			TitledBorder title = BorderFactory.createTitledBorder(etched,
					borderTitle);
			title.setTitleJustification(TitledBorder.LEFT);
			setBorder(title);
			doLayout();
		}
	}

	protected void onRightAll() {
		while (leftTable.getRowCount() > 0) {
			moveRight(0);
		}
	}

	protected void onRight() {
		if (leftTable.getRowCount() > 0) {
			while (leftTable.getSelectedRow() >= 0)
				moveRight(leftTable.getSelectedRow());
		}
	}

	protected void onLeft() {
		if (rightTable.getRowCount() > 0) {
			int sel;
			while ((sel = rightTable.getSelectedRow()) >= 0)
				moveLeft(sel);
		}
	}

	protected void onLeftAll() {
		while (rightTable.getRowCount() > 0) {
			moveLeft(0);
		}
	}

	protected void _init() {
		// init Buttons
		RightAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onRightAll();
			}
		});
		RightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onRight();
			}
		});
		LeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onLeft();
			}
		});
		LeftAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onLeftAll();
			}
		});
		// init Layout
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH; // In beiden Ebenen ausdehnbar
		gbc.weightx = 1;
		gbc.weighty = 1;
		addWithInfo(createLeftComp(), gbc, 0, 0, 1, 1);
		gbc.fill = GridBagConstraints.NONE; // Soll nicht in der Gr�sse �nderbar sein
		gbc.weightx = 0;
		gbc.weighty = 1;
		addWithInfo(createCenterComp(), gbc, 1, 0, 1, 1);
		gbc.fill = GridBagConstraints.BOTH; // In beiden Ebenen ausdehnbar
		gbc.weightx = 1;
		gbc.weighty = 1;
		addWithInfo(createRightComp(), gbc, 2, 0, 1, 1);
		Dimension bldim = new Dimension(5 * buttonWidth, 4 * buttonHeight);
		if (borderTitle != "") {
			bldim.width += 10;
			bldim.height += 30;
		}
		setPreferredSize(bldim);
	}

	protected JScrollPane createLeftComp() {
		leftTableModel = new ExchangeTableModel(ExchangeTableModel.LEFT_MODE);
		leftTable = new JTable(leftTableModel);
		JScrollPane scrollpane = new JScrollPane(leftTable);
		return scrollpane;
	}

	protected JPanel createCenterComp() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Dimension dim = new Dimension(buttonWidth, buttonHeight);
		RightAllButton.setMaximumSize(dim);
		RightButton.setMaximumSize(dim);
		LeftButton.setMaximumSize(dim);
		LeftAllButton.setMaximumSize(dim);
		panel.add(RightAllButton);
		panel.add(RightButton);
		panel.add(LeftButton);
		panel.add(LeftAllButton);
		panel.setMinimumSize(new Dimension(buttonWidth, buttonHeight * 4));
		return panel;
	}

	protected JScrollPane createRightComp() {
		rightTableModel = new ExchangeTableModel(ExchangeTableModel.RIGHT_MODE);
		rightTable = new JTable(rightTableModel);
		JScrollPane scrollpane = new JScrollPane(rightTable);
		return scrollpane;
	}

	private void addWithInfo(Component c, GridBagConstraints gbc, int x, int y,
			int w, int h) {
		gbc.gridx = x; // Horiz Position
		gbc.gridy = y; // Vertikal Position
		gbc.gridwidth = w; // Breite in Grideinheiten
		gbc.gridheight = h; // H�he in Grideinheiten
		add(c, gbc);
	}
}
