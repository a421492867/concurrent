package com.lordy.chapter4.delegating;


import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * 基于委托实现线程安全
 *
 * 多个变量如果是彼此独立的  可以将线程安全委托给多个变量
 *
 *将线程安全委托给了ConcurrentMap
 */
@ThreadSafe
public class DelegatingVehicleTracker {

    private final ConcurrentMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(points);
    }

    public Map<String, Point> getLocations(){
        return unmodifiableMap;
    }

//    public Map<String, Point> getLocations(){
//        return Collections.unmodifiableMap(new HashMap<>(locations));
//    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y){
        if(locations.replace(id, new Point(x, y)) == null){
            throw new IllegalArgumentException("Invalid vehicle name : " + id);
        }
    }
}
