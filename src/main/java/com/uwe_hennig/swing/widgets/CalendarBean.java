/**
 * @(#)CalendarBean.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class CalendarBean extends Composite {
    private static final long serialVersionUID = -1846216120564611428L;
    private static Object[]   headlines        = { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };
    private static String[]   months           = { "Jan", "Feb", "Mrz", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt",
            "Nov", "Dez" };
    private int[][]           matrix           = { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
    private Calendar          currentDate;
    private JTable            table;
    private JLabel            monthLabel       = new JLabel();
    private JLabel            yearLabel        = new JLabel();

    final class CalendarModel extends DefaultTableModel {
        private static final long serialVersionUID = 1454209452877794811L;

        public CalendarModel(Object[] headlines) {
            super(headlines, 6);
        }

        public Object getValueAt(int row, int column) {
            if (matrix[row][column] > 0)
                return new Integer(matrix[row][column]);
            else
                return "";
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }

    public CalendarBean(Calendar date) {
        currentDate = date;
        _init();
        update();
    }

    private void update() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                matrix[i][j] = 0;
            }
        }
        int monthIndex = currentDate.get(Calendar.MONTH);
        monthLabel.setText(months[monthIndex]);
        yearLabel.setText("" + currentDate.get(Calendar.YEAR));
        Calendar tmpCalendar = Calendar.getInstance();
        tmpCalendar.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), 1);
        // printDate(tmpCalendar,"eins");
        int wt = (tmpCalendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        System.out.println("wt=" + headlines[wt]);
        tmpCalendar.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH) + 1, 1);
        tmpCalendar.add(Calendar.DATE, -1);
        // printDate(tmpCalendar,"zwei");
        int days = tmpCalendar.get(Calendar.DATE);
        for (int row = 0, n = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (row == 0 && col < wt - 1)
                    matrix[row][col] = 0;
                else if (n <= days)
                    matrix[row][col] = n++;
            }
        }
        updateUI();
    }

    @SuppressWarnings("unused")
    private void printDate(Calendar cal, String s) {
        System.out.println("---------------------" + s);
        System.out.println("YEAR                 " + cal.get(Calendar.YEAR));
        System.out.println("MONTH                " + cal.get(Calendar.MONTH));
        System.out.println("DATE                 " + cal.get(Calendar.DATE));
        System.out.println("DAY_OF_MONTH         " + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("DAY_OF_WEEK          " + cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("DAY_OF_WEEK_IN_MONTH " + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("DAY_OF_YEAR          " + cal.get(Calendar.DAY_OF_YEAR));
        System.out.println("---------------------");
    }

    private void _init() {
        Border etched = BorderFactory.createEtchedBorder();
        setBorder(etched);
        setLayout(new BorderLayout());
        DefaultTableModel model = new CalendarModel(headlines);
        table = new JTable(model);
        table.setModel(model);
        table.setCellSelectionEnabled(true);
        // table.setRowSelectionAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(200, 100));
        for (int i = 0; i < model.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setPreferredWidth(20);
        for (int i = 0; i < model.getColumnCount(); i++)
            for (int j = 0; j < model.getRowCount(); j++) {
                JLabel l = (JLabel) table.getCellRenderer(j, i);
                l.setHorizontalAlignment(JLabel.CENTER);
                l.setBackground(java.awt.Color.lightGray);
            }
        add(createNorthPanel(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void decrMonth() {
        currentDate.add(Calendar.MONTH, -1);
        update();
    }

    private void incrMonth() {
        currentDate.add(Calendar.MONTH, +1);
        update();
    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JButton links = new JButton("<");
        JButton rechts = new JButton(">");
        links.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrMonth();
            }
        });
        rechts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                incrMonth();
            }
        });
        monthLabel.setText("");
        panel.add(links);
        panel.add(Box.createHorizontalGlue());
        panel.add(monthLabel);
        panel.add(new JLabel(" - "));
        panel.add(yearLabel);
        panel.add(Box.createHorizontalGlue());
        panel.add(rechts);
        return panel;
    }
}
