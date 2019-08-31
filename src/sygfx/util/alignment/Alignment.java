/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.util.alignment;

/**
 *
 * @author Serhan
 */
public interface Alignment {
    public final static HorizontalAlignment WEST = new WestAlignment();
    public final static HorizontalAlignment EAST = new EastAlignment();
    public final static HorizontalAlignment HCENTER = new HCenterAlignment();
    public final static VerticalAlignment NORTH = new NorthAlignment();
    public final static VerticalAlignment SOUTH = new SouthAlignment();
    public final static VerticalAlignment VCENTER = new VCenterAlignment();
    
    public int convert(int d);
    public int revert(int d);
    public double convert(double d);
    public double revert(double d);
    
}
