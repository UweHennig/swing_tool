/**
 * @(#)Tree.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;

/**
 * Tree standard for JTree
 * 
 * @author Uwe Hennig
 */
public class Tree extends Composite {
    private static final long serialVersionUID = -4586332003279369509L;
    private JTree             tree             = new JTree();
    private JScrollPane       scrollPane;

    public Tree() {
        _init();
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }

    public JTree getTree() {
        return this.tree;
    }

    /**
     * internal helper
     */
    private void _init() {
        setLayout(new java.awt.BorderLayout());
        setBackground(java.awt.Color.white);
        scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().add(tree);
        add(scrollPane);
    }

}
