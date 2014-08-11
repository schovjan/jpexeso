/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.gui;

import java.awt.Dialog;

/**
 *
 * @author schovjan
 */
public class InformationDialog extends Dialog {

    Pexeso pexeso;

    public InformationDialog(Pexeso pexeso) {
        super(pexeso);
        this.pexeso = pexeso;
        //
        setLocationRelativeTo(pexeso);
        setModal(true);
        setTitle("Informace");
    }
}
