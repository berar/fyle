package org.fyle.view.component.impl;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.fyle.view.component.Label;

public class StatusLabelImpl extends JLabel implements Label{
	
	private static Image loadingImage;
    private static ImageIcon loadingImageIcon;
    private static URL loadingImageURL;
    
    private static Image okImage;
    private static ImageIcon okImageIcon;
    private static URL okImageURL;
    
    private static Image noImage;
    private static ImageIcon noImageIcon;
    private static URL noImageURL;
	
	public StatusLabelImpl(String text){
		super(text);
	}
	
	@Override
	public void setOkIcon(){
		if(okImage == null && okImageIcon == null && okImageURL == null){
			okImageURL = getClass().getResource("/images/ok icon green 16x16.png");
			okImage = Toolkit.getDefaultToolkit().getImage(okImageURL);
			okImageIcon = new ImageIcon(okImage);
		}
		super.setIcon(okImageIcon);
	}
	
	@Override
	public void setErrorIcon(){
		if(noImage == null && noImageIcon == null && noImageURL == null){
			noImageURL = getClass().getResource("/images/no icon red 16x16.png");
			noImage = Toolkit.getDefaultToolkit().getImage(noImageURL);
			noImageIcon = new ImageIcon(noImage);
		}
		super.setIcon(noImageIcon);
	}
	
	@Override
	public void setLoadingIcon(){
		if(loadingImage == null && loadingImageIcon == null && loadingImageURL == null){
			loadingImageURL = getClass().getResource("/images/ajax-loader.gif");
	        loadingImage = Toolkit.getDefaultToolkit().getImage(loadingImageURL);
	        loadingImageIcon = new ImageIcon(loadingImage);
		}
		super.setIcon(loadingImageIcon);
	}
}
