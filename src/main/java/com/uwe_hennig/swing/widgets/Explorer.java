/**
 * @(#)Explorer.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

/**
 * Explorer
 * 
 * @author Uwe Hennig
 */
public class Explorer extends Composite {
    private static final long      serialVersionUID   = 2022648289983494896L;
    private Tree                   tree;
    private Browser                browser;
    private SplitPane              splitpane;
    private TreePath               treepath           = null;
    private ExplorerBrowserFactory explBrowserFactory = null;

    public Explorer(Tree tree, ExplorerBrowserFactory ebf) {
        this.tree = tree;
        this.explBrowserFactory = ebf;
        _init();
    }

    public TreePath getCurrentTreePath() {
        return treepath;
    }

    private void _init() {
        tree.getTree().addTreeSelectionListener(new ExplorerTreeSelListener());
        browser = explBrowserFactory.create(null);
        splitpane = new SplitPane(tree, browser);
        add(splitpane);
    }

    class ExplorerTreeSelListener implements TreeSelectionListener {
        public void valueChanged(TreeSelectionEvent e) {
            treepath = tree.getTree().getSelectionPath();
            browser = explBrowserFactory.create(treepath);
            splitpane.setRightComponent(browser);
        }
    }
}
