/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import view.SewaAlatView;
import controller.SewaAlatController;

/**
 *
 * @author MyBook Z Series
 */
public class Main {

    public static void main(String[] args) {

        SewaAlatView view = new SewaAlatView();
        SewaAlatController controller = new SewaAlatController(view);

        controller.jalankanProgram();

    }
}
