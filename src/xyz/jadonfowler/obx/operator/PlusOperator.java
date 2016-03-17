package xyz.jadonfowler.obx.operator;

import xyz.jadonfowler.obx.*;

public class PlusOperator extends Operator {
    @Override public ArgAmount getArgAmount() {
        return ArgAmount.TWO;
    }

    @Override public Object run(Object x, Object y, Object z) {
        return null;
    }

    @Override public Object run(Object x, Object y) {
        if (x instanceof String || y instanceof String) return x.toString() + y.toString();
        else if (x instanceof Double && y instanceof Double) return ((double) x) + ((double) y);
        return null;
    }

    @Override public Object run(Object x) {
        return null;
    }
}