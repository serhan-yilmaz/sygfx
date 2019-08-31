/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.util;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Serhan
 */
public class Rectangle {
    public final int x;
    public final int y;
    public final int width;
    public final int height;
    
    public Rectangle(Rectangle r){
        this.x = r.x;
        this.y = r.y;
        this.width = r.width;
        this.height = r.height;
    }
    
    public Rectangle(Point p, Dimension d){
        this.x = p.x;
        this.y = p.y;
        this.width = d.width;
        this.height = d.height;
    }
    
    public Rectangle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }
    
    public Point getPoint(){
        return new Point(x, y);
    }
    
    public Dimension getDimension(){
        return new Dimension(width, height);
    }
    
}
