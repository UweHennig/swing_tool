/**
 * @(#)ExplorerBrowserFactory.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import javax.swing.tree.TreePath;

/**
 * ExplorerBrowserFactory
 * 
 * @see de.uh.guibase.Explorer Explorer
 * @author Uwe Hennig
 */
public interface ExplorerBrowserFactory {
	public Browser create(TreePath treepath);
}
