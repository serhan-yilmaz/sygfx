/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import sygfx.util.Anchor;

/**
 *
 * @author Serhan
 */
public class Layer {
    private final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private final Scale basicScale;
    private final int WIDTH;
    private final int HEIGHT;
    private final int colorType;
    
    private Scale extendedScale;
    private BufferedImage img;
    private Graphics2D gImg;
    
    public Layer(Scale s, int WIDTH, int HEIGHT){
        this.basicScale = s;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.colorType = BufferedImage.TYPE_INT_ARGB;
        setExtendedScale(Scale.UNITY);
    }
    
    public Layer(Scale s, int WIDTH, int HEIGHT, int colorType){
        this.basicScale = s;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.colorType = colorType;
        setExtendedScale(Scale.UNITY);
    }
    
    public final void setExtendedScale(Scale s){
        Scale s2 = s.scale(getBasicScale());
        if(s2.getXScaling() <= 0 || s2.getYScaling() <= 0){
            throw new IllegalArgumentException("Scaling is non-Positive");
        }
        int w2 = s2.cXD(WIDTH);
        int h2 = s2.cYD(HEIGHT);
        extendedScale = s2;
        //System.out.println(s.getXShift());
        if(img == null || img.getWidth() != w2 || img.getHeight() != h2){
            img = gd.getDefaultConfiguration().createCompatibleImage(w2, h2, colorType);
            gImg = img.createGraphics();
        }
    }
    
    public ScaledGraphics createScaledGraphics(Scale view){
        gImg.setClip(null);
        return new ScaledGraphics(gImg, extendedScale.getScaling().scale(view));
    }
    
    public void commit(Graphics g){
        Scale s = getExtendedScale();
        ScaledGraphics sg = new ScaledGraphics(g, getExtendedScale());
        //System.out.println(s.getXScaling());
        //System.out.println("X : " + s.getXShift() + ", Y : " + s.getYShift());
        sg.setAnchor(Anchor.NORTHWEST);
        sg.setComposite(AlphaComposite.SrcOver);
        //sg.setColor(Color.red);
        //sg.fillRect(0 , 0, WIDTH, HEIGHT);
        sg.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        Composite comp = gImg.getComposite();
        gImg.setComposite(AlphaComposite.Clear);
        gImg.fillRect(0, 0, img.getWidth(), img.getHeight());
        gImg.setComposite(comp);
    }
    /**
     * @return the basicScale
     */
    public Scale getBasicScale() {
        return basicScale;
    }

    /**
     * @return the extendedScale
     */
    public Scale getExtendedScale() {
        return extendedScale;
    }
    
    public void drawBorder(Color c){
        gImg.setColor(c);
        gImg.drawRect(0,0, img.getWidth() - 1, img.getHeight() - 1);
    }
    
}
