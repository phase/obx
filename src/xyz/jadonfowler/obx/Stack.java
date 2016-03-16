package xyz.jadonfowler.obx;

import java.util.*;

public class Stack {
    
    public ArrayList<Object> stack = new ArrayList<Object>();
    
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
        return stack.size() < i ? stack.get(i) : null;
    }
    
    public Stack set(int i, Object o) {
        stack.set(i, o);
        return this;
    }
    
    public int last() {
        return stack.size()-1;
    }
    
    public String toString() {
        return stack.toString();
    }
    
}