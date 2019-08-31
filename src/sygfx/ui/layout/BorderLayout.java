/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui.layout;

import sygfx.ui.layout.AbstractLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import sygfx.ui.Container;
import sygfx.util.Anchor;
import sygfx.util.Rectangle;

/**
 *
 * @author Serhan
 */
public class BorderLayout extends AbstractLayout {
    public final static String CENTER = "Center";
    public final static String NORTH = "North";
    public final static String EAST = "East";
    public final static String WEST = "West";
    public final static String SOUTH = "South";
    
    private final static int CENTER_INDEX = 0;
    private final static int NORTH_INDEX = 1;
    private final static int EAST_INDEX = 2;
    private final static int WEST_INDEX = 3;
    private final static int SOUTH_INDEX = 4;
    
    private Map<String, Integer> indices = new HashMap<>(5);
    private ArrayList<Container> components = new ArrayList<>(5);
    private ArrayList<Dimension> sizes = new ArrayList<>(5);
    
    public BorderLayout(){
        indices.put(CENTER, CENTER_INDEX);
        indices.put(NORTH, NORTH_INDEX);
        indices.put(EAST, EAST_INDEX);
        indices.put(WEST, WEST_INDEX);
        indices.put(SOUTH, SOUTH_INDEX);
        for(int i = 0; i < 5; i++){
            components.add(null);
            sizes.add(null);
        }
    }
    
    @Override
    public void applyLayout(Container c) {
        Rectangle r = c.getInnerRectangle();
        Dimension innerSize = r.getDimension();
        getComponentSizeInfo();
        Dimension center = sizes.get(CENTER_INDEX);
        Dimension north = sizes.get(NORTH_INDEX);
        Dimension east = sizes.get(EAST_INDEX);
        Dimension west = sizes.get(WEST_INDEX);
        Dimension south = sizes.get(SOUTH_INDEX);
        Dimension mid = new Dimension(0, 0);
        mid.width = west.width + center.width + east.width;
        mid.height = Math.max(Math.max(west.height, center.height), east.height);
        int prefHeight = north.height + mid.height + south.height;
        if(prefHeight <= innerSize.height){
            center.height = innerSize.height - north.height - south.height;
            west.height = center.height;
            east.height = center.height;
        } else {
            int remain = innerSize.height;
            if(remain >= north.height){
                remain -= north.height;
                if(remain >= south.height){
                    remain -= south.height;
                    center.height = remain;
                    west.height = remain;
                    east.height = remain;
                } else {
                    south.height = remain;
                    center.height = 0;
                    west.height = 0;
                    east.height = 0;
                }
            } else {
                north.height = remain;
                south.height = 0;
                center.height = 0;
                west.height = 0;
                east.height = 0;
            }
        }
        if(mid.width <= innerSize.width){
            center.width = innerSize.width - west.width - east.width;
        } else {
            int remain = innerSize.width;
            if(remain >= west.width){
                remain -= west.width;
                if(remain >= east.width){
                    remain -= east.width;
                    center.width = remain;
                } else {
                    east.width = remain;
                    center.width = 0;
                }
            } else {
                west.width = remain;
                center.width = 0;
                east.width = 0;
            }
        }
        north.width = innerSize.width;
        south.width = innerSize.width;
        for(int i = 0; i < 5; i++){
            Container comp = components.get(i);
            if(comp != null){
                comp.setSize(sizes.get(i));
            }
        }
        Point p = c.getPlacementAnchor().transform(new Point(0,0), innerSize, Anchor.NORTHWEST);
        if(components.get(NORTH_INDEX) != null){
            components.get(NORTH_INDEX).setPosition(p, Anchor.NORTHWEST);
        }
        p.y += north.height;
        if(components.get(WEST_INDEX) != null){
            components.get(WEST_INDEX).setPosition(p, Anchor.NORTHWEST);
        }
        int x = p.x;
        p.x += west.width;
        if(components.get(CENTER_INDEX) != null){
            components.get(CENTER_INDEX).setPosition(p, Anchor.NORTHWEST);
        }
        p.x += center.width;
        if(components.get(EAST_INDEX) != null){
            components.get(EAST_INDEX).setPosition(p, Anchor.NORTHWEST);
        }
        p.x = x;
        p.y += west.height;
        if(components.get(SOUTH_INDEX) != null){
            components.get(SOUTH_INDEX).setPosition(p, Anchor.NORTHWEST);
        }
    }

    @Override
    protected Dimension calculatePreferredLayoutSize(Container c) {
        getComponentSizeInfo();
        Dimension layoutSize = new Dimension(0, 0);
        Dimension center = sizes.get(CENTER_INDEX);
        Dimension north = sizes.get(NORTH_INDEX);
        Dimension east = sizes.get(EAST_INDEX);
        Dimension west = sizes.get(WEST_INDEX);
        Dimension south = sizes.get(SOUTH_INDEX);
        layoutSize.width = Math.max(north.width, west.width + center.width + east.width);
        layoutSize.width = Math.max(layoutSize.width, south.width);
        layoutSize.height = north.height + Math.max(Math.max(west.height, center.height), east.height) + south.height;
        return layoutSize;
    }
    
    @Override
    public void invalidateLayout() {
        super.invalidateLayout();
    }

    @Override
    public void addComponent(Container c, Object constraint) {
        if(constraint == null){
            components.set(CENTER_INDEX, c);
//            sizes.set(CENTER_INDEX, null);
            return;
        }
        for (Map.Entry<String, Integer> pair : indices.entrySet()) {
            if(pair.getKey().equals(constraint)){
                components.set(pair.getValue(), c);
                sizes.set(pair.getValue(), null);
                return;
            }
        }
    }

    @Override
    public void removeAll() {
        for(int i = 0; i < 4; i++){
            components.set(i, null);
//            sizes.set(i, null);
        }
    }
    
    private void getComponentSizeInfo(){
        for(int i = 0; i < 5; i++){
            Container comp = components.get(i);
            if(comp == null){
                sizes.set(i, new Dimension(0, 0));
            } else {
                sizes.set(i, comp.getPreferredSize());
            }
        }
    }
    
}
