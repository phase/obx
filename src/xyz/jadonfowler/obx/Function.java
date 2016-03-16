package xyz.jadonfowler.obx;

import java.util.*;

public class Function {
    
    static HashMap<Character, Function> functions = new HashMap<Character, Function>();
    
    char id;
    String line;
    Stack stack;

    public Function(String line) {
        this.line = line;
        this.stack = new Stack();
        char id = (char) ('A'+functions.keySet().size()-1);
        functions.put(id, this);
    }
    
    public static HashMap<Character, Function> getFunctions() {
        return functions;
    }
    
    public String toString() {
        return line;
    }

    /**
     * The idea here is to read the code backwards and push each new object to
     * the stack. When we hit an operator, take the top two objects on the stack
     * and perform the operation, then push the object to the top of the stack.
     * 
     * Wait don't do that make a tree. Urg I'll do that later
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    public Object run(Object x, Object y, Object z) {
        for (char c : new StringBuffer().append(line).reverse().toString().toCharArray()) {
            parse(c, x, y, z);
        }
        return null;
    }
    
    //flags
    private boolean inString = false;
    private boolean number = false;
    private StringBuffer buffer;

    public void parse(char c, Object x, Object y, Object z) {
        String s = String.valueOf(c);
        if (number && !inString) {
            if (s.matches("0-9") || c == '.') {
                buffer.append(c);
            }
            else {
                //push number to stack after reversing it
                double id = Double.parseDouble(buffer.reverse().toString());
                stack.push(id);
                number = false;
                //redo parse now that the number has finished
                parse(c, x, y, z);
            }
        }
        else if (inString && !number) {
            if(c == '"') {
                stack.push(buffer.reverse().toString());
                inString = false;
            } else {
                buffer.append(c);
            }
        }
        else if (s.matches("0-9")) {
            number = true;
            buffer = new StringBuffer();
            buffer.append(c);
        }
        else if (c == '"') {
            inString = true;
            buffer = new StringBuffer();
        }
        else if(c == 'x') {
            stack.push(x);
        }
        else if(c == 'y') {
            stack.push(y);
        }
        else if(c == 'z') {
            stack.push(z);
        }
    }
}