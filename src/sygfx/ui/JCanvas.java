/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import sygfx.Layer;
import sygfx.Scale;
import sygfx.ScaledGraphics;

/**
 *
 * @author Serhan
 */
public class JCanvas extends JPanel{
    private final int resolution_width;
    private final int resolution_height;
    private Scale windowScale = Scale.UNITY;
    
    private final Window window;
    private final Layer layer;
    
    public JCanvas(){
        this(1280, 720);
    }
    
    public JCanvas(int width, int height){
        this.resolution_width = width;
        this.resolution_height = height;
        this.window = new Window(width, height);
        this.layer = new Layer(Scale.UNITY, width, height);
        initialize();
    }
    
    private void initialize(){
        final JCanvas canvas = this;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce){
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                canvas.setWindowScale(width, height);
                System.out.println("Component resized: " + width + " - " + height);
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                canvas.directMouseEvent(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                canvas.directMouseEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                canvas.directMouseEvent(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                canvas.directMouseEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                canvas.directMouseEvent(e);
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                canvas.directMouseEvent(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                canvas.directMouseEvent(e);
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        layer.setExtendedScale(windowScale);
        ScaledGraphics sg = layer.createScaledGraphics(Scale.UNITY);
        window.paint(sg);
        layer.commit(g);
    }
    
    private void directMouseEvent(MouseEvent e){
        Point p = e.getPoint();
        Point p2 = windowScale.inverse().transform(e.getPoint()).toPoint();
        e.translatePoint(p2.x - p.x, p2.y - p.y);
        if(window != null){
            window.processMouseEvent(e);
        }
    }
    
    public void setWindowScale(int width, int height){
        double xsca = (width + 0.0) / this.resolution_width;
        double ysca = (height + 0.0) / this.resolution_height;
        windowScale = new Scale(0, 0, xsca, ysca);
    }
    
    public Scale getScale(){
        return windowScale;
    }
    
    public Window getWindow(){
        return this.window;
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(resolution_width, resolution_height);
    }
}
