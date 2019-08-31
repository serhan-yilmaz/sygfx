/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui.layout;

import java.awt.Dimension;
import java.awt.Insets;
import sygfx.ui.Container;

/**
 *
 * @author Serhan
 */
public abstract class AbstractLayout implements LayoutManager{
    
    protected Dimension cachedPreferredSize = null;
    protected Dimension cachedPreferredLayoutSize = null;
    
    @Override
    public Dimension preferredLayoutSize(Container c) {
        if(cachedPreferredSize == null){
            calculatePreferredSizeInfo(c);
        }
        return cachedPreferredSize;
    }

    @Override
    public void invalidateLayout() {
        cachedPreferredSize = null;
        cachedPreferredLayoutSize = null;
    }
    
    protected Dimension innerLayoutSize(Container c) {
        if(cachedPreferredLayoutSize == null){
            cachedPreferredLayoutSize = calculatePreferredLayoutSize(c);
        }
        return cachedPreferredLayoutSize;
    }
    
    private void calculatePreferredSizeInfo(Container c){
        cachedPreferredLayoutSize = innerLayoutSize(c);
        Dimension preferredSize = new Dimension(cachedPreferredLayoutSize);
        if(c.getBorder() != null){
            Insets in = c.getBorder().getBorderInsets(c);
            preferredSize.width += in.left + in.right;
            preferredSize.height += in.bottom + in.top;
        }
        cachedPreferredSize = preferredSize;
    }
    
    protected abstract Dimension calculatePreferredLayoutSize(Container c);
    
    
}
