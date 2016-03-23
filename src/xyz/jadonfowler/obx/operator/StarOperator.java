package xyz.jadonfowler.obx.operator;

import java.util.*;
import xyz.jadonfowler.obx.*;

public class StarOperator extends Operator {
    @Override public ArgAmount getArgAmount() {
        return ArgAmount.TWO;
    }

    @Override public Object run(Object x, Object y, Object z) {
        return null;
    }

    @Override public Object run(Object x, Object y) {
        if (x instanceof String && y instanceof Double) {
            int yi = (int) Math.round((float) y);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < yi; i++) {
                buffer.append(x.toString());
            }
            return buffer.toString();
        }
        else if (x instanceof Double && y instanceof Double) return ((double) x) * ((double) y);
        else if(x instanceof ArrayList && y instanceof ArrayList){ 
            //something with matrix multiplication?
        }
        return null;
    }

    @Override public Object run(Object x) {
        return null;
    }
}