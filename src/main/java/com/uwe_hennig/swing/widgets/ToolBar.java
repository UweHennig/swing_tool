/**
 * @(#)ToolBar.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * @author Uwe Hennig
 */
public class ToolBar extends Composite {
    private static final long          serialVersionUID = -6881916449573549743L;
    private JToolBar                   toolbar          = new JToolBar();
    private Hashtable<String, JButton> buttons          = new Hashtable<String, JButton>();

    public ToolBar() {
        _init();
    }

    private void _init() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(toolbar);
    }

    public void addButton(Icon image, ActionListener actionListener) {
        toolbar.add(new JButton(image));
    }

    public void addButton(JButton button) {
        toolbar.add(button);
    }
    
    public void addTextButton(String text, ActionListener actionListener) {
        JButton button = new JButton();
        button.setText(text);
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
        toolbar.add(button);
    }

    public void addIconButton(String imageNameStub, ActionListener actionListener) {
        Icon defaultIcon = getIcon(getDefaultIconName(imageNameStub));
        Icon pressedIcon = getIcon(getPressedIconName(imageNameStub));
        Icon disabledIcon = getIcon(getDisabledIconName(imageNameStub));
        Icon selectedIcon = getIcon(getSelectedIconName(imageNameStub));

        JButton button = new JButton();
        button.setIcon(defaultIcon);
        button.setPressedIcon(pressedIcon);
        button.setDisabledIcon(disabledIcon);
        button.setSelectedIcon(selectedIcon);

        button.addActionListener(actionListener);
        buttons.put(imageNameStub, button);
        toolbar.add(button);
    }

    private Icon getIcon(String name) {
        Icon icon = null;

        try {
            URL tmpURL = ResourceEnvironment.getIconURL(name);
            icon = new ImageIcon(tmpURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return icon;
    }

    private String getDefaultIconName(String imageNameStub) {
        return imageNameStub + "1.gif";
    }

    private String getPressedIconName(String imageNameStub) {
        return imageNameStub + "2.gif";
    }

    private String getDisabledIconName(String imageNameStub) {
        return imageNameStub + "3.gif";
    }

    private String getSelectedIconName(String imageNameStub) {
        return imageNameStub + "1.gif";
    }
}
