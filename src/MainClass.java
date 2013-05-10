
import org.fyle.localization.Localization;
import org.fyle.localization.impl.EnglishLocalizationImpl;
import org.fyle.presenter.impl.LoginPresenterImpl;
import org.fyle.validation.LRValidator;
import org.fyle.validation.impl.LRValidatorImpl;
import org.fyle.view.LoginView;
import org.fyle.view.impl.LoginViewImpl;



/**
 *
 * @author Korisnik
 */
public class MainClass {
    public static void main(String[] args) {
        LoginView lv = new LoginViewImpl();
        Localization local = new EnglishLocalizationImpl();
        LRValidator ec = new LRValidatorImpl(local);
        final LoginPresenterImpl lp = new LoginPresenterImpl(lv, local, ec);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lp.start();
            }
        });
    }
}
