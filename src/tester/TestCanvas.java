/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import sygfx.ui.layout.FlowLayout;
import sygfx.border.LineBorder;
import sygfx.ui.Container;
import sygfx.Layer;
import sygfx.ui.Panel;
import sygfx.Scale;
import sygfx.ScaledGraphics;
import sygfx.ui.Button;
import sygfx.ui.layout.BorderLayout;
import sygfx.ui.Label;
import sygfx.ui.Window;
import sygfx.util.Anchor;
import sygfx.util.alignment.Alignment;

/**
 *
 * @author Serhan
 */
public class TestCanvas extends JPanel{
    public final static int DEFAULT_WIDTH = 1280;
    public final static int DEFAULT_HEIGHT = 720;
    
//    Window windowPanel = new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    Window mainPanel = new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//    Window mainPanel = new Window();
    
    Layer uiLayer = new Layer(Scale.UNITY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    Scale windowScale = Scale.UNITY;
    
    public TestCanvas(){
        initialize();
    }
    
    private void initialize(){
//        Panel p1 = new Panel(250, 300);
        FlowLayout layout1 = new FlowLayout(FlowLayout.VERTICAL, Anchor.NORTH);
//        layout1.setVgap(1);
//        layout1.setHgap(40);
        Panel windowPanel = new Panel();
        Panel buttonPanel = new Panel();
        Panel upperTextPanel = new Panel();
        upperTextPanel.setLayout(new FlowLayout(FlowLayout.HORIZONTAL, Anchor.NORTH, 20, 30));
        Label upperLabel = new Label("Welcome to SYGFX Tester!");
        upperLabel.setForeground(Color.MAGENTA);
        upperLabel.setFont(new Font("Arial", Font.BOLD, 30));
        upperTextPanel.add(upperLabel);
        Panel lowerPanel = new Panel();
//        lowerPanel.setLayout(new FlowLayout());
        
//        upperTextPanel.setBackground(Color.PINK);
        windowPanel.setLayout(new BorderLayout());
//        p1.setSize(new Dimension(830, 150));
//        FlowLayout layout = new FlowLayout(FlowLayout.HORIZONTAL, Anchor.NORTH);
//        BorderLayout layout = new BorderLayout();
//        layout.setAlignmentV(Alignment.SOUTH);
        buttonPanel.setLayout(layout1);
        windowPanel.setBackground(Color.CYAN);
//        String[] stringList = {BorderLayout.CENTER, BorderLayout.EAST, BorderLayout.WEST, BorderLayout.NORTH, BorderLayout.SOUTH};
        String[] textList = new String[5];
        for(int i = 0; i < 5; i++){
            textList[i] = "Button " + (i + 1);
        }
        
        for(int i = 0; i < 5; i++){
//            Panel p = new Panel(150 + 5 * i, 140 - 20 * i);
            Panel p = new Panel();
            p.setBorder(new LineBorder(2, Color.black));
            Color color = new Color(204, 204 - i * 20, 0);
            p.setBackground(color);
            Button button = new Button(200, 60, textList[i]);
//            button.setBackground(Color.lightGray);
//            p.add(button);
//            button.setFontSize(12);
//            p1.add(p, stringList[i]);
            buttonPanel.add(button);
        }
        
        for(int i = 0; i < 3; i++){
//            Panel p = new Panel(150 + 5 * i, 140 - 20 * i);
            Panel p = new Panel();
            p.setBorder(new LineBorder(2, Color.black));
            Color color = new Color(100, 100, 200);
            p.setBackground(color);
            p.add(new Label("New Button " + (i+1)));
//            p1.add(p, stringList[i]);
            lowerPanel.add(p);
        }
        
        windowPanel.setBorder(new LineBorder(5));
        windowPanel.add(buttonPanel, BorderLayout.CENTER);
        windowPanel.add(upperTextPanel, BorderLayout.NORTH);
        windowPanel.add(lowerPanel, BorderLayout.SOUTH);
//        p1.setPreferredSize(new Dimension(300,500));
//        lowerPanel.setPlacementAnchor(Anchor.WEST);
        mainPanel.setLayout(new FlowLayout(FlowLayout.HORIZONTAL, Anchor.CENTER));
        mainPanel.add(windowPanel);
//        mainPanel = p1;
        
//        windowPanel.pack();
//        windowPanel.setVisible(true);

        mainPanel.pack();
        mainPanel.setVisible(true);
//        System.out.println(buttonPanel);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        uiLayer.setExtendedScale(windowScale);
        ScaledGraphics uiLayerGraphics = uiLayer.createScaledGraphics(Scale.UNITY);
//        windowPanel.paint(uiLayerGraphics);
        mainPanel.paint(uiLayerGraphics);
        uiLayer.commit(g);
        
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public void setWindowScale(int width, int height){
        double xsca = (width + 0.0) / DEFAULT_WIDTH;
        double ysca = (height + 0.0) / DEFAULT_HEIGHT;
        //double xs =  -1 * width / ( 2 * xsca );
        //double ys = -1 * height / ( 2 * ysca );
        windowScale = new Scale(0, 0, xsca, ysca);
    }
    
}
