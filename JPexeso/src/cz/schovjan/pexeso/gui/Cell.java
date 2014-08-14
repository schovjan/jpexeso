/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui;

import cz.schovjan.pexeso.manager.Manager;
import cz.schovjan.pexeso.gui.listener.CellMouseListener;
import cz.schovjan.pexeso.util.ImgUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

/**
 *
 * @author schovjan
 */
public class Cell extends Panel {

    private final int width = 147; //rozmer v px
    private final int height = (int) ((double) width * (1d - 1d / 3d)); //rozmer v px
    private Image image;
    private String imageName;
    private boolean showImage;
    private Manager manager;
    private boolean finded;

    public Cell(Image image, String imageName, Manager manager) {
        this(image, imageName, manager, false);
    }

    public Cell(Image image, String imageName, Manager manager, boolean showImage) {
        super();
        this.image = ImgUtils.scaleImage(width, height, image);
        this.imageName = imageName;
        this.manager = manager;
        this.showImage = showImage;
        this.finded = false;

        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        addMouseListener(new CellMouseListener(this, manager));
    }

    public void refresh() {
        revalidate();
        repaint();
    }

    public Cell getCopy() {
        Cell copy = new Cell(image, imageName, manager);
        copy.setShowImage(isShowImage());
        copy.setFinded(false);
        return copy;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
    }

    public boolean isShowImage() {
        return showImage;
    }

    public void setFinded(boolean finded) {
        this.finded = finded;
    }

    public boolean isFinded() {
        return finded;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public void paint(Graphics g) {
        if (isFinded()) {
            //nic
        } else if (showImage) {
            g.drawImage(image, 0, 0, null);
        } else {
            g.setColor(Color.GRAY);
            g.drawRect(0, 0, width - 1, height - 1);
            g.drawRect(1, 1, width - 3, height - 3);
        }
    }
}
