package cz.schovjan.pexeso.gui;

import cz.schovjan.pexeso.manager.Manager;
import cz.schovjan.pexeso.gui.listener.PexesoMouseListener;
import cz.schovjan.pexeso.util.Constant;
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
    private final Manager manager;
    private final JPanel content;

    public Pexeso() throws IOException {
        super();
        ControlPanel control = new ControlPanel();
        manager = new Manager(this, control);
        manager.loadCells();
        if (manager.getCells().isEmpty()) {
            Pexeso.showErrorMessage("Do složky \".pexeso\" v domovském adresáři nahrajte obrázky ve formátu JPG.");
            System.exit(0);
        }
        //
        setTitle(Constant.APP_NAME);
        setSize(windowSize);
        setPreferredSize(windowSize);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(getToolkit().getImage(getClass().getResource(Constant.PATH_ICON)));
        //
        addMouseListener(new PexesoMouseListener(this, manager));
        //
        add(control, BorderLayout.NORTH);
        content = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(content, BorderLayout.CENTER);
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

    public void showInformationDialog() {
        InformationDialog informationDialog = new InformationDialog(this);
        informationDialog.setVisible(true);
    }

    public static void showInfoMessage(String textMessage) {
        JOptionPane.showMessageDialog(null, "\n" + textMessage + "\n\n", "Pexeso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showAlertMessage(String textMessage) {
        JOptionPane.showMessageDialog(null, "\n" + textMessage + "\n\n", "Pexeso", JOptionPane.WARNING_MESSAGE);
    }

    public static void showErrorMessage(String textMessage) {
        JOptionPane.showMessageDialog(null, "\n" + textMessage + "\n\n", "Pexeso", JOptionPane.ERROR_MESSAGE);
    }

    public static void showException(Exception ex) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = ex.getStackTrace();
        for (StackTraceElement ste : stackTrace) {
            sb.append(ste.toString());
        }
        showErrorMessage(ex.getMessage() + "\n\n\n" + sb.toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Pexeso pexeso = new Pexeso();
        pexeso.setVisible(true);
    }
}
