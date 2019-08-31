/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.util;

/**
 *
 * @author Serhan
 */
public class RectangleD {
    public final double x;
    public final double y;
    public final double width;
    public final double height;
    
    public RectangleD(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }
    
    public Rectangle toRectangle(){
        return new Rectangle(round(x), round(y), round(width), round(height));
    }
    
    private int round(double d){
        return (int) Math.round(d);
    }
    
    
}
