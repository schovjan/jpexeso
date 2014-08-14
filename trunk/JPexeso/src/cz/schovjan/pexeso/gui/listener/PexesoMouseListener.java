/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui.listener;

import cz.schovjan.pexeso.manager.Manager;
import cz.schovjan.pexeso.gui.Pexeso;
import cz.schovjan.pexeso.gui.menu.PexesoPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author schovjan
 */
public class PexesoMouseListener extends MouseAdapter {

    private Pexeso pexeso;
    private Manager manager;
    private PexesoPopupMenu popupMenu;

    public PexesoMouseListener(Pexeso pexeso, Manager manager) {
        this.pexeso = pexeso;
        this.popupMenu = new PexesoPopupMenu(manager);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
