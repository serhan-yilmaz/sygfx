/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.border;

import java.awt.Insets;
import sygfx.ScaledGraphics;
import sygfx.ui.Container;
import sygfx.util.Rectangle;

/**
 *
 * @author Serhan
 */
public interface Border {
    
    public void paint(ScaledGraphics sg, Insets in);
    public Insets getBorderInsets(Container c);
    public Rectangle getInteriorRectangle(Container c, int x, int y, int width, int height);
    public Rectangle getInteriorRectangle(Container c);
    
}
