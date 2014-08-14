/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.manager;

import cz.schovjan.pexeso.gui.Pexeso;
import cz.schovjan.pexeso.gui.Cell;
import cz.schovjan.pexeso.gui.ControlPanel;
import cz.schovjan.pexeso.model.Game;
import cz.schovjan.pexeso.model.PropertiesWrapper;
import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author schovjan
 */
public final class Manager {

    private Pexeso pexeso;
    private ControlPanel control;
    private List<Cell> cells;
    private int stringOfSuccess;
    private Cell cell1;
    private Cell cell2;
    private Game game;
    private PropertiesManager propManager;
    private PropertiesWrapper properties;

    public Manager(Pexeso pexeso, ControlPanel control) throws IOException {
        this.pexeso = pexeso;
        this.control = control;
        //
        properties = new PropertiesWrapper();
        propManager = new PropertiesManager(properties);
        propManager.loadSettings();
    }

    public void newGame() {
        game = new Game();
        cell1 = null;
        cell2 = null;
        stringOfSuccess = 0;
        control.setScore1(game.getPlayer1().getScore());
        control.setScore2(game.getPlayer2().getScore());
        control.setStringOfSuccess1(game.getPlayer1().getStringOfSuccess());
        control.setStringOfSuccess2(game.getPlayer2().getStringOfSuccess());
        control.playerOnMove(game.getPlayerOnMove());

        for (Cell c : getCells()) {
            c.setFinded(false);
            c.setShowImage(false);
        }
        pexeso.newGame();
    }

    public void turnCell(Cell cell) {
        if (cell1 == null) {
            cell1 = cell;
            cell.setShowImage(true);
        } else if (cell2 == null) {
            cell2 = cell;
            cell.setShowImage(true);
            game.moveIncrement();
            validate();
            new Timer().schedule(new TurnBackTimerTask(), 1700);
        }
    }

    public void validate() {
        if (isPair()) {
            stringOfSuccess++;
            if (game.getPlayerOnMove() % 2 == 0) {
                game.getPlayer1().setStringOfSuccess(Math.max(game.getPlayer1().getStringOfSuccess(), stringOfSuccess));
                control.setScore1(game.getPlayer1().scoreIncrement());
                control.setStringOfSuccess1(game.getPlayer1().getStringOfSuccess());
            } else {
                game.getPlayer2().setStringOfSuccess(Math.max(game.getPlayer2().getStringOfSuccess(), stringOfSuccess));
                control.setScore2(game.getPlayer2().scoreIncrement());
                control.setStringOfSuccess2(game.getPlayer2().getStringOfSuccess());
            }
        }
    }

    private boolean isPair() {
        return cell1.getImageName().equals(cell2.getImageName());
    }

    public void loadCells() {
        try {
            cells = new ArrayList();
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
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            cells = new ArrayList();
        }
    }

    public List<Cell> getCells() {
        return this.cells;
    }

    public void showInformation() {
        pexeso.showInformationDialog();
    }

    private void endOfGame() {
        properties.maxString = Collections.max(Arrays.asList(properties.maxString, game.getPlayer1().getStringOfSuccess(), game.getPlayer2().getStringOfSuccess()));
        properties.minMove = Math.min(properties.minMove, game.getCountOfMove());
        propManager.saveSettings();
        Pexeso.showInfoMessage(String.format("Počet tahů: %d\n\nMinimální počet tahů: %d\nMaximální řad: %d", game.getCountOfMove(), properties.minMove, properties.maxString));
    }

    /**
     * Otoceni karticek zpet nebo jejich zmizeni z hraci plochy
     */
    class TurnBackTimerTask extends TimerTask {

        @Override
        public void run() {
            if (isPair()) {
                cell1.setFinded(true);
                cell2.setFinded(true);
            } else {
                cell1.setShowImage(false);
                cell2.setShowImage(false);
                stringOfSuccess = 0;
                control.playerOnMove(game.playerOnMoveIncrement());
            }
            cell1.refresh();
            cell2.refresh();
            cell1 = null;
            cell2 = null;

            if (game.getTurnedPairsCount() == cells.size() / 2) {
                endOfGame();
            }
        }
    }
}
