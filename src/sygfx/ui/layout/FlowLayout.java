/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui.layout;

import sygfx.ui.layout.AbstractLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import sygfx.ui.Container;
import sygfx.ui.Container;
import sygfx.util.alignment.Alignment;
import sygfx.util.Anchor;
import sygfx.util.Rectangle;
import sygfx.util.alignment.HorizontalAlignment;
import sygfx.util.alignment.VerticalAlignment;

/**
 *
 * @author Serhan
 */
public class FlowLayout extends AbstractLayout{
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    
    public static final int FIRST_TO_LAST = 1;
    public static final int LAST_TO_FIRST = 2;
    public static final int DEFAULT_ORDERING = FIRST_TO_LAST;
    
    private int axis = HORIZONTAL;
    private Anchor anchor = Anchor.CENTER;
    private HorizontalAlignment aligH = null;
    private VerticalAlignment aligV = null;
    private int ordering = DEFAULT_ORDERING;
    private int hgap = 5;
    private int vgap = 5;
    
    public FlowLayout(){
        this.axis = HORIZONTAL;
        this.anchor = Anchor.NORTHWEST;
    }
    
    public FlowLayout(int axis){
        this.axis = axis;
        this.anchor = Anchor.NORTHWEST;
    }
    
    public FlowLayout(int axis, Anchor anchor){
        this.axis = axis;
        this.anchor = anchor;
    }
    
    public FlowLayout(Anchor anchor){
        this.anchor = anchor;
    }
    
    public FlowLayout(int axis, Anchor anchor, int hgap, int vgap){
        this.axis = axis;
        this.anchor = anchor;
        this.hgap = hgap;
        this.vgap = vgap;
    }
    
    public void setAxis(int axis){
        this.axis = axis;
    }
    
    public void setAnchor(Anchor a){
        this.anchor = a;
    }
    
    public void setAlignmentH(HorizontalAlignment ha){
        aligH = ha;
    }
    
    public void setAlignmentV(VerticalAlignment va){
        aligV = va;
    }
    
    public void setComponentOrdering(int ordering){
        this.ordering = ordering;
    }
    
    @Override
    public void applyLayout(Container c) {
        if(c.getComponentCount() <= 0){
            return;
        }
        Anchor a;
        int i_start;
        int i_update;
        switch(ordering){
            case FIRST_TO_LAST :
                i_start = 0;
                i_update = 1;
                break;
            case LAST_TO_FIRST :
                i_start = c.getComponentCount() - 1;
                i_update = -1;
                break;
            default :
                throw new IllegalArgumentException("Ordering is invalid.");
        }
        Dimension layoutSize = innerLayoutSize(c);
        switch(axis){
            case HORIZONTAL :
//                Rectangle r = c.getBorder().getInteriorRectangle(c);
                Rectangle r = c.getInnerRectangle();
//                Dimension dim = c.getSize();
                Dimension dim = r.getDimension();
                Point p = c.getPlacementAnchor().transform(new Point(0,0), dim, anchor);
                if(aligV == null){
                    a = new Anchor(Alignment.WEST, anchor.getAlignmentV());
                } else {
                    a = new Anchor(Alignment.WEST, aligV);
                }
                p = anchor.transform(p, layoutSize, a);
                p.x += hgap;
                p.y += anchor.getAlignmentV().convert(2 * vgap) - vgap;
                for(int i = i_start; i < c.getComponentCount() && i >= 0; i+=i_update){
                    Container c2 = c.getComponent(i);
                    if(c2.isVisible()){
                        c2.setSize(c2.getPreferredSize());
                        c2.setPosition(p, a);
                        p.x += c2.getWidth();
                        p.x += hgap;
                    }
                }
                break;
            case VERTICAL   :
                dim = c.getSize();
                p = c.getPlacementAnchor().transform(new Point(0,0), dim, anchor);
                if(aligH == null){
                    a = new Anchor(anchor.getAlignmentH(), Alignment.NORTH);
                } else {
                    a = new Anchor(aligH, Alignment.NORTH);
                }
                p = anchor.transform(p, layoutSize, a);
                p.y += vgap;
                p.x += anchor.getAlignmentH().convert(2 * hgap) + hgap;
                for(int i = i_start; i < c.getComponentCount() && i >= 0; i+=i_update){
                    Container c2 = c.getComponent(i);
                    if(c2.isVisible()){
                        c2.setSize(c2.getPreferredSize());
                        c2.setPosition(p, a);
                        p.y += c2.getHeight();
                        p.y += vgap;
                    }
                }
                break;
            default         :
                throw new IllegalArgumentException("Axis is invalid.");
        }
    }

    /**
     * @return the hgap
     */
    public int getHgap() {
        return hgap;
    }

    /**
     * @param hgap the hgap to set
     */
    public void setHgap(int hgap) {
        this.hgap = hgap;
    }

    /**
     * @return the vgap
     */
    public int getVgap() {
        return vgap;
    }

    /**
     * @param vgap the vgap to set
     */
    public void setVgap(int vgap) {
        this.vgap = vgap;
    }
    
    @Override
    public Dimension calculatePreferredLayoutSize(Container c) {
        Dimension layoutSize = new Dimension(0,0);
        Anchor a;
        int i_start;
        int i_update;
        switch(ordering){
            case FIRST_TO_LAST :
                i_start = 0;
                i_update = 1;
                break;
            case LAST_TO_FIRST :
                i_start = c.getComponentCount() - 1;
                i_update = -1;
                break;
            default :
                throw new IllegalArgumentException("Ordering is invalid.");
        }
        switch(axis){
            case HORIZONTAL :
                layoutSize.width += hgap;
                for(int i = i_start; i < c.getComponentCount() && i >= 0; i+=i_update){
                    Container c2 = c.getComponent(i);
                    Dimension d = c2.getPreferredSize();
                      if(c2.isVisible()){
                        layoutSize.width += d.width + hgap;
                        layoutSize.height = Math.max(layoutSize.height, d.height + 2 * vgap);
                    }
                }
                break;
            case VERTICAL   :
                layoutSize.height += vgap;
                for(int i = i_start; i < c.getComponentCount() && i >= 0; i+=i_update){
                    Container c2 = c.getComponent(i);
                    Dimension d = c2.getPreferredSize();
                    if(c2.isVisible()){
                        layoutSize.height += d.height + vgap;
                        layoutSize.width = Math.max(layoutSize.width, d.width + 2 * hgap);
                    }
                }
                break;
            default         :
                throw new IllegalArgumentException("Axis is invalid.");
        }
        cachedPreferredLayoutSize = layoutSize;
        return layoutSize;
    }

    @Override
    public void invalidateLayout() {
        super.invalidateLayout();
    }

    @Override
    public void addComponent(Container c, Object constraint) {
        
    }

    @Override
    public void removeAll() {
        
    }
    
}
