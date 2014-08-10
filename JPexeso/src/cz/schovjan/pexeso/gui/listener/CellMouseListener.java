/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui.listener;

import cz.schovjan.pexeso.gui.Cell;
import cz.schovjan.pexeso.Manager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author schovjan
 */
public class CellMouseListener extends MouseAdapter {

    private Cell cell;
    private Manager manager;

    public CellMouseListener(Cell cell, Manager manager) {
        super();
        this.cell = cell;
        this.manager = manager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (cell.isFinded() || cell.isShowImage()) {
            return;
        }
        manager.turnCell(cell);
        cell.refresh();
    }

}
