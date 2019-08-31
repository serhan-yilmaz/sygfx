/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygfx.util;

import java.awt.Event;
import java.util.EventListener;
import java.util.TreeMap;

/**
 *
 * @author Serhan
 */
public class EventListenerList {
    private final TreeMap<String, EventListener> list = new TreeMap<>();
    
    public void add(EventListener l){
        list.put(l.getClass().getName(), l);
    }
    
    public void remove(EventListener l){
        list.remove(l.getClass().getName());
    }
    
    public void remove(Class<EventListener> l){
        list.remove(l.getName());
    }
    
    public EventListener get(Class<EventListener> l){
        return list.get(l.getName());
    }
    
}
