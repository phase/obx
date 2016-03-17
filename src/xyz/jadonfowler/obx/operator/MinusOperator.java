package xyz.jadonfowler.obx.operator;

import xyz.jadonfowler.obx.*;

public class MinusOperator extends Operator {
    @Override public ArgAmount getArgAmount() {
        return ArgAmount.TWO;
    }

    @Override public Object run(Object x, Object y, Object z) {
        return null;
    }

    @Override public Object run(Object x, Object y) {
        if (x instanceof String || y instanceof String) {
            String xs = x.toString();
            String ys = y.toString();
            return xs.replace(ys, "");
        }
        else if (x instanceof Double && y instanceof Double) return ((double) x) - ((double) y);
        return null;
    }

    @Override public Object run(Object x) {
        return null;
    }
}