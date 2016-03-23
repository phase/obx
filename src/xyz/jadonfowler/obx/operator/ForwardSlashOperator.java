package xyz.jadonfowler.obx.operator;

import java.util.*;
import xyz.jadonfowler.obx.*;

public class ForwardSlashOperator extends Operator {
    @Override public ArgAmount getArgAmount() {
        return ArgAmount.TWO;
    }

    @Override public Object run(Object x, Object y, Object z) {
        return null;
    }

    @Override public Object run(Object x, Object y) {
        if (x instanceof String && y instanceof String) {
            String[] split = x.toString().split(y.toString());
            ArrayList<Object> a = new ArrayList<Object>();
            // a.addAll(s); // wtf why this no work?
            for (String s : split)
                a.add(s);
            return a;
        }
        else if (x instanceof Double && y instanceof Double) return ((double) x) / ((double) y);
        return null;
    }

    @Override public Object run(Object x) {
        return null;
    }
}