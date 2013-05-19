package org.fyle.view;

import java.util.List;
import org.fyle.model.WindowExitListener;
import org.fyle.view.component.Button;
import org.fyle.view.component.Friend;
import org.fyle.view.component.MenuItem;

public interface MainView {
	void setVisible(boolean vis);
	void subscribeOnWindowExit(WindowExitListener wl);
	MenuItem getAddContact();
	MenuItem getBlockUser();
	Button getSendChatButton();
	Button getSendFileButton();
	Button getSendDirButton();
	List<? extends Friend> getFriends();
    void switchFriend(int i);
}
