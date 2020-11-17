package com.lordy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 在tag1处，尝试更换为另一个对象，引用发生了变化，会抛出UnsupportedOperationException异常。unmodifiableMap阻止了对其的修改
 *
 * 但如果引用不变，见tag2处，还是tom， 但对该对象内容作了修改，unmodifiableMap并未阻止该行为。unmodifiableMap的内容变为 tom age为11
 */
public class UnmodifiableMap {


    public static void main(String[] args) {
        Map<String, Student> map = new HashMap<>();
        Student tom = new Student("tom", 3);
        map.put("tom", tom);
        map.put("jerry", new Student("jerry", 1));

        Map<String, Student> unmodifiableMap = Collections.unmodifiableMap(map);

        //unmodifiableMap.put("tom", new Student("tom", 11));  // tag1
        tom.setAge(11); // tag2
        System.out.println(unmodifiableMap);

    }




}

class Student{

    private String name;

    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ",age=" + age +
                "}";
    }
}
