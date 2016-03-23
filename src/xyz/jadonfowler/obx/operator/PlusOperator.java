package xyz.jadonfowler.obx.operator;

import java.util.*;
import xyz.jadonfowler.obx.*;

public class PlusOperator extends Operator {
    @Override public ArgAmount getArgAmount() {
        return ArgAmount.TWO;
    }

    @Override public Object run(Object x, Object y, Object z) {
        return null;
    }

    @SuppressWarnings("unchecked") @Override public Object run(Object x, Object y) {
        if (x instanceof String || y instanceof String) return x.toString() + y.toString();
        else if (x instanceof Double && y instanceof Double) return ((double) x) + ((double) y);
        else if (x instanceof ArrayList && y instanceof ArrayList) {
            ArrayList<Object> xa = (ArrayList<Object>) x;
            xa.addAll((ArrayList<Object>) y);
            return xa;
        }
        return null;
    }

    @Override public Object run(Object x) {
        return null;
    }
}