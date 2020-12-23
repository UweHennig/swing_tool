/**
 * @(#)KeyValueTypeModel.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.util.Hashtable;

import javax.swing.table.AbstractTableModel;

/**
 * @author Uwe Hennig
 */
public class KeyValueTypeModel extends AbstractTableModel {
    private static final long serialVersionUID = 3412714169059979754L;
    private String            keyHeadline      = "key";
    private String            valueHeadline    = "value";
    private String            typeHeadline     = "type";
    private Entry[]           data;
    private int               entries          = 0;
    private static int        initialCapacity  = 11;

    private static class Entry {
        String key;
        Object value;
        String type;

        public Entry(String key, Object value, String type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public String toString() {
            return key.toString() + "=" + value.toString() + "/" + type.toString();
        }
    }

    public KeyValueTypeModel(String keyHeadline, String valueHeadline, String typeHeadline) {
        this.keyHeadline = keyHeadline;
        this.valueHeadline = valueHeadline;
        this.typeHeadline = typeHeadline;
        data = new Entry[initialCapacity];
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

    public String getType(String key) {
        for (int i = 0; i < entries; i++) {
            String tmp = data[i].key;
            if (tmp.compareTo(key) == 0)
                return (String) data[i].type;
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
        Hashtable<String, Object> table = new Hashtable<String, Object>((entries <= 0) ? 1 : entries);
        for (int i = 0; i < entries; i++) {
            table.put(data[i].key, data[i].value);
        }
        return table;
    }

    public void add(String key, Object value, String type) {
        if (entries + 1 >= data.length)
            resize();
        Object o = getValue(key);
        if (o == null) // no duplicates
            data[entries++] = new Entry(key, value, type);
    }

    /** table structure */
    public int getRowCount() {
        return entries;
    }

    /** table structure */
    public Object getValueAt(int row, int column) {
        if (column == 0)
            return data[row].key;
        else if (column == 1)
            return data[row].value;
        else
            return data[row].type;
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
        if (columnIndex == 2)
            data[rowIndex].type = (String) aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public boolean isCellEditable(int row, int col) {
        return col == 1 || col == 2;
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int column) {
        switch (column) {
        case 0:
            return keyHeadline;
        case 1:
            return valueHeadline;
        default:
            return typeHeadline;
        }
    }
}
