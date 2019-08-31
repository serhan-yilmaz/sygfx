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
public abstract class VerticalAlignment implements Alignment {
    
    public int convertY(int p, Dimension dim){
        return p + convert(dim.height);
    }
    
    public int revertY(int p, Dimension dim){
        return p + revert(dim.height);
    }
    
    public int transform(int p, Dimension dim, VerticalAlignment va){
          return va.convertY(revertY(p, dim), dim);
    }
    
    public double convertY(double p, DimensionD dim){
        return p + convert(dim.height);
    }
    
    public double revertY(double p, DimensionD dim){
        return p + revert(dim.height);
    }
    
    public double transform(double p, DimensionD dim, VerticalAlignment va){
          return va.convertY(revertY(p, dim), dim);
    }
    
    
}
