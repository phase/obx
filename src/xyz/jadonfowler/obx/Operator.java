package xyz.jadonfowler.obx;

public abstract class Operator {
    public abstract ArgAmount getArgAmount();

    public abstract Object run(Object x, Object y, Object z);

    public abstract Object run(Object x, Object y);

    public abstract Object run(Object x);

    public static enum ArgAmount {
        ONE(0b001), TWO(0b010), THREE(0b100), 
        ONE_OR_TWO(0b011), ONE_OR_THREE(0b101), 
        TWO_OR_THREE(0b110), ALL(0b111);

        int args;

        ArgAmount(int amount) {
            this.args = amount;
        }

        public int toInt() {
            return args;
        }

        public boolean handlesOne() {
            return (args & 0b001) == 0b001;
        }

        public boolean handlesTwo() {
            return (args & 0b010) == 0b010;
        }

        public boolean handlesThree() {
            return (args & 0b100) == 0b100;
        }
    }
}