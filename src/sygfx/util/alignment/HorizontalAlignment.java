/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.util.alignment;

import java.awt.Dimension;
import sygfx.util.DimensionD;

/**
 *
 * @author Serhan
 */
public abstract class HorizontalAlignment implements Alignment {
    
    public int convertX(int p, Dimension dim){
        return p + convert(dim.width);
    }
    
    public int revertX(int p, Dimension dim){
        return p + revert(dim.width);
    }
    
    public double convertX(double p, DimensionD dim){
        return p + convert(dim.width);
    }
    
    public double revertX(double p, DimensionD dim){
        return p + revert(dim.width);
    }
    
    public int transform(int p, Dimension dim, HorizontalAlignment ha){
          return ha.convertX(revertX(p, dim), dim);
    }
    
    public double transform(double p, DimensionD dim, HorizontalAlignment ha){
          return ha.convertX(revertX(p, dim), dim);
    }
    
}
