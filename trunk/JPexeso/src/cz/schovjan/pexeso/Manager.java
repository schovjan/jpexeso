/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso;

import cz.schovjan.pexeso.gui.Pexeso;
import cz.schovjan.pexeso.gui.Cell;
import cz.schovjan.pexeso.gui.ControlPanel;
import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author schovjan
 */
public class Manager {

    private Pexeso pexeso;
    private ControlPanel control;
    private List<Cell> cells;
    private int score1;
    private int score2;
    private int playerOnMove;
    private int stringOfSuccess;
    private int stringOfSuccess1;
    private int stringOfSuccess2;
    private int countOfMove;

    private Cell cell1;
    private Cell cell2;

    public Manager(Pexeso pexeso, ControlPanel control) {
        this.pexeso = pexeso;
        this.control = control;
        score1 = 0;
        score2 = 0;
        cell1 = null;
        cell2 = null;
        playerOnMove = 0;
        stringOfSuccess = 0;
        stringOfSuccess1 = 0;
        stringOfSuccess2 = 0;
        countOfMove = 0;
        control.playerOnMove(playerOnMove);
        this.cells = loadCells();
    }

    public void turnCell(Cell cell) {
        if (cell1 == null) {
            cell1 = cell;
            cell.setShowImage(true);
        } else if (cell2 == null) {
            cell2 = cell;
            countOfMove++;
            cell.setShowImage(true);
            if (validate()) {
                stringOfSuccess++;
                if (playerOnMove % 2 == 0) {
                    stringOfSuccess1 = Math.max(stringOfSuccess1, stringOfSuccess);
                    control.setScore1(++score1);
                    control.setStringOfSuccess1(stringOfSuccess1);
                } else {
                    stringOfSuccess2 = Math.max(stringOfSuccess2, stringOfSuccess);
                    control.setScore2(++score2);
                    control.setStringOfSuccess2(stringOfSuccess2);
                }
            }
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            if (validate()) {
                                cell1.setFinded(true);
                                cell2.setFinded(true);
                            } else {
                                cell1.setShowImage(false);
                                cell2.setShowImage(false);
                                stringOfSuccess = 0;
                                control.playerOnMove(++playerOnMove);
                            }
                            cell1.refresh();
                            cell2.refresh();
                            cell1 = null;
                            cell2 = null;

                            if (score1 + score2 == cells.size() / 2) {
                                pexeso.end(countOfMove);
                            }
                        }
                    },
                    1700);
        }
    }

    public boolean validate() {
        return cell1.getImageName().equals(cell2.getImageName());
    }

    public void newGame() {
        cell1 = null;
        cell2 = null;
        playerOnMove = 0;
        countOfMove = 0;
        stringOfSuccess = 0;
        stringOfSuccess1 = 0;
        stringOfSuccess2 = 0;
        score1 = 0;
        score2 = 0;
        control.setScore1(score1);
        control.setScore2(score2);
        control.setStringOfSuccess1(stringOfSuccess1);
        control.setStringOfSuccess2(stringOfSuccess2);
        control.playerOnMove(playerOnMove);

        for (Cell c : getCells()) {
            c.setFinded(false);
            c.setShowImage(false);
        }
        pexeso.newGame();
    }

    public List<Cell> getCells() {
        return this.cells;
    }

    public List loadCells() {
        try {
            List cells = new ArrayList();
            File folder = new File(System.getProperty("user.home") + "/.pexeso");
            File[] listFiles = folder.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".JPG");
                }
            });
            for (File file : listFiles) {
                Image img = ImageIO.read(file);
                Cell cell = new Cell(img, file.getName(), this);
                cells.add(cell);
                cells.add(cell.getCopy());
            }
            return cells;
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<Cell>();
    }
}
