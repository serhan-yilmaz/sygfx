/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.border;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Stroke;
import sygfx.ScaledGraphics;
import sygfx.ui.Container;
import sygfx.util.Anchor;

/**
 *
 * @author Serhan
 */
public class LineBorder extends AbstractBorder{
    private static final Color DEFAULT_COLOR = Color.black; 
    
    private final BasicStroke stroke;
    private final Color color;
    private final int width;
    
    public LineBorder(int width){
        if(width <= 0){
            throw new IllegalArgumentException("Width must be an integer greater than 0.");
        }
        this.stroke = new BasicStroke(width - 1);
        this.color = DEFAULT_COLOR;
        this.width = width;
    }
    
    public LineBorder(int width, Color color){
        if(width <= 0){
            throw new IllegalArgumentException("Width must be an integer greater than 0.");
        }
        this.stroke = new BasicStroke(width - 1);
        this.color = color;
        this.width = width;
    }
    
    @Override
    public void paint(ScaledGraphics sg, Insets in) {
        Stroke prev = sg.getStroke();
        sg.setDynamicStroke(stroke);
//        sg.setStroke(stroke);
        sg.setColor(color);
        int hwl = 0;
        int hwu = 0;
//        int hwl = width / 2;
//        int hwu = (int) Math.ceil(width / 2.0);
        
        in.left += hwl;
        in.right -= hwu;
        in.bottom -= hwu;
        in.top += hwl;
        sg.setAnchor(Anchor.NORTHWEST);
        sg.setDynamicRectPositioning(true);
        sg.drawRect(in.left, in.top, in.right - in.left, in.bottom - in.top);
        sg.setDynamicRectPositioning(false);
//        sg.setDynamicStroke(stroke, 0);
//        sg.drawLine(in.left, in.bottom, in.right, in.bottom);
//        sg.drawLine(in.left, in.top, in.right, in.top);
//        sg.setDynamicStroke(stroke, 90);
//        sg.drawLine(in.left, in.bottom, in.left, in.top);
//        sg.drawLine(in.right, in.bottom, in.right, in.top);
//        sg.setStroke(prev);
    }

    @Override
    public Insets getBorderInsets(Container c) {
//        return new Insets(0, 0, 0, 0);
        return new Insets(width - 1, width - 1, width - 1, width - 1);
//        return new Insets(width, width, width, width);
//          return new Insets(width/2, width/2, width/2, width/2);
    }
}
