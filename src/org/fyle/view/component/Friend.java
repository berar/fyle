package org.fyle.view.component;

import javax.swing.ImageIcon;
import org.fyle.model.ClickListener;

public interface Friend {
	void subscribeOnClick(ClickListener cl);
	void setOnline(boolean online);
	void setProfilePicture(ImageIcon img);
	void setDefaultProfilePicture();
	void setFocus(boolean focus);
}
