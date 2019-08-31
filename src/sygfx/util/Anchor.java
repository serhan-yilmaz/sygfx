/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.util;

import java.awt.Dimension;
import java.awt.Point;
import sygfx.util.alignment.EastAlignment;
import sygfx.util.alignment.HCenterAlignment;
import sygfx.util.alignment.HorizontalAlignment;
import sygfx.util.alignment.NorthAlignment;
import sygfx.util.alignment.SouthAlignment;
import sygfx.util.alignment.VCenterAlignment;
import sygfx.util.alignment.VerticalAlignment;
import sygfx.util.alignment.WestAlignment;

/**
 *
 * @author Serhan
 */
public class Anchor {
      public final static Anchor CENTER = new Anchor(new HCenterAlignment(), new VCenterAlignment());
      public final static Anchor NORTH = new Anchor(new HCenterAlignment(), new NorthAlignment());
      public final static Anchor SOUTH = new Anchor(new HCenterAlignment(), new SouthAlignment());
      public final static Anchor WEST = new Anchor(new WestAlignment(), new VCenterAlignment());
      public final static Anchor EAST = new Anchor(new EastAlignment(), new VCenterAlignment());
      public final static Anchor NORTHWEST = new Anchor(new WestAlignment(), new NorthAlignment());
      public final static Anchor NORTHEAST = new Anchor(new EastAlignment(), new NorthAlignment());
      public final static Anchor SOUTHWEST = new Anchor(new WestAlignment(), new SouthAlignment());
      public final static Anchor SOUTHEAST = new Anchor(new EastAlignment(), new SouthAlignment());
    
      private HorizontalAlignment aligH;
      private VerticalAlignment aligV;
      
      public Anchor(HorizontalAlignment aligH, VerticalAlignment aligV){
          this.aligH = aligH;
          this.aligV = aligV;
      }
      
      /**
       * Converts the point from the coordinate system of SOUTHWEST anchor to
       * the coordinate system of this anchor.
       * 
       * @param p Point to be transformed
       * @param d Size of the container box
       * @return 
       */
      public Point convert(Point p, Dimension d){
          return new Point(aligH.convertX(p.x, d), aligV.convertY(p.y, d));
      }
      
      /**
       * Converts the point from the coordinate system of SOUTHWEST anchor to
       * the coordinate system of this anchor in double precision.
       * 
       * @param p Point to be transformed
       * @param d Size of the container box
       * @return 
       */
      public PointD convert(PointD p, DimensionD d){
          return new PointD(aligH.convertX(p.x, d), aligV.convertY(p.y, d));
      }
      
      /**
       * Reverts the point from the coordinate system of this anchor to
       * the coordinate system of SOUTHWEST anchor.
       * 
       * @param p Point to be transformed
       * @param d Size of the container box
       * @return 
       */
      public Point revert(Point p, Dimension d){
          return new Point(aligH.revertX(p.x, d), aligV.revertY(p.y, d));
      }
      
      /**
       * Reverts the point from the coordinate system of this anchor to
       * the coordinate system of SOUTHWEST anchor in DOUBLE precision.
       * 
       * @param p Point to be transformed
       * @param d Size of the container box
       * @return 
       */
      public PointD revert(PointD p, DimensionD d){
          return new PointD(aligH.revertX(p.x, d), aligV.revertY(p.y, d));
      }
      
      /**
       * Transforms the point from the coordinate system of the source anchor to
       * the coordinate system of this anchor.
       * 
       * @param p Point to be transformed
       * @param d Size of the container box
       * @param source Source Anchor
       * @return 
       */
      public Point transform(Point p, Dimension d, Anchor source){
          return convert(source.revert(p, d), d);
      }
      
      /**
       * Transforms the point from the coordinate system of the source anchor to
       * the coordinate system of this anchor in DOUBLE precision.
       * 
       * @param p Point to be transformed
       * @param d Size of the container box
       * @param source Source Anchor
       * @return 
       */
      public PointD transform(PointD p, DimensionD d, Anchor source){
          return convert(source.revert(p, d), d);
      }
      
      /**
       * Transforms the point from the coordinate system of the source anchor to
       * the coordinate system of the target anchor.
       * 
       * @param p Point to be transformed
       * @param d Size of the container box
       * @param source Source Anchor
       * @param target Target Anchor
       * @return 
       */
      public static Point transform(Point p, Dimension d, Anchor source, Anchor target){
          return target.convert(source.revert(p, d), d);
      }
      /**
       * Transforms the point from the coordinate system of the source anchor to
       * the coordinate system of the target anchor in DOUBLE precision.
       * 
       * @param p Point to be transformed
       * @param d Size of the container box
       * @param source Source Anchor
       * @param target Target Anchor
       * @return 
       */
      public static PointD transform(PointD p, DimensionD d, Anchor source, Anchor target){
          return target.convert(source.revert(p, d), d);
      }
      
      /**
       * Returns the horizontal alignment of this anchor.
       * 
       * @return HorizontalAlignment component of this anchor.
       */
      public HorizontalAlignment getAlignmentH(){
          return aligH;
      }
      /**
       * Returns the vertical alignment of this anchor.
       * 
       * @return VerticalAlignment component of this anchor.
       */
      public VerticalAlignment getAlignmentV(){
          return aligV;
      }
    
}
