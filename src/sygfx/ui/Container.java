/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.ui;

import sygfx.ui.layout.FlowLayout;
import sygfx.ui.layout.LayoutManager;
import sygfx.Scale;
import sygfx.ScaledGraphics;
import sygfx.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import sygfx.util.Anchor;
import sygfx.util.Rectangle;

/**
 *
 * @author Serhan
 */

public abstract class Container {
    
    protected boolean enabled = true;
    protected boolean visible = true;
    protected boolean focused = false;
    protected boolean valid = false;
    protected boolean clickable = true;
   
    protected boolean cached = false;
    protected boolean preferredSizeLocked = false;
    
    protected final ArrayList<Container> children = new ArrayList<>();
    protected final ArrayList<Object> childrenConstraints = new ArrayList<>();
    
    protected Color backColor;
    protected Border border = null;
    protected Point position;
    protected Dimension size = new Dimension(0, 0);
    protected Dimension preferredSize = new Dimension(0, 0);
    protected Anchor placementAnchor = Anchor.NORTHWEST;
    protected LayoutManager layout = 
            new FlowLayout(FlowLayout.HORIZONTAL, Anchor.NORTH);
    
    private Container parent = null;
    
    private MouseListener mouseListener = null;
    private MouseMotionListener mouseMotionListener = null;
    
    public Container(){
        this.backColor = null;
        setPosition(0, 0);
    }
    
    public Container(int width, int height){
        this(width, height, null);
    }
    
    public Container(int width, int height, Color backcolor){
        this.backColor = backcolor;
        size = new Dimension(width, height);
        preferredSize = new Dimension(width, height);
        preferredSizeLocked = true;
        setPosition(0, 0);
    }
    
    public void add(Container co){
        add(co, null);
    }
    
    public void add(Container co, Object constraints){
        children.add(co);
        childrenConstraints.add(constraints);
        if(co.parent != null){
            throw new IllegalArgumentException(
                    "Component already have an ancestor!");
        }
        co.parent = this;
        if(layout != null){
            layout.addComponent(co, constraints);
        }
        invalidate();
    }
    
    public Container getComponent(int index){
        return children.get(index);
    }
    
    public int getComponentCount(){
        return children.size();
    }
    
    public Color getBackground(){
        return backColor;
    }
    
    public Border getBorder(){
        return border;
    }
    
    public int getHeight(){
        return size.height;
    }
    
    public Rectangle getInnerRectangle(){
        if(border == null){
            return new Rectangle(position, size);
        }
        return border.getInteriorRectangle(this);
    }
    
    public Insets getInsets(){
        return new Insets(position.y, position.x, 
                position.y + size.height, position.x + size.width);
    }
    
    public Anchor getPlacementAnchor(){
        return placementAnchor;
    }
    
    public Point getPosition(){
        return position;
    }
    
    public Dimension getPreferredSize(){
        if(preferredSizeLocked || layout == null){
            return new Dimension(preferredSize);
        }
        cached = true;
        return new Dimension(layout.preferredLayoutSize(this));
    }
    
    public Dimension getSize(){
        return size;
    }
    
    public int getWidth(){
        return size.width;
    }
    
    public final boolean isEnabled(){
        return enabled;
    }
    
    public final boolean isFocused(){
        return focused;
    }
    
    public final boolean isShown(){
        if(!isVisible()){
            return false;
        }
        if(parent != null){
            return parent.isShown();
        }
        return true;
    }
    
    public final boolean isValid(){
        return valid;
    }
    
    public final boolean isVisible(){
        return visible;
    }
    
    public void removeAll(){
        children.clear();
        childrenConstraints.clear();
        if(layout != null){
            layout.removeAll();
        }
    }
    
    public void setBackground(Color color){
        this.backColor = color;
    }
    
    public void setBorder(Border border){
        this.border = border;
        invalidate();
    }
    
    public void setEnabled(boolean bo){
        enabled = bo;
    }
    
    public void setFocused(boolean bo){
        focused = bo;
    }
    
    public void setLayout(LayoutManager layout){
        if(layout != null){
            layout.removeAll();
            for(int i = 0; i < children.size(); i++){
                layout.addComponent(children.get(i), 
                        childrenConstraints.get(i));
            }
        }
        this.layout = layout;
        invalidate();
    }
    
    public void setPlacementAnchor(Anchor anchor){
        placementAnchor = anchor;
    }
    
    public final void setPosition(int x, int y){
        setPosition(new Point(x, y));
    }
    
    public final void setPosition(int x, int y, Anchor anchor){
        setPosition(new Point(x, y), anchor);
    }
    
    public final void setPosition(Point p){
        setPosition(p, Anchor.NORTHWEST);
    }
    
    public final void setPosition(Point p, Anchor anchor){
        if(p == null){
            p = new Point(0, 0);
        }
        position = anchor.transform(p, size, Anchor.NORTHWEST);
    }
    
    public void setPreferredSize(Dimension dim){
        if(dim == null){
            preferredSizeLocked = false;
            this.preferredSize = new Dimension(0, 0);
            return;
        }
        this.preferredSize = dim;
        preferredSizeLocked = true;
    }
    
    public void setSize(Dimension dim){
        if(dim.height != size.height || dim.width != size.width){
            invalidate();
        }
        this.size = dim;
    }
    
    public void setVisible(boolean bo){
        if(visible != bo){
            invalidate();
        }
        visible = bo;
    }
    
    public final void paint(ScaledGraphics g){
        if(isVisible()){
            Shape clip = g.getClip();
            
            g.setAnchor(Anchor.NORTHWEST);
            g.clipRect(position.x, position.y, size.width, size.height);
//            Shape borderClip = g.getClip();
            
            Rectangle r = getInnerRectangle();
//            Point p = Anchor.NORTHWEST.transform(position, size, placementAnchor);
            Point p = Anchor.NORTHWEST.transform(r.getPoint(), 
                    r.getDimension(), placementAnchor);
            Scale s = new Scale(p.x, p.y);
            ScaledGraphics g2 = new ScaledGraphics(g, s);
            
            g.setAnchor(Anchor.NORTHWEST);
//            g.clipRect(r);
            
            paintComponent(g);
            
            for(Container co : children){
                co.paint(g2);
            }
            
            if(border != null){
                border.paint(g, getInsets());
            }
            
            g.setClip(clip);
        }
    }
    
    protected void paintComponent(ScaledGraphics g){
        if(backColor != null){
            g.setAnchor(Anchor.NORTHWEST);
            g.setColor(backColor);
            g.fillRect(position.x, position.y, size.width, size.height);
        }
    }
    
    public void validate(){
        if(!valid){
            validateTree();
        }
    }
    
    protected void validateTree(){
        layout.applyLayout(this);
        for(Container c : children){
            c.validate();
        }
        valid = true;
    }
    
    public void invalidate(){
        if(isValid() || cached){
            valid = false;
            cached = false;
            if(layout != null){
                layout.invalidateLayout();
            }
            if(parent != null){
                parent.invalidate();
            }
        }
    }
    
    public void revalidate(){
        
    }
    
    public void addMouseListener(MouseListener l){
        this.mouseListener = l;
    }
    
    public void addMouseMotionListener(MouseMotionListener l){
        this.mouseMotionListener = l;
    }

    private boolean contains(Point p, Rectangle rect){
        boolean wX = (p.x <= (rect.x + rect.width)) & (p.x >= rect.x);
        boolean wY = (p.y <= (rect.y + rect.height)) & (p.y >= rect.y);
        return wX & wY;
    }
    
    protected boolean processMouseEvent(MouseEvent e){
        if(!isVisible() || !isEnabled()){
            return false;
        }
        if(e.isConsumed()){
            return false;
        }
        Point p = e.getPoint();
        Rectangle r = getInnerRectangle();
        if(!contains(p, r)){
            return false;
        }
        Point p2 = Anchor.NORTHWEST.transform(r.getPoint(), 
                r.getDimension(), placementAnchor);
        e.translatePoint(-p2.x, -p2.y);
        for(Container c: children){
            if(e.isConsumed()){
                return false;
            }
            c.processMouseEvent(e);
        }
        if(!e.isConsumed() && isClickable()){
            processMouseListener(e);
            processMouseMotionListener(e);
            e.consume();
            return true;
        }
        e.translatePoint(p2.x, p2.y);
        return false;
    }
    
    private void processMouseListener(MouseEvent e){
        if(mouseListener == null){
            return;
        }
        switch(e.getID())
        {
            case MouseEvent.MOUSE_CLICKED:
                mouseListener.mouseClicked(e);
                break;
            case MouseEvent.MOUSE_PRESSED:
                mouseListener.mousePressed(e);
                break;
            case MouseEvent.MOUSE_RELEASED:
                mouseListener.mouseReleased(e);
                break;
            case MouseEvent.MOUSE_ENTERED:
                mouseListener.mouseEntered(e);
                break;
            case MouseEvent.MOUSE_EXITED:
                mouseListener.mouseExited(e);
                break;
        }
    }
    
    private void processMouseMotionListener(MouseEvent e){
        if(mouseMotionListener == null){
            return;
        }
        switch(e.getID())
        {
            case MouseEvent.MOUSE_MOVED:
                mouseMotionListener.mouseMoved(e);
                break;
            case MouseEvent.MOUSE_DRAGGED:
                mouseMotionListener.mouseDragged(e);
                break;    
        }
    }
    
    public boolean isPreferredSizeSet(){
        return preferredSizeLocked;
    }
    
    public boolean isClickable(){
        return clickable;
    }
    
    public void setClickable(boolean b){
        this.clickable = b;
    }
}
