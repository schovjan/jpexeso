/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui.menu;

import cz.schovjan.pexeso.Manager;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author schovjan
 */
public class PexesoPopupMenu extends JPopupMenu {

    private Manager manager;

    public PexesoPopupMenu(Manager manager) {
        this.manager = manager;
        add(new JMenuItem(new AbstractAction("Nová hra") {

            @Override
            public void actionPerformed(ActionEvent e) {
                manager.newGame();
            }
        }));
        add(new JMenuItem(new AbstractAction("Informace") {

            @Override
            public void actionPerformed(ActionEvent e) {
                manager.showInformation();
            }
        }));
        add(new JMenuItem(new AbstractAction("Konec") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }));
    }
}
