package xyz.jadonfowler.obx;

public abstract class Operator {
    public abstract ArgAmount getArgAmount();

    public abstract Object run(Object x, Object y, Object z);

    public abstract Object run(Object x, Object y);

    public abstract Object run(Object x);

    public static enum ArgAmount {
        ONE(1), TWO(2), THREE(3);
        int args;

        ArgAmount(int amount) {
            this.args = amount;
        }

        public int toInt() {
            return args;
        }
    }
}