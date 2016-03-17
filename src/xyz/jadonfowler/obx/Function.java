package xyz.jadonfowler.obx;

import java.util.*;

public class Function {
    static HashMap<Character, Function> functions = new HashMap<Character, Function>();
    char id;
    String line;
    Stack stack;

    public Function(String line) {
        this.line = line;
        this.id = (char) ('A' + functions.size());
        functions.put(id, this);
    }

    public static HashMap<Character, Function> getFunctions() {
        return functions;
    }

    public Stack getStack() {
        return stack;
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
        stack = new Stack();
        char[] chars = new StringBuffer().append(line).reverse().toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            parse(chars[i], i + 1 < chars.length ? chars[i + 1] : (char) 0, x, y, z);
        }
        return stack.pop();
    }

    // flags
    private boolean inString = false;
    private boolean number = false;
    private StringBuffer buffer;

    public void parse(char c, char next, Object x, Object y, Object z) {
        String s = String.valueOf(c);
        if (number && !inString) {
            buffer.append(c);
            if (!(String.valueOf(next).matches("[0-9]") || next == '.')) {
                double id = Double.parseDouble(buffer.reverse().toString());
                stack.push(id);
                number = false;
            }
        }
        else if (inString && !number) {
            if (c == '"') {
                stack.push(buffer.reverse().toString());
                inString = false;
            }
            else {
                buffer.append(c);
            }
        }
        else if (s.matches("[0-9]")) {
            number = true;
            buffer = new StringBuffer();
            buffer.append(c);
            if (!(String.valueOf(next).matches("[0-9]") || next == '.')) {
                double id = Double.parseDouble(buffer.reverse().toString());
                stack.push(id);
                number = false;
            }
        }
        else if (c == '"') {
            inString = true;
            buffer = new StringBuffer();
        }
        else if (s.matches("[A-Z]")) {
            Object nx = stack.stack.size() > 0 ? stack.pop() : null;
            Object ny = stack.stack.size() > 0 ? stack.pop() : null;
            Object nz = stack.stack.size() > 0 ? stack.pop() : null;
            stack.push(functions.get(c).run(nx, ny, nz));
        }
        else if (c == 'x') {
            stack.push(x);
        }
        else if (c == 'y') {
            stack.push(y);
        }
        else if (c == 'z') {
            stack.push(z);
        }
        else {
            if (Obx.getOperators().keySet().contains(c)) {
                Operator op = Obx.getOperators().get(c);
                if (op.getArgAmount() == Operator.ArgAmount.ONE) stack.push(op.run(stack.pop()));
                else if (op.getArgAmount() == Operator.ArgAmount.TWO) stack.push(op.run(stack.pop(), stack.pop()));
                else if (op.getArgAmount() == Operator.ArgAmount.THREE)
                    stack.push(op.run(stack.pop(), stack.pop(), stack.pop()));
            }
        }
        // Pretty awesome debug statement
        //System.out.println(c + " " + next + " " + (buffer == null ? " " : buffer.toString()) + " " + stack.toString());
    }
}