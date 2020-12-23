/**
 * @(#)DateField.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

public class DateField extends JTextField {
    private static final long serialVersionUID = -6007675674613267016L;
    private SimpleDateFormat  format           = new SimpleDateFormat("dd.MM.yyyy");

    public DateField(Date value) {
        super(10);
        setDocument(new FormattedDocument(format));
        setValue(value);
    }

    public Date getValue() {
        try {
            return format.parse(getText());
        } catch (Exception e) {
            return null;
        }
    }

    public void setValue(Date value) {
        setText(format.format(value));
    }
}
