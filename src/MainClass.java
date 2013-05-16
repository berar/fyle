
import org.fyle.client.Client;
import org.fyle.localization.Localization;
import org.fyle.localization.impl.EnglishLocalizationImpl;
import org.fyle.presenter.impl.LoginPresenterImpl;
import org.fyle.view.LoginView;
import org.fyle.view.impl.LoginViewImpl;



/**
 *
 * @author berar
 */
public class MainClass {
    public static void main(String[] args) {
    	Client client = new Client("localhost", 6789);
        LoginView lv = new LoginViewImpl();
        Localization local = new EnglishLocalizationImpl();
        final LoginPresenterImpl lp = new LoginPresenterImpl(lv, local, client);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lp.start();
            }
        });
    }
}
