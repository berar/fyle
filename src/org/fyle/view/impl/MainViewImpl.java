package org.fyle.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.fyle.data.FriendInfo;
import org.fyle.model.WindowExitListener;
import org.fyle.view.MainView;
import org.fyle.view.component.Button;
import org.fyle.view.component.Friend;
import org.fyle.view.component.MenuItem;
import org.fyle.view.component.impl.ButtonImpl;
import org.fyle.view.component.impl.FriendImpl;
import org.fyle.view.component.impl.MenuItemImpl;
import org.fyle.view.component.impl.TextAreaImpl;

public class MainViewImpl extends JFrame implements MainView {

	private URL serverImageURL = getClass().getResource(
			"/images/server avatar 32x32.png");
	private Image serverImage = Toolkit.getDefaultToolkit().getImage(
			serverImageURL);
	private ImageIcon serverImageIcon = new ImageIcon(serverImage);
	private URL fileImageURL = getClass().getResource("/images/file 32x32.png");
	private Image fileImage = Toolkit.getDefaultToolkit()
			.getImage(fileImageURL);
	private ImageIcon fileImageIcon = new ImageIcon(fileImage);
	private URL dirImageURL = getClass()
			.getResource("/images/folder 32x32.png");
	private Image dirImage = Toolkit.getDefaultToolkit().getImage(dirImageURL);
	private ImageIcon dirImageIcon = new ImageIcon(dirImage);

	private List<FriendImpl> friends;
	private List<TextAreaImpl> friendsChatArea;
	private List<JScrollPane> friendsChatScrollPane;
	private JPanel friendsPanel;
	private ButtonImpl sendChatButton, sendFileButton, sendDirButton;
	private TextAreaImpl chatField;
	private TextAreaImpl serverChatArea;
	private FriendImpl serverFriend;
	private FocusedFriend focusedFriend;
	private JPanel completeChatPanel;
	private JPanel chatFieldAndButtonsPanel;

	private JMenuBar menuBar;
	private JMenu contactsMenu;
	private MenuItemImpl addContact;
	private MenuItemImpl blockUser;

	public MainViewImpl(List<FriendInfo> friendsInfo) {
		friends = new ArrayList<>();
		friendsChatArea = new ArrayList<>();
		friendsChatScrollPane = new ArrayList<>();
		initUI(friendsInfo);
	}

	private void initUI(List<FriendInfo> friendsInfo) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setTitle("Fyle");
		setLayout(new BorderLayout());
		getContentPane().setPreferredSize(new Dimension(700, 500));

		menuBar = new JMenuBar();
		contactsMenu = new JMenu("Contacts");
		menuBar.add(contactsMenu);
		addContact = new MenuItemImpl("Add contact");
		blockUser = new MenuItemImpl("Block user");
		contactsMenu.add(addContact);
		contactsMenu.add(blockUser);
		setJMenuBar(menuBar);

		friendsPanel = new JPanel();
		friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
		friendsPanel.setMaximumSize(friendsPanel.getPreferredSize());
		serverFriend = new FriendImpl("Server", true, serverImageIcon);
		serverFriend.setFocus(true);
		focusedFriend = new FocusedFriend(serverFriend, 0);
		serverFriend.setMaximumSize(serverFriend.getPreferredSize());
		serverChatArea = new TextAreaImpl(10, 10);
		// erase this
		serverChatArea.append(" " + -1);
		serverChatArea.setFont(new Font(serverChatArea.getFont().getFontName(),
				Font.PLAIN, 15));
		JScrollPane serverChatScrollPane = new JScrollPane(serverChatArea);
		serverChatScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		serverChatScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		for (int i = -1; i < friendsInfo.size(); i++) {
			if (i == -1) {
				friendsPanel.add(serverFriend);
				friendsPanel.add(Box.createVerticalStrut(1));
				continue;
			}
			friends.add(new FriendImpl(friendsInfo.get(i).getUsername(),
					friendsInfo.get(i).isOnline(), friendsInfo.get(i).getImg()));
			friends.get(i).setMaximumSize(friends.get(i).getPreferredSize());
			friends.get(i).setFocus(false);			
			friendsPanel.add(friends.get(i));
			friendsPanel.add(Box.createVerticalStrut(1));
			friendsChatArea.add(new TextAreaImpl(10, 10));
			friendsChatArea.get(i).setFont(
					new Font(friendsChatArea.get(i).getFont().getFontName(),
							Font.PLAIN, 15));
			// erase this
			friendsChatArea.get(i).append(" " + i);
			friendsChatScrollPane.add(new JScrollPane(friendsChatArea.get(i)));
			friendsChatScrollPane.get(i).setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			friendsChatScrollPane.get(i).setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		}
		friends.add(0, serverFriend);
		friendsChatScrollPane.add(0, serverChatScrollPane);
		JScrollPane friendsScrollPane = new JScrollPane(friendsPanel);
		friendsScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		friendsScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		sendChatButton = new ButtonImpl("Send");
		sendChatButton.setPreferredSize(new Dimension(100, 30));
		sendChatButton.setMaximumSize(sendChatButton.getPreferredSize());
		sendChatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sendFileButton = new ButtonImpl(fileImageIcon);
		sendFileButton.setPreferredSize(new Dimension(50, 30));
		sendFileButton.setMaximumSize(sendFileButton.getPreferredSize());
		sendDirButton = new ButtonImpl(dirImageIcon);
		sendDirButton.setPreferredSize(new Dimension(50, 30));
		sendDirButton.setMaximumSize(sendDirButton.getPreferredSize());
		JPanel sendFilesPanel = new JPanel(new FlowLayout());
		sendFilesPanel.add(sendFileButton);
		sendFilesPanel.add(sendDirButton);
		sendFilesPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		JPanel sendButtonsPanel = new JPanel();
		sendButtonsPanel.setLayout(new BoxLayout(sendButtonsPanel,
				BoxLayout.Y_AXIS));
		sendButtonsPanel.add(sendFilesPanel);
		sendButtonsPanel.add(sendChatButton);
		chatField = new TextAreaImpl(4, 25);
		chatField.setFont(new Font(chatField.getFont().getFontName(),
				Font.PLAIN, 15));
		chatField.setMaximumSize(chatField.getPreferredSize());
		chatField.setEditable(true);
		chatFieldAndButtonsPanel = new JPanel(new FlowLayout());
		JScrollPane chatFieldScrollPane = new JScrollPane(chatField);
		chatFieldScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatFieldScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatFieldAndButtonsPanel.add(chatFieldScrollPane);
		chatFieldAndButtonsPanel.add(sendButtonsPanel);
		completeChatPanel = new JPanel();
		completeChatPanel.setLayout(new BorderLayout());
		completeChatPanel
				.add(friendsChatScrollPane.get(0), BorderLayout.CENTER);
		completeChatPanel.add(chatFieldAndButtonsPanel, BorderLayout.SOUTH);

		add(friendsScrollPane, BorderLayout.WEST);
		add(completeChatPanel, BorderLayout.CENTER);
		pack();
	}

	@Override
	public void subscribeOnWindowExit(final WindowExitListener wl) {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				wl.exit();
			}
		});
	}

	private void replaceChatAreas(int index) {
		completeChatPanel.removeAll();
		completeChatPanel.add(friendsChatScrollPane.get(index),
				BorderLayout.CENTER);
		completeChatPanel.add(chatFieldAndButtonsPanel, BorderLayout.SOUTH);
		validate();
		repaint();
		// pack();
	}

	@Override
	public void switchFriend(int i) {
		focusedFriend.getFocusedFriend().setFocus(false);
		friends.get(i).setFocus(true);
		focusedFriend.setFocusedFriend(friends.get(i));
		focusedFriend.setIndex(i);
		replaceChatAreas(i);
	}

	@Override
	public MenuItem getAddContact() {
		return addContact;
	}

	@Override
	public MenuItem getBlockUser() {
		return blockUser;
	}

	@Override
	public Button getSendChatButton() {
		return sendChatButton;
	}

	@Override
	public Button getSendFileButton() {
		return sendFileButton;
	}

	@Override
	public Button getSendDirButton() {
		return sendDirButton;
	}

	@Override
	public List<? extends Friend> getFriends() {
		return friends;
	}

	final class FocusedFriend {

		private Friend focusedFriend;
		private int index;

		public FocusedFriend(Friend f, int i) {
			this.focusedFriend = f;
			this.index = i;
		}

		public Friend getFocusedFriend() {
			return focusedFriend;
		}

		public void setFocusedFriend(Friend focusedFriend) {
			this.focusedFriend = focusedFriend;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}
}
