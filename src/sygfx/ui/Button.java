/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import sygfx.ScaledGraphics;
import sygfx.border.LineBorder;
import sygfx.ui.layout.FreeLayout;
import sygfx.util.Anchor;

/**
 *
 * @author Serhan
 */
public class Button extends Container{
    
    private Label button_label;
    private boolean selected = false;
    private ActionListener actionListener = null;
    
    public Button(){
        this("");
    }
    
    public Button(String text) {
        this(text, new Color(150, 75, 0));
    }
    
    public Button(String text, Color backcolor) {
        super();
        this.backColor = backcolor;
        button_label = new Label(text);
        button_label.setForeground(Color.white);
//        this.add(button_label);
        this.border = new LineBorder(1, Color.black);
//        super.setLayout(new FreeLayout(Anchor.CENTER));
    }
    
    public Button(int width, int height) {
        this(width, height, "");
    }
    
    public Button(int width, int height, String text) {
        this(width, height, text, new Color(150, 75, 0));
    }
    
    public Button(int width, int height, String text, Color backcolor) {
        super(width, height);
        this.backColor = backcolor;
        button_label = new Label(text);
        button_label.setForeground(Color.white);
//        this.add(button_label);
        this.border = new LineBorder(1, Color.black);
//        super.setLayout(new FreeLayout(Anchor.CENTER));
    }
    
    @Override
    protected void paintComponent(ScaledGraphics g){
        super.paintComponent(g);
        if(selected){
            g.setAnchor(Anchor.NORTHWEST);
            g.setColor(backColor.brighter());
            g.fillRect(position.x, position.y, size.width, size.height);
        }
        button_label.setSize(size);
        button_label.setPosition(position);
        button_label.paintComponent(g);
    }
    
    @Override
    public Dimension getPreferredSize(){
        if(this.preferredSizeLocked){
            return preferredSize;
        }
        return button_label.getPreferredSize();
    }
    
    @Override 
    public void removeAll(){
        super.removeAll();
        this.add(button_label);
    }
    
    public void setFont(Font font) {
        this.button_label.setFont(font);
    }
    
    public void setFontSize(float fontsize) {
        this.button_label.setFontSize(fontsize);
    }
    
    public Font getFont() {
        return this.button_label.getFont();
    }
    
    public Color getForeground(){
        return this.button_label.getForeground();
    }
    
    public void setForeground(Color color){
        this.button_label.setForeground(color);
    }
    
    @Override
    public boolean processMouseEvent(MouseEvent e){
        boolean b = super.processMouseEvent(e);
        if(b){
            if(e.getID() == MouseEvent.MOUSE_PRESSED){
                selected = true;
                System.out.println("Selected = true");
            }
            if(e.getID() == MouseEvent.MOUSE_RELEASED){
                if(selected){
                    ActionEvent event = new ActionEvent(this, 
                            ActionEvent.ACTION_PERFORMED, "MouseAction");
                    processActionListener(event);
                }
                selected = false;
                System.out.println("Selected = false");
            }
        }
        return b;
    }
    
    protected void processActionListener(ActionEvent e){
        if(actionListener != null){
            actionListener.actionPerformed(e);
        }
    }
    
    public void addActionListener(ActionListener l){
        actionListener = l;
    }
    
}
