/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui;

import sygfx.ScaledGraphics;

/**
 *
 * @author Serhan
 */
public class Panel extends Container{
    
    public Panel(){
        super();
    }
    
    public Panel(int width, int height) {
        super(width, height);
    }
    
    @Override
    protected void paintComponent(ScaledGraphics g){
        super.paintComponent(g);
    }
    
}
