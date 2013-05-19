package org.fyle.view.component.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.fyle.model.ClickListener;
import org.fyle.view.component.Friend;

public class FriendImpl extends JPanel implements Friend {
	
	private static Color lightBlue = new Color(31, 190, 214, 80);
	private JLabel usernameLabel;
	
	private JLabel onlineStatusLabel;
	private Image onlineImage;
    private ImageIcon onlineImageIcon;
    private URL onlineImageURL;
	private Image offlineImage;
    private ImageIcon offlineImageIcon;
    private URL offlineImageURL;
    
    private JLabel profilePictureLabel;
	private Image profileImage;
    private ImageIcon profileImageIcon;
    private URL profileImageURL;
	
    public FriendImpl(String username, boolean online, ImageIcon img){
    	this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    	this.setPreferredSize(new Dimension(180,50));
    	this.setBorder(BorderFactory.createLineBorder(Color.black));
    	onlineStatusLabel = new JLabel("", JLabel.CENTER);
    	onlineStatusLabel.setPreferredSize(new Dimension(32,32));
    	setOnline(online);
    	profilePictureLabel = new JLabel("", JLabel.CENTER);
    	profilePictureLabel.setPreferredSize(new Dimension(32,32));
    	profilePictureLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
    	profilePictureLabel.setHorizontalAlignment(JLabel.LEFT);
    	usernameLabel = new JLabel(username, JLabel.CENTER);
    	usernameLabel.setPreferredSize(new Dimension(116,50));
    	usernameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
    	usernameLabel.setHorizontalAlignment(JLabel.LEFT);
    	if(img!=null){
    		setProfilePicture(img);
    	} else {
    		setDefaultProfilePicture();
    	}
    	this.add(profilePictureLabel);
    	this.add(usernameLabel);
    	this.add(onlineStatusLabel);
    }
    
    @Override
	public void subscribeOnClick(final ClickListener cl) {
        this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl.actionPerformed();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}});
	}

	@Override
	public void setOnline(boolean online) {
		if(online){
			if(onlineImage == null && onlineImageIcon == null && onlineImageURL == null){
				onlineImageURL = getClass().getResource("/images/user online 32x32.png");
				onlineImage = Toolkit.getDefaultToolkit().getImage(onlineImageURL);
				Image newimg = onlineImage.getScaledInstance( 16, 16,  java.awt.Image.SCALE_SMOOTH );
				onlineImageIcon = new ImageIcon(newimg);
			}
			onlineStatusLabel.setIcon(onlineImageIcon);
		}
		if(!online){
			if(offlineImage == null && offlineImageIcon == null && offlineImageURL == null){
				offlineImageURL = getClass().getResource("/images/user offline 32x32.png");
				offlineImage = Toolkit.getDefaultToolkit().getImage(offlineImageURL);
				Image newimg = offlineImage.getScaledInstance( 16, 16,  java.awt.Image.SCALE_SMOOTH );
				offlineImageIcon = new ImageIcon(newimg);
			}
			onlineStatusLabel.setIcon(offlineImageIcon);
		}
	}

	@Override
	public void setProfilePicture(ImageIcon icon) {
		profilePictureLabel.setIcon(icon);
	}
	
	@Override
	public void setDefaultProfilePicture(){
		if(profileImage == null && profileImageIcon == null && profileImageURL == null){
			profileImageURL = getClass().getResource("/images/user default avatar 32x32.png");
			profileImage = Toolkit.getDefaultToolkit().getImage(profileImageURL);
			profileImageIcon = new ImageIcon(profileImage);
		}
		profilePictureLabel.setIcon(profileImageIcon);
	}
	
	@Override
	public void setFocus(boolean focus){
		if (focus) {
			this.setBackground(lightBlue);
		} else {
			this.setBackground(Color.WHITE);
		}
	}
}
