package org.fyle.presenter.impl;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ConnectException;
import org.fyle.model.*;
import org.fyle.client.Client;
import org.fyle.data.DiscMessage;
import org.fyle.data.LRInterface;
import org.fyle.data.lr.LRMessage;
import org.fyle.localization.Localization;
import org.fyle.util.MessageReceivedParser;
import org.fyle.util.impl.MessageReceivedParserImpl;
import org.fyle.view.LoginView;
import org.fyle.view.component.TextField;
import org.jboss.netty.channel.ConnectTimeoutException;

/**
 * 
 * @author berar
 */
public class LoginPresenterImpl implements ClientHandlerSubscriber {

	LoginView lView;
	Localization localization;
	Client client;

	public LoginPresenterImpl(LoginView lView, Localization localization,
			Client client) {
		this.lView = lView;
		this.localization = localization;
		this.client = client;
		client.getPipelineFactory().getClientHandler().addSubscriber(this);
		init();
	}

	private void init() {
		lView.getLoginUsername().setToolTipText(
				localization.getUsernameToolTip());
		lView.getLoginPassword().setToolTipText(
				localization.getPasswordToolTip());
		lView.getStatusLabel().setText(
				localization.getLRStatusLabelNotConnected());
		TextField[] loginFields = new TextField[] { lView.getLoginUsername(),
				lView.getLoginPassword() };
		TextField[] regFields = new TextField[] { lView.getRegEmail(),
				lView.getRegPass(), lView.getRegRepPass(),
				lView.getRegUsername() };
		lView.subscribeOnWindowExit(new WindowExitListener() {
			@Override
			public void exit() {
				if (client.isConnected()) {
					client.write(new DiscMessage().generateMessage() + "\n");
					client.disconnect();
				}
				System.exit(0);
			}
		});
		lView.getLoginButton().subscribeOnClick(new ClickListener() {
			@Override
			public void actionPerformed() {
				new Thread(new Runnable() {
					@Override
					public void run() {
						login();
					}
				}).start();
			}
		});
		for (TextField loginField : loginFields) {
			loginField.subscribeOnKeyPressed(new KeyPressedListener() {
				@Override
				public void actionPerformed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								login();
							}
						}).start();
					}
				}
			});
		}
		lView.getRegisterButton().subscribeOnClick(new ClickListener() {
			@Override
			public void actionPerformed() {
				new Thread(new Runnable() {
					@Override
					public void run() {
						register();
					}
				}).start();
			}
		});
		for (TextField regField : regFields) {
			regField.subscribeOnKeyPressed(new KeyPressedListener() {
				@Override
				public void actionPerformed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								register();
							}
						}).start();
					}
				}
			});
		}
	}
	
	private void register() {
		lView.enableAllForms(false);
		if (!client.isConnected()) {
			connect();
		}
		if (!client.isConnected()) {
			lView.enableAllForms(true);
			return;
		}
		lView.getStatusLabel().setOkIcon();
		lView.getStatusLabel()
				.setText(localization.getLRStatusLabelConnected());
		LRMessage lrm = new LRMessage(lView.getRegUsername().getText(), lView
				.getRegEmail().getText(), lView.getRegPass().getText(), lView
				.getRegRepPass().getText());
		client.write(lrm.generateMessage() + "\n");
	}

	private void login() {
		lView.enableAllForms(false);
		if (!client.isConnected()) {
			connect();
		}
		if (!client.isConnected()) {
			lView.enableAllForms(true);
			return;
		}
		lView.getStatusLabel().setOkIcon();
		lView.getStatusLabel()
				.setText(localization.getLRStatusLabelConnected());
		LRMessage lrm = new LRMessage(lView.getLoginUsername().getText(), lView
				.getLoginPassword().getText());
		client.write(lrm.generateMessage() + "\n");
	}

	private void connect() {
		lView.getStatusLabel().setLoadingIcon();
		lView.getStatusLabel().setText(
				localization.getLRStatusLabelConnecting());
		try {
			client.run();
		} catch (IOException e) {
		}
	}
	
	@Override
	public void publish(ClientHandlerEvent event) {
		if ("EXCEPTION_CAUGHT".equals(event.getProperty())) {
			Throwable error = (Throwable) event.getObject();
			handleError(error);
			return;
		}
		if ("MESSAGE_RECEIVED".equals(event.getProperty())) {
			String message = (String) event.getObject();
			MessageReceivedParser mrp = new MessageReceivedParserImpl(message);
			LRInterface lrr = mrp.handle();
			parseLRResponse(lrr);
			return;
		}
	}

	private void parseLRResponse(LRInterface lrr) {
		if ("register".equals(lrr.getType())) {
			lView.enableAllForms(true);
			if ("username".equals(lrr.getComponent())) {
				lView.getRegUsername().setBalloonTip(lrr.getMessage());
				return;
			}
			if ("email".equals(lrr.getComponent())) {
				lView.getRegEmail().setBalloonTip(lrr.getMessage());
				return;
			}
			if ("password".equals(lrr.getComponent())) {
				lView.getRegPass().setBalloonTip(lrr.getMessage());
				return;
			}
			if ("reppass".equals(lrr.getComponent())) {
				lView.getRegRepPass().setBalloonTip(lrr.getMessage());
				return;
			}
			if ("button".equals(lrr.getComponent())) {
				lView.getRegisterButton().setBalloonTip(lrr.getMessage());
				return;
			}
			return;
		}
		if ("login".equals(lrr.getType())) {
			lView.enableAllForms(true);
			if ("username".equals(lrr.getComponent())) {
				lView.getLoginUsername().setBalloonTip(lrr.getMessage());
				return;
			}
			if ("password".equals(lrr.getComponent())) {
				lView.getLoginPassword().setBalloonTip(lrr.getMessage());
				return;
			}
			if ("button".equals(lrr.getComponent())){
				lView.getLoginButton().setBalloonTip(lrr.getMessage());
				return;
			}
			return;
		}
	}

	private void handleError(Throwable error) {
		client.setConnected(false);
		lView.enableAllForms(true);
		lView.getStatusLabel().setErrorIcon();
		if (error instanceof ConnectException) {
			if (error instanceof ConnectTimeoutException) {
				lView.getStatusLabel().setText(
						localization.getLRStatusLabelConnectExcTimeout());
			} else {
				lView.getStatusLabel().setText(
						localization.getLRStatusLabelConnectExcRefused());
			}
		} else {
			lView.getStatusLabel().setText(
					localization.getLRStatusLabelConnectExcOtherIO());
		}
	}

	public void start() {
		lView.setVisible(true);
	}
}
