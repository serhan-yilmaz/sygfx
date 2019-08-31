/*
 * Copyright (C) 2016 Serhan Yılmaz
 *
 * This file is part of FamilyTree
 * 
 * FamilyTree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FamilyTree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sygfx;

import java.awt.Point;
import sygfx.util.Anchor;
import sygfx.util.DimensionD;
import sygfx.util.PointD;
import sygfx.util.Rectangle;
import sygfx.util.RectangleD;

/**
 *
 * @author Serhan
 */
public class Scale {
    /**
     * Default scaling. X and Y coordinates are left unchanged with unity scaling.
     * It is equivalent to create new Scale(0,0,1,1);
     */
    public static final Scale UNITY = new Scale(0,0,1,1);
    
    private final double xShift;
    private final double yShift;
    private final double xScaling;
    private final double yScaling;
    /**
        Partial constructor. xScaling and yScaling parameters default to 1.
     * @param xShift shift in x axis based on global coordinates.
     * @param yShift shift in y axis based on global coordinates.
    */
    public Scale(double xShift, double yShift){
        this.xShift = xShift;
        this.yShift = yShift;
        this.xScaling = 1;
        this.yScaling = 1;
    }
    
    public Scale(double xShift, double yShift, 
            double xScaling, double yScaling){
        this.xShift = xShift;
        this.yShift = yShift;
        this.xScaling = xScaling;
        this.yScaling = yScaling;
        if(xScaling == 0 || yScaling == 0){
            throw new IllegalArgumentException("Scaling is Zero.");
        }
    }
    /**
     * @return the xShift
     */
    public double getXShift() {
        return xShift;
    }

    /**
     * @return the yShift
     */
    public double getYShift() {
        return yShift;
    }

    /**
     * @return the xScaling
     */
    public double getXScaling() {
        return xScaling;
    }

    /**
     * @return the yScaling
     */
    public double getYScaling() {
        return yScaling;
    }
    
    public double icX(double x){
        return (x - xShift) / getXScaling();
    }
    
    public double icY(double y){
        return (y - yShift) / getYScaling();
    }
    
    public double icX(int x){
        return (x - xShift) / getXScaling();
    }
    
    public double icY(int y){
        return (y - yShift) / getYScaling();
    }
    
    public int convertX(double x){
        return (int) Math.round((x) * getXScaling() + xShift);
    }
    
    public int convertY(double y){
        return (int) Math.round((y) * getYScaling() + yShift);
    }
    
    public int cX(double x){
        return convertX(x);
    }
    
    public int cY(double y){
        return convertY(y);
    }
    
    // Deprecated
    public int cXdr(double x){
        return (int) Math.round((x) * getXScaling());
    }
    // Deprecated
    public int cYdr(double y){
        return (int) Math.round((y) * getYScaling());
    }
    
    public int cXD(double x){
        return (int) Math.ceil(Math.abs((x) * getXScaling()));
    }
    
    public int cYD(double y){
        return (int) Math.ceil(Math.abs((y) * getYScaling()));
    }
    
    public double cXdouble(double x){
        return (x) * getXScaling() + xShift;
    }
    
    public double cYdouble(double y){
        return (y) * getYScaling() + yShift;
    }
    
   public double cXDdouble(double x){
        return Math.abs((x) * getXScaling());
    }
    
    public double cYDdouble(double y){
        return Math.abs((y) * getYScaling());
    }
    
    /**
        * Relatively scales this object with respect to input paramater Scale. 
        * Both this object and input remains unaffected and a new object is created.
        * 
     * @param target_scale The target Scale parameter relative to the current 
     * scale.
     * @return New Scale describing the target scale relative to scale of unity.
    */
    public Scale scale(Scale target_scale){
        double xs = (target_scale.xShift * xScaling + xShift);
        double ys = (target_scale.yShift * yScaling + yShift);
        double xSca = target_scale.xScaling * xScaling;
        double ySca = target_scale.yScaling * yScaling;
        return new Scale(xs,ys,xSca,ySca);
    }
    
    public Scale scale(double xScaling, double yScaling){
        double xs = (xShift);
        double ys = (yShift);
        double xSca = xScaling * this.xScaling;
        double ySca = yScaling * this.yScaling;
        return new Scale(xs,ys,xSca,ySca);
    }
    
    public Scale scaleX(double xScaling){
        double xSca = xScaling * this.xScaling;
        return new Scale(xShift,yShift,xSca,yScaling);
    }
    
    public Scale scaleY(double yScaling){
        double ySca = yScaling * this.yScaling;
        return new Scale(xShift,yShift,xScaling,ySca);
    }
    
    public Scale shift(double xShift, double yShift){
        return new Scale(xShift * xScaling + this.xShift, 
                yShift * yScaling + this.yShift, xScaling, yScaling);
    }
    
    public Scale setShift(double xShift, double yShift){
        return new Scale(xShift, yShift, xScaling, yScaling);
    }
    
    public Scale setXShift(double xShift){
        return new Scale(xShift, yShift, xScaling, yScaling);
    }
    
    public Scale setXShift(double sx, double dx){
        double xShift = dx - sx * xScaling;
        return new Scale(xShift, yShift, xScaling, yScaling);
    }
    
    public Scale setYShift(double yShift){
        return new Scale(xShift, yShift, xScaling, yScaling);
    }
    
    public Scale setYShift(double sy, double dy){
        double yShift = dy - sy * yScaling;
        return new Scale(xShift, yShift, xScaling, yScaling);
    }
    
    public Scale setXScale(double xScaling){
        return new Scale(xShift, yShift, xScaling, yScaling);
    }
    
    public Scale setYScale(double yScaling){
        return new Scale(xShift, yShift, xScaling, yScaling);
    }
    
    public Scale inverse(){
        return new Scale(-xShift / xScaling, 
                -yShift / yScaling, 1 / xScaling, 1 / yScaling);
    }
    
    public void print(){
        System.out.println(toString());
    }
    
    public Scale getScaling(){
        return new Scale(0,0, xScaling, yScaling);
    }
    
    public static Scale create(double sx, double sy, double dx, double dy, 
            double xScale, double yScale){
        double xShift = dx - sx * xScale;
        double yShift = dy - sy * yScale;
        return new Scale(xShift, yShift, xScale, yScale);
    }
    
    public static Scale create(double sx, double sy, double dx, double dy, 
            double sx2, double sy2, double dx2, double dy2){
        double xScale = (dx - dx2) / (sx - sx2);
        double yScale = (dy - dy2) / (sy - sy2);
        double xShift = dx - sx * xScale;
        double yShift = dy - sy * yScale;
        return new Scale(xShift, yShift, xScale, yScale);
    }
    
    @Override
    public String toString(){
        return "xShift : " + xShift + ", yShift : " + yShift + 
                ", xScale : " + xScaling + ", yScale : " + yScaling;
    }
    
    public RectangleD transform(Rectangle r){
        return transform(r, Anchor.SOUTHWEST, Anchor.SOUTHWEST, this);
    }
    
    public RectangleD transform(Rectangle r, 
            Anchor source_anchor, Anchor target_anchor){
        return transform(r, source_anchor, target_anchor, this);
    }
    
    public RectangleD transform(int x, int y, int width, int height){
        return transform(x, y, width, height, 
                Anchor.SOUTHWEST, Anchor.SOUTHWEST, this);
    }
    
    public RectangleD transform(int x, int y, int width, int height, 
            Anchor source_anchor, Anchor target_anchor){
        return transform(x, y, width, height, source_anchor, target_anchor, this);
    }
    
    private static RectangleD transform(Rectangle r, 
            Anchor source_anchor, Anchor target_anchor, Scale s){
        return transform(r.x, r.y, r.width, r.height, source_anchor, target_anchor, s);
    }
    
    private static RectangleD transform(int x, int y, int width, int height, 
            Anchor source_anchor, Anchor target_anchor, Scale s){
        double w = s.cXDdouble(width);
        double h = s.cYDdouble(height);
        DimensionD d = new DimensionD(w, h);
        PointD p = transform(x, y, s);
//        PointD p = new PointD(s.cXdouble(x), s.cYdouble(y));
        p = target_anchor.transform(p, d, source_anchor);
        return new RectangleD(p.x, p.y, w, h);
    }
    
    public PointD transform(Point p){
        return transform(p.x, p.y, this);
    }
    
    public PointD transform(int x, int y){
        return transform(x, y, this);
    }
    
    public static PointD transform(int x, int y, Scale s){
        PointD p = new PointD(s.cXdouble(x), s.cYdouble(y));
        return p;
    }   
    
}
