/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import sygfx.ui.JCanvas;

/**
 *
 * @author Serhan
 */
public class GUI {
    private JFrame f = new JFrame("SYGFX Library Tester");
    private TestCanvas test_canvas = new TestCanvas();
    private JCanvas canvas = new JCanvas();
    
    public GUI(){
        initialize();
    }
    
    private void initialize(){
        
        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce){
                int width =  canvas.getWidth();
                int height = canvas.getHeight();
                canvas.setWindowScale(width, height);
                //canvas.getTotalScale().print();
            }
        });
        
//        canvas.addcomp
        
//        f.setUndecorated(true);
//        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        f.setResizable(true);
        TestWindowDesigner.designWindow(canvas.getWindow());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(canvas);
        f.pack();
        f.setLocationRelativeTo(null);
    }
    
    public void setVisible(){
        f.setVisible(true);
    }
    
    public void cycle(){
        canvas.repaint();
    }
}
