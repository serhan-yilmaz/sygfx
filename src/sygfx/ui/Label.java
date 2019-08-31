/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import sygfx.ScaledGraphics;
import sygfx.util.Anchor;

/**
 *
 * @author Serhan
 */
public class Label extends Container{
   // private final Font DEFAULT_FONT = new JLabel().getFont().deriveFont(Font.PLAIN, 24);
  //  protected final FontMetrics DEFAULT_FONT_METRICS = new JLabel().getFontMetrics(DEFAULT_FONT);
    
    private String text = "";
    private FontRenderContext frc;;
    private Dimension cachedPreferredSize = null;
    
    protected Color foreColor = Color.black;
    protected Font font;// = DEFAULT_FONT; 
    
    private int hgap = 5;
    private int vgap = 5;
    
    public Label(String text){
        this(text, 24);
    }
    
    public Label(String text, float fontsize){
        this(text, fontsize, 5, 5);
    }
    
    public Label(String text, float fontsize, int hgap, int vgap){
        this(text, new JLabel().getFont().deriveFont(Font.PLAIN, fontsize), 
                hgap, vgap);
    }
    
    public Label(String text, Font f, int hgap, int vgap){
        this.text = text;
        this.border = null;
        this.font = f;
        this.frc = new FontRenderContext(f.getTransform(), true, true);
    }
    
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
        invalidate();
    }
    
    @Override
    public Dimension getPreferredSize(){
        if(cachedPreferredSize == null){
            GlyphVector gv = getFont().createGlyphVector(frc, text);
            Rectangle2D box = gv.getVisualBounds();
            int width = (int) box.getWidth() + hgap * 2;
            int height = (int) box.getHeight() + vgap * 2;
            if(border != null){
                Insets in =  border.getBorderInsets(this);
                width += in.left + in.right;
                height += in.top + in.bottom;
            }
            
            cachedPreferredSize = new Dimension(width, height);
//            cachedPreferredSize = new Dimension(DEFAULT_FONT_METRICS.stringWidth(text) + hgap * 2, DEFAULT_FONT_METRICS.getHeight() + vgap * 2);
        }
        return new Dimension(cachedPreferredSize);
    }
    
    @Override
    public void invalidate(){
        super.invalidate();
        cachedPreferredSize = null;
    }
    
    @Override
    public void paintComponent(ScaledGraphics sg){
        super.paintComponent(sg);
        
        sg.setFont(font);
        sg.setColor(foreColor);
        frc = sg.getFontRenderContext();
        Point p = Anchor.NORTHWEST.transform(position, getInnerRectangle().getDimension(), Anchor.CENTER);
        sg.setAnchor(Anchor.CENTER);
        sg.drawString(text, p.x, p.y);
    }
    
    /**
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * @param font the font to set
     */
    public void setFont(Font font) {
        this.font = font;
    }
    
    public void setFontSize(float fontsize) {
        this.font = this.font.deriveFont(fontsize);
    }
    
    public Color getForeground(){
        return foreColor;
    }
    
    public void setForeground(Color color){
        this.foreColor = color;
    }
}
