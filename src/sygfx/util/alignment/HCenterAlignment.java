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
public class HCenterAlignment extends HorizontalAlignment {

    @Override
    public int convert(int d) {
        return -(d / 2);
    }

    @Override
    public int revert(int d) {
        return d / 2;
    }

    @Override
    public double convert(double d) {
        return -(d * 0.5);
    }

    @Override
    public double revert(double d) {
        return d * 0.5;
    }
    
}
