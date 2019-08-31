/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui.layout;

import java.awt.Dimension;
import java.awt.Point;
import sygfx.ui.Container;
import sygfx.util.Anchor;

/**
 *
 * @author Serhan
 */
public class FreeLayout implements LayoutManager{
    private Anchor anchor;
    
    public FreeLayout(){
        this(Anchor.CENTER);
    }
    
    public FreeLayout(Anchor anchor){
        this.anchor = anchor;
    }
    
    @Override
    public void applyLayout(Container c) {
        for(int i = 0; i < c.getComponentCount(); i++){
           Container child =  c.getComponent(i);
           child.setSize(child.getPreferredSize());
           child.setPosition(new Point(0, 0), anchor);
        }
    }
    
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void invalidateLayout() {
        
    }

    @Override
    public void addComponent(Container c, Object constraint) {
        
    }

    @Override
    public void removeAll() {
        
    }
    
}
