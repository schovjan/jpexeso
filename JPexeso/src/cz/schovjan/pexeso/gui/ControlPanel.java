/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author schovjan
 */
public class ControlPanel extends JPanel {

    private final JPanel left;
    private final JPanel right;
    private final JLabel score1;
    private final JLabel score2;
    private final JLabel stringOfSuccess1;
    private final JLabel stringOfSuccess2;

    public ControlPanel() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;

        left = new JPanel();
        left.setLayout(new GridBagLayout());
        right = new JPanel();
        right.setLayout(new GridBagLayout());
        add(left, gbc);
        add(right, gbc);

        Font fontBig = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        Font fontSmall = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

        score1 = new JLabel();
        score1.setFont(fontBig);
        score2 = new JLabel();
        score2.setFont(fontBig);

        stringOfSuccess1 = new JLabel();
        stringOfSuccess1.setFont(fontSmall);
        stringOfSuccess2 = new JLabel();
        stringOfSuccess2.setFont(fontSmall);

        gbc.insets = new Insets(4, 20, 4, 20);
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        left.add(score1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        left.add(stringOfSuccess1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        right.add(score2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        right.add(stringOfSuccess2, gbc);
    }

    public void playerOnMove(int value) {
        if (value % 2 == 0) {
            left.setBackground(Color.green);
            right.setBackground(null);
        } else {
            right.setBackground(Color.green);
            left.setBackground(null);
        }
    }

    public void setScore1(int value) {
        score1.setText(String.format("Skóre: %d", value));
    }

    public void setScore2(int value) {
        score2.setText(String.format("Skóre: %d", value));
    }

    public void setStringOfSuccess1(int stringOfSuccess) {
        stringOfSuccess1.setText(String.format("Řada: %d", stringOfSuccess));
    }

    public void setStringOfSuccess2(int stringOfSuccess) {
        stringOfSuccess2.setText(String.format("Řada: %d", stringOfSuccess));
    }
}
