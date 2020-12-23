/**
 * @(#)ButtonPanel.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * Button panel class.
 * 
 * @see de.uh.guibase.DefaultButtonPanel
 * @see de.uh.guibase.Dialog
 * @see de.uh.guibase.DefaultDialog
 * @author Uwe Hennig
 */
public class ButtonPanel extends Composite {
	private static final long	serialVersionUID		= 7398396326936833047L;
	protected final int			defaultButtonWidth		= 90;
	protected final int			defaultButtonHeight		= 30;
	protected final int			defaultRigidAreaWidth	= 10;
	protected final int			defaultRigidAreaHeight	= 5;
	protected Dimension			bDim					= null;
	protected Dimension			rArea					= null;
	protected Dimension			maxArea					= null;
	protected int				countButtons			= 0;

	public ButtonPanel() {
		_init();
	}

	public void addButton(JButton aButton) {
		aButton.setPreferredSize(bDim);
		add(aButton);
		add(Box.createRigidArea(rArea));
		countButtons++;
		resizePanel();
	}

	public int getCountButtons() {
		// includes subpanels!
		return countButtons;
	}

	public JButton[] getButtons() {
		JButton[] bt = new JButton[countButtons];
		int j = 0;
		// Composites of this ButtonPanel ( one or two )
		Vector<Composite> composites = getRecursiveComposites(this);
		for (Composite composite : composites) {
			if (composite instanceof ButtonPanel) {
				// this or other
				Component[] components = ((ButtonPanel) composite).getComponents();
				for (int i = 0; i < components.length; i++)
					if (components[i] instanceof JButton)
						bt[j++] = (JButton) components[i];
			}
		}
		return bt;
	}

	public void setRigidArea(Dimension dim) {
		rArea = dim;
		if (rArea.width > maxArea.width)
			maxArea.width = rArea.width;
		if (rArea.height > maxArea.height)
			maxArea.height = rArea.height;
		resizePanel();
	}

	protected void resizePanel() {
		// Buttonpanel soll alle rigitAreas berücksichtigen und geht
		// zunächst von MaxArea aus - besser Summe bilden.
		setPreferredSize(new Dimension((bDim.width + maxArea.width)
				* countButtons + maxArea.width, bDim.height + 2
				* maxArea.height));
		invalidate();
	}

	/** Interne Hilfunktion _init. Mit _init werden die Defaulteinstellung dieser Klasse vorgenommen. */
	private void _init() {
		try {
			bDim = new Dimension(defaultButtonWidth, defaultButtonHeight);
			rArea = new Dimension(defaultRigidAreaWidth, defaultRigidAreaHeight);
			maxArea = new Dimension(defaultRigidAreaWidth,
					defaultRigidAreaHeight);
			setPreferredSize(new Dimension(defaultButtonWidth + rArea.width,
					defaultButtonHeight + 2 * rArea.height));
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			// setBorder(BorderFactory.createEtchedBorder());
			add(Box.createHorizontalGlue());
		} catch (Exception ex) {
			System.out.println("Exception " + ex);
		}
	}
}
