/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
        right = new JPanel();
        add(left, gbc);
        add(right, gbc);

        score1 = new JLabel();
        score2 = new JLabel();
        
        stringOfSuccess1 = new JLabel();
        stringOfSuccess2 = new JLabel();

        left.add(new JLabel("Skóre: "));
        left.add(score1);

        left.add(new JLabel("Řada: "));
        left.add(stringOfSuccess1);

        right.add(new JLabel("Skóre: "));
        right.add(score2);

        right.add(new JLabel("Řada: "));
        right.add(stringOfSuccess2);
    }

    public void setScore1(int value) {
        score1.setText(String.valueOf(value));
    }

    public void setScore2(int value) {
        score2.setText(String.valueOf(value));
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

    public void setStringOfSuccess1(int stringOfSuccess) {
        stringOfSuccess1.setText(String.valueOf(stringOfSuccess));
    }

    public void setStringOfSuccess2(int stringOfSuccess) {
        stringOfSuccess2.setText(String.valueOf(stringOfSuccess));
    }
}
