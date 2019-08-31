/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.util;

import java.awt.Point;

/**
 *
 * @author Serhan
 */
public class PointD {
    public double x;
    public double y;
    
    public PointD(){
        x = 0;
        y = 0;
    }
    
    public PointD(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }
    
    public Point toPoint(){
        return new Point((int) Math.round(x), (int) Math.round(y));
    }
}
