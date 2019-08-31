/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui.layout;

import java.awt.Dimension;
import sygfx.ui.Container;

/**
 *
 * @author Serhan
 */
public interface LayoutManager {
    
    public void applyLayout(Container c);
    public Dimension preferredLayoutSize(Container c);
    public void invalidateLayout();
    public void addComponent(Container c, Object constraint);
    public void removeAll();
    
}
