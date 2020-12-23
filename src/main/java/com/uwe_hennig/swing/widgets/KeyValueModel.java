/**
 * @(#)KeyValueModel.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import javax.swing.table.AbstractTableModel;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * @author Uwe Hennig
 */
public class KeyValueModel extends AbstractTableModel {
	private static final long	serialVersionUID	= 639726709073256778L;
	private String				keyHeadline			= "key";
	private String				valueHeadline		= "value";
	private Entry[]				data;
	private int					entries				= 0;
	private static int			initialCapacity		= 11;

	private static class Entry {
		String	key;
		Object	value;

		public Entry(String key, Object value) {
			this.key = key;
			this.value = value;
		}

		public String toString() {
			return key.toString() + "=" + value.toString();
		}
	}

	public KeyValueModel(String keyHeadline, String valueHeadline) {
		this.keyHeadline = keyHeadline;
		this.valueHeadline = valueHeadline;
		data = new Entry[initialCapacity];
	}

	public KeyValueModel(String keyHeadline, String valueHeadline, Hashtable table) {
		this.keyHeadline = keyHeadline;
		this.valueHeadline = valueHeadline;
		data = new Entry[table.size()];
		add(table);
	}

	/** returns value or null if not found */
	public Object getValue(String key) {
		for (int i = 0; i < entries; i++) {
			String tmp = data[i].key;
			if (tmp.compareTo(key) == 0)
				return data[i].value;
		}
		return null;
	}

	public void remove(String key) {
		for (int i = 0; i < entries; i++) {
			if (key.compareTo(data[i].key) == 0) {
				remove(i);
				return;
			}
		}
	}

	public void remove(int pos) {
		for (int i = pos; i < entries - 1; i++) {
			data[i] = data[i + 1];
		}
		data[--entries] = null;
	}

	public Hashtable<String, Object> getAll() {
		Hashtable<String, Object> table = new Hashtable<String, Object>(
				(entries <= 0) ? 1 : entries);
		for (int i = 0; i < entries; i++) {
			table.put(data[i].key, data[i].value);
		}
		return table;
	}

	public void add(String key, Object value) {
		if (entries + 1 >= data.length)
			resize();
		Object o = getValue(key);
		if (o == null) // no duplicates
			data[entries++] = new Entry(key, value);
	}

	public void add(Hashtable table) {
		Enumeration keys = table.keys();
		Object key;
		Object value;
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			value = table.get(key);
			if (key instanceof String)
				add((String) key, value);
		}
	}

	/** table structure */
	public int getRowCount() {
		return entries;
	}

	/** table structure */
	public Object getValueAt(int row, int column) {
		if (column == 0)
			return data[row].key;
		else
			return data[row].value;
	}

	private void resize() {
		int oldCapacity = data.length;
		int newCapacity = oldCapacity * 2 + 1;
		Entry oldData[] = data;
		Entry newData[] = new Entry[newCapacity];
		for (int i = 0; i < oldCapacity; i++) {
			newData[i] = oldData[i];
		}
		data = newData;
	}

	/** only for internal use */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 1)
			data[rowIndex].value = aValue;
	}

	public boolean isCellEditable(int row, int col) {
		return col == 1;
	}

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int column) {
		switch (column) {
			case 0:
				return keyHeadline;
			case 1:
				return valueHeadline;
			default:
				return "";
		}
	}
}
