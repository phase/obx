package xyz.jadonfowler.obx;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import xyz.jadonfowler.obx.operator.*;

public class Obx {
    private static HashMap<Character, Operator> operators;

    static {
        operators = new HashMap<Character, Operator>();
        operators.put('+', new PlusOperator());
        operators.put('-', new MinusOperator());
    }

    private static Obx instance;

    public static void main(String[] args) {
        instance = new Obx();
        if (args.length > 0) {
            // file mode
            String output = instance.runFile(args[0], args);
            System.out.println(output);
        }
        else {
            // REPL
            System.out.println("obx REPL");
            Scanner in = new Scanner(System.in);
            String line;
            while ((line = in.nextLine()) != null) {
                if (line.trim().equals("")) break;
                Function f = new Function(line);
                // TODO: Replace with something else
                Object o = f.run(0d, 1d, 2d);
                String result = o instanceof Double ? doubleToString((double) o) : o.toString();
                System.out.println(result + "\n" + f.id + "(0,1,2): " + result + " " + f.getStack().toString());
            }
            in.close();
        }
    }

    public static Obx getInstance() {
        return instance;
    }

    public static HashMap<Character, Operator> getOperators() {
        return operators;
    }

    public String runFile(String fileName, String[] args) {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                new Function(line);
            }
            reader.close();
            int functionSize = Function.getFunctions().size();
            Object x = args.length > 1 ? isDouble(args[1]) ? Double.parseDouble(args[1]) : args[1] : null;
            Object y = args.length > 2 ? isDouble(args[2]) ? Double.parseDouble(args[2]) : args[2] : null;
            Object z = args.length > 3 ? isDouble(args[3]) ? Double.parseDouble(args[3]) : args[3] : null;
            Function lastFunction = Function.getFunctions().get((char) ('A' + functionSize - 1));
            Object o = lastFunction.run(x, y, z);
            return o instanceof Double ? doubleToString((double) o) : o.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final Pattern DOUBLE_PATTERN = Pattern
            .compile("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)"
                    + "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|"
                    + "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))"
                    + "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

    public static boolean isDouble(String s) {
        return DOUBLE_PATTERN.matcher(s).matches();
    }

    public static String doubleToString(double d) {
        return ((int) d) == d ? (d + "").split(".0")[0] : d + "";
    }
}