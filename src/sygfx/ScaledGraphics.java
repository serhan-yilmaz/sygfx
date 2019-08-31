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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import sygfx.util.Anchor;
import sygfx.util.DimensionD;
import sygfx.util.PointD;
import sygfx.util.Rectangle;
import sygfx.util.RectangleD;

/**
 *
 * @author Serhan
 */
public class ScaledGraphics {
    public static int NORTH_ANCHOR = 1;
    public static int NORTHWEST_ANCHOR = 2;
    public static int NORTHEAST_ANCHOR = 3;
    public static int SOUTH_ANCHOR = 4;
    public static int SOUTHWEST_ANCHOR = 5;
    public static int SOUTHEAST_ANCHOR = 6;
    public static int MIDDLE_ANCHOR = 7;
    public static int WEST_ANCHOR = 8;
    public static int EAST_ANCHOR = 9;
    
    private Anchor anchor = Anchor.SOUTHWEST;
    
    private final Graphics g;
    private final Graphics2D g2;
    private final FontRenderContext frc;
    private final Scale s;
    
    private boolean dynamicStroke = false;
    private BasicStroke baseStroke = null;
    private boolean dynamicRectPositioning = false;
    private int dynamicStrokeWidth = 0;
    
//    private class ScaledString{
//        
//        public ScaledString(Font
//        
//        
//    }
    public ScaledGraphics(Graphics g, Scale s){
        this.g = g;
        this.g2 = (Graphics2D) g;
        this.frc = g2.getFontRenderContext();
        this.s = s;
    }
    
    public ScaledGraphics(ScaledGraphics sg, Scale s){
        this.g = sg.g;
        this.g2 = sg.g2;
        this.frc = g2.getFontRenderContext();
        this.s = sg.s.scale(s);
    }
    
    public void setColor(Color c){
        g.setColor(c);
    }
    
    public Color getColor(){
        return g2.getColor();
    }
    
    public void setFont(Font f){
        g.setFont(f);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2){
        g.drawLine(getScale().cX(x1), getScale().cY(y1), getScale().cX(x2), getScale().cY(y2));
    }
    
    public void drawRect(int x, int y, int width, int height){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        if(dynamicStroke && dynamicRectPositioning){
//            r = doRectPositioning(r);
            drawRectLines(r);
        } else {
//            Rectangle r = rd.toRectangle();
            g.drawRect(r.x, r.y, r.width, r.height);
        }
    }
    
    private void drawRectLines(Rectangle r){
        Stroke s = g2.getStroke();
        Color color = g.getColor();
        int hstroke = calculateDynamicStroke(baseStroke, 90);
        int vstroke = calculateDynamicStroke(baseStroke, 0);
//        int hstroke = dynamicStrokeWidth;
//        int vstroke = dynamicStrokeWidth;
        double hwl = (hstroke) / 2.0 - 1;
//        int hwu = hstroke - hwl;
        double hwu = hwl;
        double vwl = (vstroke) / 2.0 - 1;
//        int vwu = vstroke - vwl;
        double vwu = vwl;
        g.setColor(Color.black);
        g.fillRect(r.x - 1, r.y - 1, r.width + 1, vstroke + 1);
        g.fillRect(r.x - 1, r.y - 1 + r.height - vstroke, r.width + 1, vstroke + 1);
        g.fillRect(r.x - 1, r.y - 1, hstroke + 1, r.height + 1);
        g.fillRect(r.x - 1 + r.width - hstroke, r.y - 1, hstroke + 1, r.height + 1);
        
//        adjustDynamicStroke(baseStroke, hstroke);
//        g.drawLine(round(x + hwl), round(y + hwl), round(x + width - hwu) , round(y + hwl));
//        g.drawLine(round(x + hwl), round(y + height - hwu), round(x + width - hwu) , round(y + height - hwu));
//        adjustDynamicStroke(baseStroke, vstroke);
//        g.drawLine(round(x + vwl), round(y + vwl), round(x + vwl), round(y + height - vwu));
//        g.drawLine(round(x + width - vwu), round(y + vwl), round(x + width - vwu), round(y + height - vwu));
//        adjustDynamicStroke(baseStroke, hstroke);
//        g.drawLine(floor(x + hwl), floor(y + hwl), ceil(x + width - hwu) , floor(y + hwl));
//        g.drawLine(floor(x + hwl), ceil(y + height - hwu), ceil(x + width - hwu) , ceil(y + height - hwu));
//        adjustDynamicStroke(baseStroke, vstroke);
//        g.drawLine(floor(x + vwl), floor(y + vwl), floor(x + vwl), ceil(y + height - vwu));
//        g.drawLine(ceil(x + width - vwu), floor(y + vwl), ceil(x + width - vwu), ceil(y + height - vwu));
        g2.setStroke(s);
        g2.setColor(color);
    }
    
    private int floor(double d){
        return (int) Math.floor(d);
    }
    
    private int ceil(double d){
        return (int) Math.ceil(d);
    }
    
    private int round(double d){
        return (int) Math.round(d);
    }
    
    public void fillRect(int x, int y, int width, int height){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        g.fillRect(r.x, r.y, r.width, r.height);
    }
    
    public void drawOval(int x, int y, int width, int height){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        g.drawOval(r.x, r.y, r.width, r.height);
    }
    
    public void fillOval(int x, int y, int width, int height){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        g.fillOval(r.x, r.y, r.width, r.height);
    }
    
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        g.drawArc(r.x, r.y, r.width, r.height, startAngle, arcAngle);
    }
    
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        g.fillArc(r.x, r.y, r.width, r.height, startAngle, arcAngle);
    }
    
    public void drawString(String text, int x, int y){
        drawString(g, getScale(), text, x, y, anchor);
    }
    
    public static void drawString(Graphics g, Scale s, String text, int x, int y, Anchor target_anchor){
        Graphics2D g2 = (Graphics2D) g;
        Font myfont = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        int fx = (int) (myfont.getSize() * s.getXScaling());
        int fy = (int) (myfont.getSize() * s.getYScaling());
        Font newFont = myfont.deriveFont((float)Math.min(fx, fy));
        g.setFont(newFont);
        GlyphVector gv = newFont.createGlyphVector(frc, text);
        Rectangle2D box = gv.getVisualBounds();
        Dimension dim = new Dimension((int) box.getWidth(), (int) box.getHeight());
        Point p = new Point(s.cX(x), s.cY(y));
        Point p1 = target_anchor.transform(p, dim, Anchor.SOUTHWEST);
//        Point p2 = target_anchor.transform(p, dim, Anchor.NORTHWEST);
        
//        Rectangle r = transform(x, y, (int) box.getWidth(), (int) box.getHeight(), Anchor.SOUTHWEST, target_anchor, s).toRectangle();
//        Rectangle r2 = transform(x, y, (int) box.getWidth(), (int) box.getHeight(), Anchor.NORTHWEST, target_anchor, s).toRectangle();
//        g2.setStroke(new BasicStroke(1));
//        g2.drawRect(p2.x, p2.y, dim.width, dim.height);
//        g2.setFont(newFont);
        g2.drawString(text, p1.x, p1.y);
        g2.setFont(myfont);
    }
    
    public Rectangle2D getEstimatedTextArea(String text){
        Font font = g2.getFont();
        GlyphVector gv = font.createGlyphVector(frc, text);
        Rectangle2D box = gv.getVisualBounds();
        return box;
    }
    
    public void drawStringArea(String text, int x, int y, int width, int height){
        int w = getScale().cXD(width);
        int h = getScale().cYD(height);
        Font myfont = g2.getFont();
        GlyphVector gv = myfont.createGlyphVector(frc, text);
        Rectangle2D box = gv.getVisualBounds(); 
        int fx = (int) (Math.floor(0.9 * myfont.getSize2D() * w / box.getWidth()));
        int fy = (int) (Math.floor(0.9 * myfont.getSize2D() * h / box.getHeight()));
        float fsize = (float) Math.min(fx, fy);
        Font newFont = myfont.deriveFont(fsize);
        g.setFont(newFont);
        gv = newFont.createGlyphVector(frc, text);
        box = gv.getVisualBounds();
        if(box.getWidth() > w || box.getHeight() > h){
            newFont = myfont.deriveFont(fsize - 1);
            g.setFont(newFont);
            gv = newFont.createGlyphVector(frc, text);
            box = gv.getVisualBounds();
        }
        
        Rectangle r = transform(x, y, (int) box.getWidth(), (int) box.getHeight(), Anchor.NORTHWEST).toRectangle();
        g2.drawString(text, r.x, r.y);
        g2.setFont(myfont);
    }
    
    public void setClip(int x, int y, int width, int height){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        g.setClip(r.x, r.y, r.width, r.height);
    }
    
    public void setClip(Rectangle r){
        setClip(r.x, r.y, r.width, r.height);
    }
    
    public void setClip(Shape clip){
        g.setClip(clip);
    }
    
    public void clipRect(int x, int y, int width, int height){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        g.clipRect(r.x - 1, r.y - 1, r.width + 2, r.height + 2);
    }
    
    public void clipRect(Rectangle r){
        clipRect(r.x, r.y, r.width, r.height);
    }
    
    public void clearClip(){
        g.setClip(null);
    }
    
    public Shape getClip(){
        return g.getClip();
    }
    
    /**
     * @param anchor the anchor to setdynamicRectPositioningset
     */
    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }
    
    public Anchor getAnchor(){
        return anchor;
    }
    
    public void setStroke(Stroke s){
        g2.setStroke(s);
        dynamicStrokeWidth = 0;
        dynamicStroke = false;
    }
    
    public Stroke getStroke(){
        return g2.getStroke();
    }
    
    public void setDynamicStroke(BasicStroke s){
        baseStroke = s;
        dynamicStroke = true;
        float w = s.getLineWidth();
        int m = Math.min(this.getScale().cXD(w) , this.getScale().cYD(w));
        adjustDynamicStroke(s, m);
    }
    
    private int calculateDynamicStroke(BasicStroke s, double angle){
        float w = s.getLineWidth();
        double xm = (this.getScale().cXDdouble(w)) * Math.sin(Math.toRadians(angle));
        double ym = (this.getScale().cYDdouble(w)) * Math.cos(Math.toRadians(angle));
        int m = (int) Math.ceil(Math.sqrt(xm * xm + ym * ym));
        return m;
    }
    
    private void adjustDynamicStroke(BasicStroke s, int m){
        dynamicStrokeWidth = m;
        Stroke dynamicStroke = new BasicStroke(m, s.getEndCap(), 
                s.getLineJoin(), s.getMiterLimit(), 
                s.getDashArray(), s.getDashPhase());
        g2.setStroke(dynamicStroke);
    }
    
    public void setComposite(Composite comp){
        g2.setComposite(comp);
    }
    
    public void drawImage(Image img, int x, int y, int width, int height, ImageObserver io){
        Rectangle r = transform(x, y, width, height, Anchor.NORTHWEST).toRectangle();
        g.drawImage(img, r.x, r.y, r.width, r.height, io);
    }
    
    public void drawImage(Image img, int x, int y, ImageObserver io){
        drawImage(img,x,y,img.getWidth(io), img.getHeight(io), io);
    }
    
    private RectangleD transform(Rectangle r, Anchor source_anchor){
        return transform(r.x, r.y, r.width, r.height, source_anchor);
    }
    
    private RectangleD transform(int x, int y, int width, int height, Anchor source_anchor){
        return getScale().transform(x, y, width, height, source_anchor, anchor);
    }

    /**
     * @return the dynamicPositioning
     */
    public boolean isDynamicRectPositioning() {
        return dynamicRectPositioning;
    }

    /**
     * @param dynamicRectPositioning the dynamicRectPositioning to set
     */
    public void setDynamicRectPositioning(boolean dynamicRectPositioning) {
        this.dynamicRectPositioning = dynamicRectPositioning;
    }
    
    public FontRenderContext getFontRenderContext(){
        return frc;
    }

    /**
     * @return the scale s
     */
    public Scale getScale() {
        return s;
    }
    
    
}
