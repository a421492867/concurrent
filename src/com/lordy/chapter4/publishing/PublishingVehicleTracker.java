package com.lordy.chapter4.publishing;

import com.lordy.chapter4.delegating.Point;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 将线程安全委托给ConcurrentMap Map中的元素也是线程安全的
 */

@ThreadSafe
public class PublishingVehicleTracker {

    private final ConcurrentMap<String, SafePoint> locations;

    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(points);
    }

    public Map<String, SafePoint> getLocations(){
        return unmodifiableMap;
    }



    public SafePoint getLocation(String id){
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y){
        if(!locations.containsKey(id)){
            throw new IllegalArgumentException();
        }
        locations.get(id).set(x, y);
    }

}
