package cz.schovjan.pexeso.gui.listener;

import cz.schovjan.pexeso.gui.Cell;
import cz.schovjan.pexeso.gui.menu.PexesoPopupMenu;
import cz.schovjan.pexeso.manager.Manager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author schovjan
 */
public class CellMouseListener extends MouseAdapter {

    private Cell cell;
    private Manager manager;
    private PexesoPopupMenu popupMenu;

    public CellMouseListener(Cell cell, Manager manager) {
        super();
        this.cell = cell;
        this.manager = manager;
        this.popupMenu = new PexesoPopupMenu(manager);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (cell.isFinded() || cell.isShowImage()) {
                return;
            }
            manager.turnCell(cell);
            cell.refresh();
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

}
