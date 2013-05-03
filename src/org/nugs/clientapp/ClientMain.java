package org.nugs.clientapp;

import org.nugs.clientapp.localization.Localization;
import org.nugs.clientapp.localization.impl.EnglishLocalizationImpl;
import org.nugs.clientapp.presenter.ClientPresenter;
import org.nugs.clientapp.presenter.impl.ClientPresenterImpl;
import org.nugs.clientapp.view.ClientView;
import org.nugs.clientapp.view.impl.ClientViewImpl;

/**
 *
 * @author Korisnik
 */
public class ClientMain {

    public static void main(String[] args) {
        ClientView cView = new ClientViewImpl();
        Localization local = new EnglishLocalizationImpl();
        ClientPresenter cPresenter = new ClientPresenterImpl(cView, local);

        cPresenter.start();
    }
}
