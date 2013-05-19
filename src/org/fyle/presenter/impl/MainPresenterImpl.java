package org.fyle.presenter.impl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import org.fyle.client.Client;
import org.fyle.data.DiscMessage;
import org.fyle.data.FriendInfo;
import org.fyle.localization.Localization;
import org.fyle.localization.impl.EnglishLocalizationImpl;
import org.fyle.model.ClickListener;
import org.fyle.model.WindowExitListener;
import org.fyle.view.MainView;
import org.fyle.view.impl.MainViewImpl;

public class MainPresenterImpl {

	private Client client;
	private Localization localization;
	private MainView view;

	public MainPresenterImpl(Client client, Localization localization,
			MainView view) {
		this.client = client;
		this.localization = localization;
		this.view = view;
		init();
	}

	private void init() {
		view.getSendFileButton().setToolTipText(
				localization.getSendFileButtonTooltip());
		view.getSendDirButton().setToolTipText(
				localization.getSendDirButtonTooltip());
		view.subscribeOnWindowExit(new WindowExitListener() {
			@Override
			public void exit() {
				if (client.isConnected()) {
					client.write(new DiscMessage().generateMessage() + "\n");
					client.disconnect();
				}
				System.exit(0);
			}
		});
		for (int i = 0; i < view.getFriends().size(); i++) {
			final int helperInt = i;
			view.getFriends().get(helperInt)
					.subscribeOnClick(new ClickListener() {
						@Override
						public void actionPerformed() {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									view.switchFriend(helperInt);
								}
							});
						}
					});
		}
	}

	public void start() {
		view.setVisible(true);
	}

	public static void main(String... arg) {
		List<FriendInfo> ffs = new ArrayList<>();
		ffs.add(new FriendInfo("Nenad", false, null));
		ffs.add(new FriendInfo("Strahinja", false, null));
		ffs.add(new FriendInfo("Sniro", false, null));
		ffs.add(new FriendInfo("Djole", false, null));
		ffs.add(new FriendInfo("Jelena", false, null));
		ffs.add(new FriendInfo("Ivana", false, null));
		ffs.add(new FriendInfo("Vajda", false, null));
		Client client = new Client("localhost", 6789);
		Localization local = new EnglishLocalizationImpl();
		MainView view = new MainViewImpl(ffs);
		final MainPresenterImpl pres = new MainPresenterImpl(client, local,
				view);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pres.start();
			}
		});
	}
}
