/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.border;

import java.awt.Insets;
import sygfx.ui.Container;
import sygfx.util.Rectangle;

/**
 *
 * @author Serhan
 */
public abstract class AbstractBorder implements Border{
    
    @Override
    public Rectangle getInteriorRectangle(Container c, int x, int y, int width, int height){
        Insets in = this.getBorderInsets(c);
        return new Rectangle(x + in.left, y + in.top, width - in.left - in.right, height - in.top - in.bottom);
    }
    
    @Override
    public Rectangle getInteriorRectangle(Container c){
        Insets in = c.getInsets();
        return getInteriorRectangle(c, in.left, in.top, in.right - in.left, in.bottom - in.top);
    }
    
}
