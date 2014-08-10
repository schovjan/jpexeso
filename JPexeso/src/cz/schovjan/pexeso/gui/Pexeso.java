/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui;

import cz.schovjan.pexeso.Manager;
import cz.schovjan.pexeso.gui.listener.PexesoMouseListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author schovjan
 */
public class Pexeso extends JFrame {

    private final Dimension windowSize = new Dimension(600, 600);
    private Manager manager;
    private JPanel content;

    public Pexeso() throws IOException {
        super();
        ControlPanel control = new ControlPanel();
        manager = new Manager(this, control);
        //
        setTitle("Pexeso");
        setMaximumSize(windowSize);
        setSize(windowSize);
        setPreferredSize(windowSize);
        setMinimumSize(windowSize);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //
        addMouseListener(new PexesoMouseListener(this, manager));
        //
        add(control, BorderLayout.NORTH);
        content = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(content, BorderLayout.CENTER);
        manager.loadCells();
        //
        manager.newGame();
    }

    public void newGame() {
        content.removeAll();

        Collections.shuffle(manager.getCells());
        for (Cell cell : manager.getCells()) {
            content.add(cell);
        }
        revalidate();
        repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Pexeso pexeso = new Pexeso();
        pexeso.setVisible(true);
    }

    public void end(int countOfMove) {
        JOptionPane.showMessageDialog(null, "Počet tahů: " + countOfMove, "Pexeso", JOptionPane.INFORMATION_MESSAGE);
    }

}
