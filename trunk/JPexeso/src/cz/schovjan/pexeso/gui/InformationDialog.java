package cz.schovjan.pexeso.gui;

import cz.schovjan.pexeso.util.Constant;
import java.awt.Dimension;
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
            JEditorPane ep = new JEditorPane(ClassLoader.getSystemResource(Constant.PATH_INFORMATION));
            ep.setEditable(false);

            JLabel icon = new JLabel(new ImageIcon(getToolkit().getImage(getClass().getResource(Constant.PATH_ICON))));
            JLabel title = new JLabel(Constant.APP_NAME);
            title.setFont(Constant.FONT_BIG);

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
