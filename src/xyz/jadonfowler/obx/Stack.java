package xyz.jadonfowler.obx;

import java.util.*;

public class Stack {
    
    private ArrayList<Object> stack = new ArrayList<Object>();
    
    public Stack push(Object o) {
        assert o != null : "Object pushed to stack " + this.hashCode() + " was null";
        stack.add(o);
        return this;
    }
    
    public Object pop() {
        assert stack.size() > 0 : "Stack " + this.hashCode() + " doesn't have an object to pop";
        Object o = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return o;
    }
    
    public Object get(int i) {
        return stack.get(i);
    }
    
    public Stack set(int i, Object o) {
        stack.set(i, o);
        return this;
    }
    
}