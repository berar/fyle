package org.fyle.presenter.impl;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ConnectException;

import org.fyle.client.Client;
import org.fyle.data.LRMessage;
import org.fyle.localization.Localization;
import org.fyle.model.ClickListener;
import org.fyle.model.ClientHandlerEvent;
import org.fyle.model.ClientHandlerSubscriber;
import org.fyle.model.KeyPressedListener;
import org.fyle.util.MessageReceivedParser;
import org.fyle.util.impl.LRResponse;
import org.fyle.util.impl.MessageReceivedParserImpl;
import org.fyle.util.mrhandler.MessageReceivedHandler;
import org.fyle.view.LoginView;
import org.fyle.view.TextField;
import org.jboss.netty.channel.ConnectTimeoutException;


/**
 * 
 * @author berar
 */
public class LoginPresenterImpl implements ClientHandlerSubscriber {

	LoginView lView;
	Localization localization;
	Client client;

	public LoginPresenterImpl(LoginView lView, Localization localization, Client client) {
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
	//first check if connected, if not try to connect.
	//second check is a must since connect() will block
	//and it might not connect due to timeout or refused connection.
	//if connected pick up all fields from registration form and send them.
	//swing's elements get disabled and stay so, they will be enabled
	//but that is driven either by receiving a message from server
	//or by connection error
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
		client.write(lrm.generateRegisterMessage()+"\n");
	}

	private void login() {
		System.out.println("Unsupported yet.");
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
	
	//ClientHandler sends notification when server sends something, and the object will
	//get parsed. MessageReceivedParser determine if it's a login or register message.
	//MessageReceivedHandler will 'decrypt' the message into LLResponse object.
	//That object is later passed to presenter's method so it can publish result on view. 
	//Right now it does seem a little bit of an overkill because all of the classes and
	//objects, so it might get simplified. Similar approach is on server's side and it fits
	//better there because server will deal with much more than handling login and registration.
	@Override
	public void publish(ClientHandlerEvent event) {
		if ("EXCEPTION_CAUGHT".equals(event.getProperty())) {
			Throwable error = (Throwable) event.getObject();
			handleError(error);
			return;
		}
		if ("MESSAGE_RECEIVED".equals(event.getProperty())) {
			String message = (String) event.getObject();
			System.out.println(message);
			MessageReceivedParser mrp = new MessageReceivedParserImpl(message);
			MessageReceivedHandler mrh = mrp.handle();
			LRResponse lrr = mrh.createLRResponse();
			parseLRResponse(lrr);
			return;
		}
	}
	
	private void parseLRResponse(LRResponse lrr){
		if("REGISTER".equals(lrr.getProperty())){
			lView.enableAllForms(true);
			if ("USERNAME".equals(lrr.getComponent())){
				lView.getRegUsername().setBalloonTip(lrr.getMessage());
				return;
			}
			if("EMAIL".equals(lrr.getComponent())){
				lView.getRegEmail().setBalloonTip(lrr.getMessage());
				return;
			}
			if("PASSWORD".equals(lrr.getComponent())){
				lView.getRegPass().setBalloonTip(lrr.getMessage());
				return;
			}
			if("REPPASS".equals(lrr.getComponent())){
				lView.getRegRepPass().setBalloonTip(lrr.getMessage());
				return;
			}
			return;
		}
		if("LOGIN".equals(lrr.getProperty())){
			return;
		}
	}
	
	private void handleError(Throwable error) {
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
		lView.show();
	}
	/*
	 * private void checkLoginErrors() { if
	 * (lView.getLoginUsername().getText().length() == 0) {
	 * lView.getLoginUsername
	 * ().setBalloonTip(localization.getThisFieldIsEmpty()); return; } if
	 * (lView.getLoginPassword().getText().length() == 0) {
	 * lView.getLoginPassword
	 * ().setBalloonTip(localization.getThisFieldIsEmpty()); return; } String
	 * regUsername = lView.getLoginUsername().getText(); String regPassword =
	 * lView.getLoginPassword().getText(); ValidationResult vr; vr =
	 * eci.isUsernameCorrect(regUsername); if (!vr.isValid()) {
	 * lView.getLoginUsername().setBalloonTip(vr.getMessage()); return; } vr =
	 * eci.isPasswordCorrect(regPassword); if (!vr.isValid()) {
	 * lView.getLoginPassword().setBalloonTip(vr.getMessage()); return; }
	 * System.out.println("Login is valid."); }
	 */
}
