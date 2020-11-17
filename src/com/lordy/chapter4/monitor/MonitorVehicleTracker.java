package com.lordy.chapter4.monitor;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Java监视器模式 线程安全
 *
 * MutablePoint不是线程安全的 但MonitorVehicleTracker是线程安全的
 * Map对象和MutablePoint对象都未曾发布
 * 当需要返回车辆位置时 通过MutablePoint拷贝构造函数或者deepCopy方法来复制正确的值 从而生成一个新的Map对象
 * 并且该对象中的值与原有Map对象中的key value都相同
 *
 *
 */

@ThreadSafe
public class MonitorVehicleTracker {

    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id){
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocations(String id, int x, int y){
        MutablePoint loc = locations.get(id);
        if(loc == null){
            throw new IllegalArgumentException("No such ID : " + id);
        }
        loc.x = x;
        loc.y = y;
    }


    /**
     * deepCopy不只是用unmodifiableMap来包装Map
     * 只用unmodifiableMap包装 只能防止容器对象被修改 而不能防止调用者修改保存在Map中的对象
     *
     * 同样 如果只通过拷贝构造函数来填充result 也是不正确的  因为这样只是复制了指向Point对象的引用 而不是Point对象本身
     * （理解 ： result中put的MutablePoint是一个引用而不是本身  如果不用unmodifiableMap 将这个MutablePoint对象改变 这个引用便指向了这个改变后的对象
     *  使用unmodifiableMap以确保key value均不可变）
     *
     * @param m
     * @return
     */
    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m){
        Map<String, MutablePoint> result = new HashMap<>();
        for(String id : m.keySet()){
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
