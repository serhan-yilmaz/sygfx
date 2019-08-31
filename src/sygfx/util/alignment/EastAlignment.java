/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.util.alignment;

import java.awt.Dimension;

/**
 *
 * @author Serhan
 */
public class EastAlignment extends HorizontalAlignment{

    @Override
    public int convert(int d) {
        return -d;
    }

    @Override
    public int revert(int d) {
        return d;
    }

    @Override
    public double convert(double d) {
        return -d;
    }

    @Override
    public double revert(double d) {
        return d;
    }
    
}
