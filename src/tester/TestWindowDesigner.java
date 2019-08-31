/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import sygfx.border.LineBorder;
import sygfx.ui.Button;
import sygfx.ui.Label;
import sygfx.ui.Panel;
import sygfx.ui.Window;
import sygfx.ui.layout.BorderLayout;
import sygfx.ui.layout.FlowLayout;
import sygfx.util.Anchor;

/**
 *
 * @author Serhan
 */
public class TestWindowDesigner{
    
    public static void designWindow(Window window){
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
        windowPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(layout1);
        windowPanel.setBackground(Color.CYAN);
        String[] textList = new String[5];
        for(int i = 0; i < 5; i++){
            textList[i] = "Button " + (i + 1);
        }
        
        for(int i = 0; i < 5; i++){
//            Panel p = new Panel();
//            p.setBorder(new LineBorder(2, Color.black));
//            Color color = new Color(204, 204 - i * 20, 0);
//            p.setBackground(color);
            Button button = new Button(200, 60, textList[i]);
            buttonPanel.add(button);
            if(i == 0 || i == 1){
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        Button bu = (Button) e.getSource();
                        Random rand = new Random();
                        float r = rand.nextFloat();
                        float g = rand.nextFloat();
                        float b = rand.nextFloat();
                        button.setBackground(new Color(r, g, b));
                        System.out.println("Action is performed. Yeyy!");
                    }
                });
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
//                        Random rand = new Random();
//                        float r = rand.nextFloat();
//                        float g = rand.nextFloat();
//                        float b = rand.nextFloat();
//                        windowPanel.setBackground(new Color(r, g, b));
//                        System.out.println("Button is clicked. Yeyy!");
                    }
                        
                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
        }
        
//        buttonPanel.getComponent(1).addMouseListener(newM);
        
        for(int i = 0; i < 3; i++){
            Panel p = new Panel();
            p.setBorder(new LineBorder(2, Color.black));
            Color color = new Color(100, 100, 200);
            p.setBackground(color);
            p.add(new Label("New Button " + (i+1)));
            lowerPanel.add(p);
        }
        
        windowPanel.setBorder(new LineBorder(5));
        windowPanel.add(buttonPanel, BorderLayout.CENTER);
        windowPanel.add(upperTextPanel, BorderLayout.NORTH);
        windowPanel.add(lowerPanel, BorderLayout.SOUTH);
//        p1.setPreferredSize(new Dimension(300,500));
//        lowerPanel.setPlacementAnchor(Anchor.WEST);
        window.setLayout(new FlowLayout(FlowLayout.HORIZONTAL, Anchor.CENTER));
        window.add(windowPanel);
        window.pack();
        window.setVisible(true);
//        System.out.println(buttonPanel);
    }
}
