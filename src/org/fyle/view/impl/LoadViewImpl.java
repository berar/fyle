package org.fyle.view.impl;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.fyle.presenter.impl.LoginPresenterImpl;
import org.fyle.view.LoadView;

/**
 *
 * @author Korisnik
 */
public class LoadViewImpl extends JFrame implements LoadView {

    Image loadingImage;
    ImageIcon loadingImageIcon;
    URL loadingImageURL = getClass().getResource("/org/fyle/data/ajax-loader.gif");
    JLabel loadingLabel;

    public LoadViewImpl() {
        init();
    }

    private void init() {
        loadingImage = Toolkit.getDefaultToolkit().getImage(loadingImageURL);
        loadingImageIcon = new ImageIcon(loadingImage);
        loadingLabel = new JLabel("loading... ", loadingImageIcon, JLabel.CENTER);
        add(loadingLabel);
        
        setTitle("Fyle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoadViewImpl().show();
        String path = LoginPresenterImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = null;
        try {
            decodedPath = URLDecoder.decode(path, "UTF-8");
            System.out.println(decodedPath);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginPresenterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Path path1 = Paths.get(decodedPath.substring(1));
        System.out.println(path1.getParent().getParent());
        File targetFile = new File(path1.getParent().getParent()+"/foo/bar/asdf");
        File parent = targetFile.getParentFile();
        System.out.println(parent);
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
    }
}
