package org.fyle.view.component.impl;

import javax.swing.JTextArea;

import org.fyle.view.component.TextArea;

public class TextAreaImpl extends JTextArea implements TextArea {
	
	public TextAreaImpl(int x, int y){
		super(x,y);
		this.setEditable(false);
	}
}
