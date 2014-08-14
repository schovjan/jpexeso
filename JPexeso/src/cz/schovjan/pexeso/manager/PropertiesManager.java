/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.manager;

import cz.schovjan.pexeso.gui.Pexeso;
import cz.schovjan.pexeso.model.PropertiesWrapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author schovjan
 */
public class PropertiesManager {

    private static final String APP_VERSION = "appVersion";
    private static final String MAX_STRING = "maxString";
    private static final String MIN_MOVE = "minMove";
    private static final String COMMENT = "Settings and information for PEXESO app";
    private final PropertiesWrapper propertiesWrapper;
    private final String pathFile;
    private final String pathFolder;

    public PropertiesManager(PropertiesWrapper propertiesWrapper) {
        this.propertiesWrapper = propertiesWrapper;
        pathFolder = System.getProperty("user.home") + "/.pexeso/";
        pathFile = pathFolder + "settings.properties";
        // overeni, ze existuje properties s nastavenim, pokud ne tak jej vytvoří
        File f = new File(pathFile);
        if (!f.exists()) {
            createSettingsProperties();
        }
    }

    private void createSettingsProperties() {
        try {
            File f = new File(System.getProperty("user.home") + "/.pexeso/");
            f.mkdir();
            f = new File(pathFile);
            f.createNewFile();
            saveSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSettings() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(pathFile)));
            propertiesWrapper.maxString = Integer.valueOf(prop.getProperty(MAX_STRING));
            propertiesWrapper.minMove = Integer.valueOf(prop.getProperty(MIN_MOVE));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveSettings() {
        try {
            Properties prop = new Properties();
            prop.setProperty(MAX_STRING, String.valueOf(propertiesWrapper.maxString));
            prop.setProperty(MIN_MOVE, String.valueOf(propertiesWrapper.minMove));
            prop.setProperty(APP_VERSION, propertiesWrapper.appVersion);
            prop.store(new FileOutputStream(pathFile), COMMENT);
        } catch (IOException ex) {
            Pexeso.showException(ex);
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
