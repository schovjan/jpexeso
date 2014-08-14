/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

/**
 *
 * @author schovjan
 */
public class InformationDialog extends JDialog {

    public InformationDialog(Pexeso pexeso) {
        super(pexeso);
        //
        setSize(new Dimension(550, 400));
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setTitle("Informace");
        //
        try {
            Font fontBig = new Font(Font.SANS_SERIF, Font.BOLD, 18);

            JEditorPane ep = new JEditorPane(ClassLoader.getSystemResource("resource/information.html"));
            ep.setEditable(false);

            JLabel icon = new JLabel(new ImageIcon(getToolkit().getImage(getClass().getResource("/resource/ico32.png"))));
            JLabel title = new JLabel("PEXESO");
            title.setFont(fontBig);

            GroupLayout layout = new GroupLayout(getContentPane());
            setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(icon)
                            .addComponent(title))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ep)
                    )
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(icon)
                            .addComponent(title)
                    )
                    .addComponent(ep)
            );
        } catch (IOException e) {
            e.printStackTrace();
            Pexeso.showException(e);
        }
    }
}
