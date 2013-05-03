
package org.nugs.clientapp.presenter.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.nugs.clientapp.localization.Localization;
import org.nugs.clientapp.model.PressOrClick;
import org.nugs.clientapp.net.client.Client;
import org.nugs.clientapp.presenter.ClientPresenter;
import org.nugs.clientapp.user.impl.Message;
import org.nugs.clientapp.user.impl.UserImpl;
import org.nugs.clientapp.view.ClientView;
import org.nugs.clientapp.util.*;
import org.nugs.clientapp.util.impl.*;

/**
 *
 * @author Korisnik
 */
public class ClientPresenterImpl implements ClientPresenter {

    ClientView cView;
    Localization localization;
    private String message;
    private org.nugs.clientapp.net.client.Client client;
    private Thread connectionThread;
    private static final String[] optionsArray = new String[]{"/name", "/quit", "/clear", "/options"};

    public ClientPresenterImpl(ClientView cView, Localization localization) {
        this.cView = cView;
        this.localization = localization;
        init();
    }

    private void init() {
        cView.subscribeOnPressOrClick(new PressOrClick() {
            @Override
            public void pressOrClickPerformed() {
                //check if input is not all white spaced
                if (cView.getInputText().trim().length() > 0) {
                    //trim all white spaces before beginning and after ending charcters
                    message = cView.getInputText().trim();
                    //check if it's an options input
                    if (message.startsWith("/")) {
                        String messageWoFirst = DelimiterInputParserImpl.removeFirst(message);
                        //parse string into string array word by word
                        List<String> optionList = DelimiterInputParserImpl.parseInput(messageWoFirst);
                        switch (optionList.get(0).toLowerCase()) {
                            case "name": {
                                //check if name is of a-z, A-Z, 0-9
                                if (Pattern.matches(Constants.RGX_DETECT_NAME_COMMAND_CORRECT, message)) {
                                    UserImpl.getInstance().setName(optionList.get(1));
                                    cView.setOutputText(localization.getYourNewName() + UserImpl.getInstance().getName(), true);
                                } else {
                                    cView.setOutputText(localization.getInvalidUsername(), true);
                                }
                                break;
                            }
                            case "connect": {
                                if (!UserImpl.getInstance().isIsConnected()) {
                                    UserImpl.getInstance().setHostIp(optionList.get(1));
                                    UserImpl.getInstance().setHostPort(Integer.parseInt(optionList.get(2)));
                                    UserImpl.getInstance().setIsConnected(true);
                                    //creating new thread because swing isn't good with demanding non swing tasks
                                    connectionThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                //client connecting
                                                client = new Client(UserImpl.getInstance().getHostIp(), UserImpl.getInstance().getHostPort(), ClientPresenterImpl.this);
                                                client.run();
                                            } catch (Exception ex) {
                                                Logger.getLogger(ClientPresenterImpl.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    });
                                    connectionThread.start();
                                } else {
                                    cView.setOutputText(localization.getAlreadyConnected(), true);
                                }
                                break;
                            }
                            case "quit": {
                                if (UserImpl.getInstance().isIsConnected()) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //if /quit send /quit to server
                                            client.setChannelFuture(client.getChannel().write("/quit\n"));
                                            try {
                                                //check if server has to send something back and then close connection
                                                client.getChannel().closeFuture().sync();
                                                if (client.getChannelFuture() != null) {
                                                    client.getChannelFuture().sync();
                                                }
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(ClientPresenterImpl.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            client.getEventLoopGroup().shutdown();
                                            UserImpl.getInstance().setIsConnected(false);
                                            cView.setOutputText(localization.getDisconnectedSuccess(), true);
                                        }
                                    }).start();

                                } else {
                                    cView.setOutputText(localization.getNotConnected(), true);
                                }
                                break;
                            }
                            case "clear": {
                                cView.clearChatArea();
                                break;
                            }
                            case "options": {
                                for (String option : optionsArray) {
                                    cView.setOutputText(option, true);
                                }
                                break;
                            }
                            default: {
                                cView.setOutputText(localization.getInvalidOption(), true);
                                break;
                            }
                        }
                    } else {
                        Message.getInstance().setUsername(UserImpl.getInstance().getName());
                        Message.getInstance().setMessage(message);
                        if (UserImpl.getInstance().isIsConnected()) {
                            client.setChannelFuture(client.getChannel().write(Message.getInstance().toString()+"\n"));
                        }
                    }
                }
                cView.clearTextArea();

            }
        });
    }

    @Override
    public void start() {
        cView.show();
    }

    @Override
    public ClientView getView() {
        return this.cView;
    }
}
