/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui;

import java.awt.Color;

/**
 *
 * @author Serhan
 */
public class Window extends Container{
    private static final Color DEFAULT_COLOR = new Color(235, 235, 235);
    
    public Window(){
        super();
        this.backColor = DEFAULT_COLOR;
    }
    
    public Window(int width, int height){
        super(width, height);
        this.backColor = DEFAULT_COLOR;
    }
    
    public void pack(){
        this.preferredSize = getPreferredSize();
        this.size = this.preferredSize;
        validateTree();
    }
    
}
